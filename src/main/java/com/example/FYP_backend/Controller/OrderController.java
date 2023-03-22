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
    @Resource
    PmsAbstractProductRepository productRepo;

    @RequestMapping(value = "/ordersThisWeek")
    public List<OmsOrder> getOrdersThisWeek(){
        return orderRepo.findAllByCreateTimeThisWeek(TimeUtils.getStartOfWeekInstant());
    }
    @RequestMapping(value = "/orderItems/{orderID}")
    public List<OmsOrderItem> getItemsById(@PathVariable(value = "orderID") int orderID){
        return orderItemRepo.findByOrder_Id(orderID);
    }

    @RequestMapping(value = "/updateOrderStatus")
    public void updateOrdersStatus(@RequestBody List<Integer> orderIds){
        List<OmsOrder> res = orderRepo.findAllByIdIn(orderIds);
        for (OmsOrder order:res){
            order.setStatus(1);
        };
        orderRepo.saveAll(res);
        //分两步走，先取出，再全改再全存
    }

    @RequestMapping(value = "/updateReceiptStatus")
    public void updateReceiptsStatus(@RequestBody List<Integer> receiptIds){
        List<OmsReceipt> res = receiptRepo.findAllByIdIn(receiptIds);
        for (OmsReceipt receipt:res){
            receipt.setStatus(1);
        };
        receiptRepo.saveAll(res);
        //分两步走，先取出，再全改再全存
    }
    @RequestMapping(value = "/receiptsSaving")
    public void saveReceipts(@RequestBody List<OmsReceipt> receipts){
        receiptRepo.saveAll(receipts);
    }

    @RequestMapping(value = "/receiptItemsSaving")//这个函数，其实是负责把类似orderItem的东西通过赋予batchId,让他与具体货物对应，总共有三个功能，可以在service层中重构，我在这里就不改了
    public void saveReceiptItems(@RequestBody List<OmsReceiptItem> items){
        //这里其实是三个功能，第一，是修改batch，第二，是更新batch_id，第三，是以amountNeed去更新to_be_outbound
        List<OmsReceiptItem> result = new LinkedList<>();
        for (OmsReceiptItem item:items) {
            List<PmsBatch> batches = batchRepo.findAllByIdAndOrderByBBD(item.getProduct().getId());
            //3更新to_be_outbound
            PmsAbstractProduct product = productRepo.findFirstByIdEquals(item.getProduct().getId());
            product.setToBeOutbound(product.getToBeOutbound()+item.getAmount());//我敢这么减，是因为我相信传进来的东西已经处理好了，没有问题了
            productRepo.save(product);
            //1修改batch
            BigDecimal singlePrice = item.getTotalPrice().divide(BigDecimal.valueOf(item.getAmount()));//因为可能是强加的价格，所以要把单价这么搞
            int amountNeed = item.getAmount();

            for (PmsBatch batch: batches){
                if(amountNeed != 0){
                    if(amountNeed > batch.getAmount()){//如果这一批不能覆盖，就生成一个，推到结果集
                        //2更新batch_id
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
            receiptItemRepo.saveAll(result);
        };
    }
}
