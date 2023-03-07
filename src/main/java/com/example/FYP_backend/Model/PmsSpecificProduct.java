package com.example.FYP_backend.Model;

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
    @JsonIgnore
    private PmsAbstractProduct abstractProduct;

//    相比之前，这是新加的
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "batch_id", nullable = false)
    private PmsBatch batch;

    public PmsBatch getBatch() {
        return batch;
    }

    public void setBatch(PmsBatch batch) {
        this.batch = batch;
    }

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
