package com.example.FYP_backend.Controller;

import com.example.FYP_backend.DAO.OmsOrderItemRepository;
import com.example.FYP_backend.DAO.OmsOrderRepository;
import com.example.FYP_backend.DAO.OmsReceiptRepository;
import com.example.FYP_backend.Model.OmsOrder;

import com.example.FYP_backend.Model.OmsOrderItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

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

    @Resource
    OmsOrderRepository orderRepo;
    @Resource
    OmsOrderItemRepository orderItemRepo;
    @Resource
    OmsReceiptRepository receiptRepo;

    @RequestMapping(value = "/ordersThisWeek")
    public List<OmsOrder> getOrdersThisWeek(){
        return orderRepo.findAllByCreateTimeThisWeek(startOfWeekInstant);
    }
    @RequestMapping(value = "/orderItems/{orderID}")
    public List<OmsOrderItem> getItemsById(@PathVariable(value = "orderID") int orderID){
        return orderItemRepo.findByOrder_Id(orderID);
    }
//    @RequestMapping(value = "/receipts")
//    public List<OmsReceipt> getReceiptsByStatus(){
//        return receiptRepo.findAllByStatus(1);
//    }
//
//    @RequestMapping(value = "/testOrdres")
//    public List<OmsOrder> getOrder(){
//        return orderRepo.findAll();
//    }
//
//    @RequestMapping(value = "/testOrdreItems")
//    public List<OmsOrderItem> getOrderItem(){
//        return orderItemRepo.findAll();
//    }


//    第一步，本周的订单，有哪些，
//    这步对应的处理订单也简单，就是在货物的待出货上加个数


//    第二步，有问题的订单，有哪些
//    这是先行判断的，如果，有需求
//

//    第三步，处理好的订单，进行数据擦屁股，订单处理后，就生成receipt了，
//    销售出去的商品，统计的数据就自动加到销售记录里了，

//    只管杀不管埋，处理订单我就先这样有去无回了
}
