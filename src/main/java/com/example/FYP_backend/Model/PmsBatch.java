package com.example.FYP_backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "pms_batch")
public class PmsBatch {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "BBD")
    private Instant bbd;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "abstract_product_id", nullable = false)
    @JsonIgnore
    private PmsAbstractProduct abstractProduct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Instant getBbd() {
        return bbd;
    }

    public void setBbd(Instant bbd) {
        this.bbd = bbd;
    }

    public PmsAbstractProduct getAbstractProduct() {
        return abstractProduct;
    }

    public void setAbstractProduct(PmsAbstractProduct abstractProduct) {
        this.abstractProduct = abstractProduct;
    }

}
