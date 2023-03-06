package com.example.fypTest.Model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "oms_receipt_item")
public class OmsReceiptItem {
    @EmbeddedId
    private OmsReceiptItemId id;

    @MapsId("receiptId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "receipt_id", nullable = false)
    private OmsReceipt receipt;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "status")
    private Integer status;

    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "oms_receipt_id")
    private OmsReceipt omsReceipt;

    public OmsReceipt getOmsReceipt() {
        return omsReceipt;
    }

    public void setOmsReceipt(OmsReceipt omsReceipt) {
        this.omsReceipt = omsReceipt;
    }

    public OmsReceiptItemId getId() {
        return id;
    }

    public void setId(OmsReceiptItemId id) {
        this.id = id;
    }

    public OmsReceipt getReceipt() {
        return receipt;
    }

    public void setReceipt(OmsReceipt receipt) {
        this.receipt = receipt;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}
