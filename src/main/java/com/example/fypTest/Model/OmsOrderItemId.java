package com.example.fypTest.Model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OmsOrderItemId implements Serializable {
    private static final long serialVersionUID = -5582023193672943378L;
    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @Column(name = "abstract_product_id", nullable = false)
    private Integer abstractProductId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getAbstractProductId() {
        return abstractProductId;
    }

    public void setAbstractProductId(Integer abstractProductId) {
        this.abstractProductId = abstractProductId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OmsOrderItemId entity = (OmsOrderItemId) o;
        return Objects.equals(this.orderId, entity.orderId) &&
                Objects.equals(this.abstractProductId, entity.abstractProductId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, abstractProductId);
    }

}
