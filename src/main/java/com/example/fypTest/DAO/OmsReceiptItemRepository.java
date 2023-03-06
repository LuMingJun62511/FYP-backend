package com.example.fypTest.DAO;

import com.example.fypTest.Model.OmsReceiptItem;
import com.example.fypTest.Model.OmsReceiptItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OmsReceiptItemRepository extends JpaRepository<OmsReceiptItem, OmsReceiptItemId> {
}
