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
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/cp")
public class CustomerReceiptController {
    @Resource
    private OmsReceiptRepository receiptRepo;
    @Resource
    private OmsReceiptItemRepository receiptItemRepo;

    @RequestMapping(value = "/receiptById/{receiptID}")
    public OmsReceipt receiptById(@PathVariable int receiptID){
        return receiptRepo.findOmsReceiptByIdEquals(receiptID);
    }

    @RequestMapping(value = "/receiptItems/{receiptID}")
    public List<OmsReceiptItem> receiptItems(@PathVariable int receiptID){
        return receiptItemRepo.findById_ReceiptIdEquals(receiptID);
    }
    @RequestMapping(value = "/returnItem")
    public void returnItem(@RequestBody OmsReceiptItem receiptItem){
        OmsReceiptItem toBeChanged = receiptItemRepo.findByIdEquals(receiptItem.getId());
//        System.out.println(toBeChanged.getAmount());
//        System.out.println(toBeChanged.getId().getReceiptId());
//        System.out.println(toBeChanged.getId().getProductId());
//        System.out.println(toBeChanged.getId().getBatchId());
//        反正现在是这样的，你如果不加单独标识的id,就出大问题，
        if(toBeChanged.getAmount() > receiptItem.getAmount()){
            BigDecimal singlePrice = toBeChanged.getTotalPrice().divide(new BigDecimal(toBeChanged.getAmount()));

            toBeChanged.setAmount(toBeChanged.getAmount() - receiptItem.getAmount());
            toBeChanged.setTotalPrice(singlePrice.multiply(new BigDecimal(toBeChanged.getAmount())));

            receiptItem.setTotalPrice(singlePrice.multiply(new BigDecimal(receiptItem.getAmount())));
            receiptItem.setStatus(2);

            receiptItemRepo.save(toBeChanged);
            receiptItemRepo.save(receiptItem);
//            对哦，那你现在不就是出问题的那个状态吗，
        }else if(toBeChanged.getAmount() == receiptItem.getAmount()){
            toBeChanged.setStatus(2);
            receiptItemRepo.save(toBeChanged);
        }else {
            System.out.println("Error");
        }
    }

}
