package com.example.FYP_backend.DAO;

import com.example.FYP_backend.Model.OmsReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OmsReceiptRepository extends JpaRepository<OmsReceipt, Integer> {
    List<OmsReceipt> findAllByStatus(Integer status);
}
