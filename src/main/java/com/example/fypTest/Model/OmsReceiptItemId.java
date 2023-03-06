package com.example.fypTest.Model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OmsReceiptItemId implements Serializable {
    private static final long serialVersionUID = -5731138998435687611L;
    @Column(name = "receipt_id", nullable = false)
    private Integer receiptId;

    @Column(name = "specific_product_id", nullable = false)
    private Integer specificProductId;

    public Integer getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
    }

    public Integer getSpecificProductId() {
        return specificProductId;
    }

    public void setSpecificProductId(Integer specificProductId) {
        this.specificProductId = specificProductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OmsReceiptItemId entity = (OmsReceiptItemId) o;
        return Objects.equals(this.specificProductId, entity.specificProductId) &&
                Objects.equals(this.receiptId, entity.receiptId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(specificProductId, receiptId);
    }

}
