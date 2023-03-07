package com.example.FYP_backend.Model;

import javax.persistence.*;

@Entity
@Table(name = "ums_member_product_relation")
public class UmsMemberProductRelation {
    @EmbeddedId
    private UmsMemberProductRelationId id;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private UmsMember member;

    @MapsId("productId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private PmsAbstractProduct product;

    public UmsMemberProductRelationId getId() {
        return id;
    }

    public void setId(UmsMemberProductRelationId id) {
        this.id = id;
    }

    public UmsMember getMember() {
        return member;
    }

    public void setMember(UmsMember member) {
        this.member = member;
    }

    public PmsAbstractProduct getProduct() {
        return product;
    }

    public void setProduct(PmsAbstractProduct product) {
        this.product = product;
    }

}
