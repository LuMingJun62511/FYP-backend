package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.*;
import com.example.FYP_backend.Model.*;

import com.example.FYP_backend.Utils.TimeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/oms")
public class OrderController {
    @Resource
    OmsOrderRepository orderRepo;
    @Resource
    OmsOrderItemRepository orderItemRepo;
    @Resource
    OmsReceiptRepository receiptRepo;
    @Resource
    OmsReceiptItemRepository receiptItemRepo;
    @Resource
    PmsBatchRepository batchRepo;

    @RequestMapping(value = "/ordersThisWeek")
    public List<OmsOrder> getOrdersThisWeek(){
        return orderRepo.findAllByCreateTimeThisWeek(TimeUtils.getStartOfWeekInstant());
    }
    @RequestMapping(value = "/orderItems/{orderID}")
    public List<OmsOrderItem> getItemsById(@PathVariable(value = "orderID") int orderID){
        return orderItemRepo.findByOrder_Id(orderID);
    }

    @RequestMapping(value = "/receiptsSaving")
    public void saveReceipts(@RequestBody List<OmsReceipt> receipts){
        for (OmsReceipt o:receipts) {
            System.out.println("难道id真的空");
            System.out.println(o.getId());
        }
        receiptRepo.saveAll(receipts);
    }

    @RequestMapping(value = "/receiptItemsSaving")
    public void saveReceiptItems(@RequestBody List<OmsReceiptItem> items){
        List<OmsReceiptItem> result = new LinkedList<>();
        for (OmsReceiptItem item:items) {
            //首先，整些batch,我这里只要能处理的，肯定不会缺货，我就放心大胆的用
            List<PmsBatch> batches = batchRepo.findAllByIdAndOrderByBBD(item.getProduct().getId());
            System.out.println(batches.size());
            //这里其实是两个功能，应该拆开，第一，是batch扣除，第二，是标记batch_id，
            BigDecimal singlePrice = item.getTotalPrice().divide(BigDecimal.valueOf(item.getAmount()));
            int amountNeed = item.getAmount();
            for (PmsBatch batch: batches){
                if(amountNeed != 0){
                    if(amountNeed > batch.getAmount()){//如果这一批不能覆盖，就生成一个，推到结果集
                        OmsReceiptItem itemAdded = new OmsReceiptItem(
                                item.getId(),
                                item.getReceipt(),
                                item.getProduct(),
                                batch,
                                singlePrice.multiply(BigDecimal.valueOf(batch.getAmount())),
                                batch.getAmount(),
                                item.getStatus()
                        );
                        result.add(itemAdded);
                        amountNeed = amountNeed - batch.getAmount();
                        batch.setAmount(0);
                    }else {//如果这一批能覆盖，也生成一个，推到结果集
                        OmsReceiptItem itemAdded = new OmsReceiptItem(
                                item.getId(),
                                item.getReceipt(),
                                item.getProduct(),
                                batch,
                                singlePrice.multiply(BigDecimal.valueOf(amountNeed)),
                                amountNeed,
                                item.getStatus()
                        );
                        result.add(itemAdded);
                        batch.setAmount(batch.getAmount() - amountNeed);
                        amountNeed = 0;
                    }
                }else {
                    break;
                }
            }
            batchRepo.saveAll(batches);
//            for (OmsReceiptItem i:result) {
//                System.out.println("Receipt ID");
//                System.out.println(i.getReceipt().getId());
//                System.out.println("Product ID");
//                System.out.println(i.getProduct().getId());
//                System.out.println("amount");
//                System.out.println(i.getAmount());
//            }
            receiptItemRepo.saveAll(result);
        };
    }
}
