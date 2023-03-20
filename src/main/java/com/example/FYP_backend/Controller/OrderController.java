package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.*;
import com.example.FYP_backend.Model.*;

import com.example.FYP_backend.Utils.TimeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
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
        receiptRepo.saveAll(receipts);
    }

    @RequestMapping(value = "/receiptItemsSaving")
    public void saveReceiptItems(@RequestBody List<OmsToBeSavedReceiptItem> items){
        for (OmsToBeSavedReceiptItem item:items) {
            //首先，整些batch,我这里只要能处理的，肯定不会缺货，我就放心大胆的用
            List<PmsBatch> batches= batchRepo.findAllByIdAndOrderByBBD(item.getProductId());
            int tempNeed = item.getAmount();
            for (PmsBatch batch: batches){
                if(tempNeed!=0){
                    if(tempNeed>batch.getAmount()){
                        tempNeed = tempNeed-batch.getAmount();
                        batch.setAmount(0);
                    }else {
                        batch.setAmount(batch.getAmount()-tempNeed);
                        tempNeed=0;
                    }
                }else {
                    break;
                }
            }
            batchRepo.saveAll(batches);

//            System.out.println(item.getReceiptId());
//            System.out.println();
//            System.out.println(item.getBatchId());
//            System.out.println(item.getTotalPrice());
//            System.out.println(item.getAmount());
//            System.out.println(item.getStatus());
        };


        //其次，我给他整些
//        receiptRepo.saveAll(receipts);
    }
}
