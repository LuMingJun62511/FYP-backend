package com.example.fypTest.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "pms_specific_product")
public class PmsSpecificProduct {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "abstract_product_id", nullable = false)
    @JsonIgnore      //所以，在find得实例的过程中，具体商品实例里就不会再有abstractProduct对象了
    private PmsAbstractProduct abstractProduct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PmsAbstractProduct getAbstractProduct() {
        return abstractProduct;
    }

    public void setAbstractProduct(PmsAbstractProduct abstractProduct) {
        this.abstractProduct = abstractProduct;
    }

}
