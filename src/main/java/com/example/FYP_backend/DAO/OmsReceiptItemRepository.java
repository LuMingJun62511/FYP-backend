package com.example.FYP_backend.DAO;

import com.example.FYP_backend.Model.OmsReceiptItem;
import com.example.FYP_backend.Model.OmsReceiptItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OmsReceiptItemRepository extends JpaRepository<OmsReceiptItem, OmsReceiptItemId> {
}
