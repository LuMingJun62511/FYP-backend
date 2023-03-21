package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.OmsReceiptItemRepository;
import com.example.FYP_backend.DAO.OmsReceiptRepository;
import com.example.FYP_backend.Model.OmsReceipt;
import com.example.FYP_backend.Model.OmsReceiptItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/ims")
public class InOutboundController {
    @Resource
    OmsReceiptRepository receiptRepo;
    @Resource
    OmsReceiptItemRepository receiptItemRepo;

    @RequestMapping(value = "/unhandledReceipts")
    public List<OmsReceipt> getReceipts(){
        return receiptRepo.findAllByStatusEquals(0);//返回所有未发货单子
    }

    @RequestMapping(value = "/allReceipts")
    public List<OmsReceipt> getAllReceipts(){
        return receiptRepo.findAll();
    }

    @RequestMapping(value = "/receipt/{receiptID}")
    public OmsReceipt getOneReceipt(@PathVariable(value = "receiptID") int receiptID){
        return receiptRepo.findOmsReceiptByIdEquals(receiptID);
    }

    @RequestMapping(value = "/receiptItems")
    public List<OmsReceiptItem> getReceiptItems(@RequestBody List<OmsReceipt> receipts){
        List<Integer> queryList = new LinkedList<>();
        for (OmsReceipt r:receipts) {
            queryList.add(r.getId());
        }
        return receiptItemRepo.findAllByReceiptIn(queryList);
    }
}
