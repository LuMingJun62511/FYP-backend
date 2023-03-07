package com.example.FYP_backend.DAO;

import com.example.FYP_backend.Model.OmsOrderItem;
import com.example.FYP_backend.Model.OmsOrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OmsOrderItemRepository extends JpaRepository<OmsOrderItem, OmsOrderItemId> {
    List<OmsOrderItem> findByOrder_Id(Integer id);

}
