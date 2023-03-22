package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.OmsReceiptItemRepository;
import com.example.FYP_backend.DAO.OmsReceiptRepository;
import com.example.FYP_backend.DAO.PmsAbstractProductRepository;
import com.example.FYP_backend.DAO.PmsBatchRepository;
import com.example.FYP_backend.Model.*;
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
    @Resource
    PmsBatchRepository batchRepo;
    @Resource
    PmsAbstractProductRepository productRepo;

    @RequestMapping(value = "/notFinishedReceipts")
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

    @RequestMapping(value = "/receiptItemsByReceiptId/{receiptID}")
    public List<OmsReceiptItem> getReceiptItemsByRId(@PathVariable(value = "receiptID") int receiptID){
        return receiptItemRepo.findById_ReceiptIdEquals(receiptID);
    }

    @RequestMapping(value = "/updateBatch/{batchId}/{amount}")
    public void updateBatch(
            @PathVariable(value = "batchId") int batchId,
            @PathVariable(value = "amount") int amount
        ){
        PmsBatch batch = batchRepo.findByIdEquals(batchId);
        batch.setAmount(batch.getAmount()+amount);
        batchRepo.save(batch);
    }

    @RequestMapping(value = "/updateProduct/{productId}/{amount}")
    public void updateProduct(
            @PathVariable(value = "productId") int productId,
            @PathVariable(value = "amount") int amount
        ){
        PmsAbstractProduct product = productRepo.findFirstByIdEquals(productId);
        product.setStock(product.getStock()+amount);
        productRepo.save(product);
    }

    @RequestMapping(value = "/updateReceiptItems/{amount}")
    public void updateReceiptItems(
            @PathVariable(value = "amount") int amount,
            @RequestBody OmsReceiptItemId RIID
        ){
        OmsReceiptItem receiptItem = receiptItemRepo.findByIdEquals(RIID);
        receiptItem.setAmount(receiptItem.getAmount() - amount);
        receiptItemRepo.save(receiptItem);
    }





}
