package com.example.fypTest.DAO;

import com.example.fypTest.Model.ChartOrderData;
import com.example.fypTest.Model.OmsOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface OmsOrderRepository extends JpaRepository<OmsOrder, Integer> {
    @Query("SELECT o FROM OmsOrder o WHERE o.createTime >= :eightSettlementDateAgo")
    List<OmsOrder> findAllByCreateTimeTwoMonthAgo(@Param("eightSettlementDateAgo") Instant eightSettlementDateAgo);
    //找到了8个结算日之前的数据了，因为他不支持自动转化成我想要的ChartOrderData类型，所以，我就只能手动sum和count了，

    @Query("SELECT o FROM OmsOrder o WHERE o.createTime >= :oneSettlementDateAgo")
    List<OmsOrder> findAllByCreateTimeThisWeek(@Param("oneSettlementDateAgo") Instant oneSettlementDateAgo);

}
