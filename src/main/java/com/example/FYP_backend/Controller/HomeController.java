package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.OmsOrderRepository;
import com.example.FYP_backend.DAO.PmsAbstractProductRepository;
import com.example.FYP_backend.DAO.PmsBatchRepository;
import com.example.FYP_backend.Model.ChartOrderData;
import com.example.FYP_backend.Model.OmsOrder;
import com.example.FYP_backend.Model.PmsAbstractProduct;
import com.example.FYP_backend.Model.PmsBatch;
import com.example.FYP_backend.Utils.TimeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.*;
import java.util.LinkedList;
import java.util.List;



@RestController
@RequestMapping(value = "/api/home")
public class HomeController {

    @Resource
    private OmsOrderRepository orderRepo;
    @Resource
    private PmsAbstractProductRepository productRepo;
    @Resource
    private PmsBatchRepository batchRepo;
    @RequestMapping("/orderDataForChart")
    public List<ChartOrderData> orderData() {
        List<ChartOrderData> dataList = new LinkedList<>();
        for (int i = 0; i < 8; i++) {//8周
            dataList.add(new ChartOrderData(i-7,0.0,0));
        }

        List<OmsOrder> queryList = orderRepo.findAllByCreateTimeTwoMonthAgo(TimeUtils.getEightSettlementDateAgo());
        for (OmsOrder o: queryList) {
            int week = TimeUtils.countWeeksBeforeDDay(o.getCreateTime());
            ChartOrderData changed = dataList.get(7-week);
            changed.setSale(changed.getSale()+o.getPayAmount().doubleValue());
            changed.setAmount(changed.getAmount()+1);
        }
        return dataList;
    }

    @RequestMapping("/checkProducts")
    public void checkProducts(){
        Instant aWeekLater = TimeUtils.getDday().plus(Duration.ofDays(7));//Dday一周后
//        Instant aWeekLater = now.plus(Duration.ofDays(7));//一周后的现在
        //这个就是检查刷新，再返回商品函数，
        List<PmsAbstractProduct> products= productRepo.findAll();
        for (PmsAbstractProduct product:products) {
//            判断一下货物是不是不够了
            if(product.getStock() - product.getToBeOutbound() <= product.getLowStock()){
                product.setIsLow(1);
            }
            List<PmsBatch> batches = batchRepo.findByAbstractProduct_IdEquals(product.getId());
            for (PmsBatch batch : batches) {
                if(batch.getBbd().isBefore(aWeekLater)){//如果有bbd在一周以内，那就很危险了，
                    product.setIsUrgent(1);
                }
            }
            //比完了记得把这个batches推上去，
            batchRepo.saveAll(batches);
        }
        //比完了记得把这个products推上去，
        productRepo.saveAll(products);
    }

    @RequestMapping("/getProducts")
    public List<PmsAbstractProduct> getProducts(){
        return productRepo.findAll();
    }

}
