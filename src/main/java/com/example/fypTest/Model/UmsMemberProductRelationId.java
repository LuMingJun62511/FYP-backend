package com.example.fypTest.Model;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UmsMemberProductRelationId implements Serializable {
    private static final long serialVersionUID = 7302753311155499714L;
    @Column(name = "member_id", nullable = false)
    private Integer memberId;

    @Column(name = "product_id", nullable = false)
    private Integer productId;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UmsMemberProductRelationId entity = (UmsMemberProductRelationId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.memberId, entity.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, memberId);
    }

}
