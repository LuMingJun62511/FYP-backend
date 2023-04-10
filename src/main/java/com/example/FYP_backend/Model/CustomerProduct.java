package com.example.FYP_backend.Model;

import java.math.BigDecimal;
import java.time.Instant;


public class CustomerProduct {
    private Integer id;

    private BigDecimal price;

    private Integer sale;

    private String pic;

    private Instant createdTime;

    private String name;

    private Integer isLow;

    private PmsProductCategory category;

    public CustomerProduct() {
    }

    public CustomerProduct(Integer id, BigDecimal price, Integer sale, String pic, Instant createdTime, String name, Integer isLow, PmsProductCategory category) {
        this.id = id;
        this.price = price;
        this.sale = sale;
        this.pic = pic;
        this.createdTime = createdTime;
        this.name = name;
        this.isLow = isLow;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsLow() {
        return isLow;
    }

    public void setIsLow(Integer isLow) {
        this.isLow = isLow;
    }

    public PmsProductCategory getCategory() {
        return category;
    }

    public void setCategory(PmsProductCategory category) {
        this.category = category;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }
}
