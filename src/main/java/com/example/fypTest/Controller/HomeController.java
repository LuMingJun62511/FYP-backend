package com.example.fypTest.Controller;

import com.example.fypTest.DAO.OmsOrderRepository;
import com.example.fypTest.Model.ChartOrderData;
import com.example.fypTest.Model.OmsOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping(value = "/test")
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
    @RequestMapping("/orderData")
    public List<ChartOrderData> orderData() {
        List<ChartOrderData> dataList = new LinkedList<>();
        //8周
        for (int i = 0; i < 8; i++) {
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

    public int countWeeksBeforeNow(Instant a) {
        Instant now = Instant.now();
        long daysBetween = ChronoUnit.DAYS.between(a.atZone(ZoneId.systemDefault()), now.atZone(ZoneId.systemDefault()));
        int weeks = (int) (daysBetween / 7);
        return weeks;
    }
}
