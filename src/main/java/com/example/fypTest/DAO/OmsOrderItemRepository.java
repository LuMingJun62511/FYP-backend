package com.example.fypTest.DAO;

import com.example.fypTest.Model.OmsOrderItem;
import com.example.fypTest.Model.OmsOrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OmsOrderItemRepository extends JpaRepository<OmsOrderItem, OmsOrderItemId> {
    List<OmsOrderItem> findByOrder_Id(Integer id);

}
