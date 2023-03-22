package com.example.FYP_backend.DAO;

import com.example.FYP_backend.Model.OmsReceiptItem;
import com.example.FYP_backend.Model.OmsReceiptItemId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OmsReceiptItemRepository extends JpaRepository<OmsReceiptItem, OmsReceiptItemId> {
    List<OmsReceiptItem> findById_ReceiptIdEquals(Integer receiptId);

    OmsReceiptItem findByIdEquals(OmsReceiptItemId RIID);
}
