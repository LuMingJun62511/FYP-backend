package com.example.FYP_backend.Model;

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

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private PmsAbstractProduct product;

    @MapsId("batchId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "batch_id", nullable = false)
    private PmsBatch batch;

    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "status")
    private Integer status;

    public OmsReceiptItem(OmsReceiptItemId id, OmsReceipt receipt, PmsAbstractProduct product, PmsBatch batch, BigDecimal totalPrice, Integer amount, Integer status) {
        this.id = id;
        this.receipt = receipt;
        this.product = product;
        this.batch = batch;
        this.totalPrice = totalPrice;
        this.amount = amount;
        this.status = status;
    }

    public OmsReceiptItem() {
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

    public PmsAbstractProduct getProduct() {
        return product;
    }

    public void setProduct(PmsAbstractProduct product) {
        this.product = product;
    }

    public PmsBatch getBatch() {
        return batch;
    }

    public void setBatch(PmsBatch batch) {
        this.batch = batch;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
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

}
