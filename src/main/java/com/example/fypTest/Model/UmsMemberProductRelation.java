package com.example.fypTest.Model;

import javax.persistence.*;

@Entity
@Table(name = "ums_member_product_relation")
public class UmsMemberProductRelation {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private PmsAbstractProduct product;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PmsAbstractProduct getProduct() {
        return product;
    }

    public void setProduct(PmsAbstractProduct product) {
        this.product = product;
    }

}
