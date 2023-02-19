package com.example.fypTest.Model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SmsShelfItemId implements Serializable {
    private static final long serialVersionUID = -5545783383176647935L;
    @Column(name = "shelf_id", nullable = false)
    private Integer shelfId;

    @Column(name = "abstract_product_id", nullable = false)
    private Integer abstractProductId;

    public Integer getShelfId() {
        return shelfId;
    }

    public void setShelfId(Integer shelfId) {
        this.shelfId = shelfId;
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
        SmsShelfItemId entity = (SmsShelfItemId) o;
        return Objects.equals(this.shelfId, entity.shelfId) &&
                Objects.equals(this.abstractProductId, entity.abstractProductId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shelfId, abstractProductId);
    }

}
