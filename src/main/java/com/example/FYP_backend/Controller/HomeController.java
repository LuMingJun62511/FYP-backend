package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.OmsOrderRepository;
import com.example.FYP_backend.DAO.PmsAbstractProductRepository;
import com.example.FYP_backend.DAO.PmsBatchRepository;
import com.example.FYP_backend.Model.ChartOrderData;
import com.example.FYP_backend.Model.OmsOrder;
import com.example.FYP_backend.Model.PmsAbstractProduct;
import com.example.FYP_backend.Model.PmsBatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping(value = "/api/home")
public class HomeController {

    // 获取当前时间
    Instant now = Instant.now();
    // 获取当前时间所在的时区
    ZoneId zone = ZoneId.systemDefault();
    // 将当前时间转换为所在时区的本地日期时间
    LocalDateTime localDateTime = LocalDateTime.ofInstant(now, zone);
    // 获取当前时间是本周的第几天（周一为1，周日为7）
    DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
    // 获取本周一的日期时间
    LocalDateTime startOfWeek = localDateTime.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    // 将本周一的日期时间转换为Instant类型
    Instant startOfWeekInstant = startOfWeek.atZone(zone).toInstant();
//    逻辑是这样，默认结算日为周一，现在直到下一个结算日为不完整的一周，不过实时监控吗，这周也得算
//    然后从上个结算日往前推7整周，为我需要的时间，
    Instant eightSettlementDateAgo = startOfWeekInstant.minus(Duration.ofDays(49));
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

        List<OmsOrder> queryList = orderRepo.findAllByCreateTimeTwoMonthAgo(eightSettlementDateAgo);
        for (OmsOrder o: queryList) {
            int week = countWeeksBeforeNow(o.getCreateTime());
            ChartOrderData changed = dataList.get(7-week);
            changed.setSale(changed.getSale()+o.getPayAmount().doubleValue());
            changed.setAmount(changed.getAmount()+1);
        }
        return dataList;
    }

    @RequestMapping("/checkProducts")
    public void checkProducts(){
        Instant aWeekLater = now.plus(Duration.ofDays(7));//一周后的现在
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

    public int countWeeksBeforeNow(Instant a) {
        Instant now = Instant.now();
        long daysBetween = ChronoUnit.DAYS.between(a.atZone(ZoneId.systemDefault()), now.atZone(ZoneId.systemDefault()));
        int weeks = (int) (daysBetween / 7);
        return weeks;
    }
}
