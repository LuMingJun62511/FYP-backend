package com.example.FYP_backend.DAO;

import com.example.FYP_backend.Model.OmsOrder;
import com.example.FYP_backend.Model.OmsReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OmsReceiptRepository extends JpaRepository<OmsReceipt, Integer> {
    List<OmsReceipt> findAllByStatusEquals(Integer status);

    OmsReceipt findOmsReceiptByIdEquals(Integer status);

    @Query("SELECT r FROM OmsReceipt r WHERE r.id IN :ids")
    List<OmsReceipt> findAllByIdIn(@Param("ids") List<Integer> ids);
}
