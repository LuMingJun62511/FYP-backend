package com.example.FYP_backend.DAO;

import com.example.FYP_backend.Model.OmsReceiptItem;
import com.example.FYP_backend.Model.OmsReceiptItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OmsReceiptItemRepository extends JpaRepository<OmsReceiptItem, OmsReceiptItemId> {
    @Query("SELECT ri FROM OmsReceiptItem ri WHERE ri.receipt.id IN :ReceiptIDs")
    List<OmsReceiptItem> findAllByReceiptIn(List<Integer> ReceiptIDs);

}
