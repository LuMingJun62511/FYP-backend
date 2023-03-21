package com.example.FYP_backend.Model;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "pms_abstract_product_statistic")
public class PmsAbstractProductStatistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private PmsAbstractProduct product;

    @Column(name = "sale_time")
    private Instant saleTime;

    @Column(name = "amount")
    private Integer amount;

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

    public Instant getSaleTime() {
        return saleTime;
    }

    public void setSaleTime(Instant saleTime) {
        this.saleTime = saleTime;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}
