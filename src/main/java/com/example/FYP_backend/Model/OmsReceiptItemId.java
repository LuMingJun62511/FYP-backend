package com.example.FYP_backend.Model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OmsReceiptItemId implements Serializable {
    private static final long serialVersionUID = 5502610651366363301L;
    @Column(name = "receipt_id", nullable = false)
    private Integer receiptId;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    @Column(name = "batch_id", nullable = false)
    private Integer batchId;

    public Integer getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OmsReceiptItemId entity = (OmsReceiptItemId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.batchId, entity.batchId) &&
                Objects.equals(this.receiptId, entity.receiptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, batchId, receiptId);
    }

    public OmsReceiptItemId(Integer receiptId, Integer productId, Integer batchId) {
        this.receiptId = receiptId;
        this.productId = productId;
        this.batchId = batchId;
    }

    public OmsReceiptItemId() {
    }
}
