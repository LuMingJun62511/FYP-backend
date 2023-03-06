package com.example.fypTest.DAO;

import com.example.fypTest.Model.OmsReceipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OmsReceiptRepository extends JpaRepository<OmsReceipt, Integer> {
    List<OmsReceipt> findAllByStatus(Integer status);
}
