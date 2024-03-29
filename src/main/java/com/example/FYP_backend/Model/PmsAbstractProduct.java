package com.example.FYP_backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "pms_abstract_product")
public class PmsAbstractProduct {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "low_stock")
    private Integer lowStock;

    @Column(name = "to_be_outbound")
    private Integer toBeOutbound;

    @Column(name = "pic")
    private String pic;

    @Column(name = "created_time")
    private Instant createdTime;

    @Column(name = "sale")
    private Integer sale;

    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "is_low", nullable = false)
    private Integer isLow;

    @Column(name = "is_urgent", nullable = false)
    private Integer isUrgent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private PmsProductCategory category;

//    @OneToMany(mappedBy = "abstractProduct")
//    @JsonIgnore
//    private Set<PmsSpecificProduct> pmsSpecificProducts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "abstractProduct")
    private Set<PmsBatch> pmsBatches = new LinkedHashSet<>();

    public Set<PmsBatch> getPmsBatches() {
        return pmsBatches;
    }

    public void setPmsBatches(Set<PmsBatch> pmsBatches) {
        this.pmsBatches = pmsBatches;
    }

//    public Set<PmsSpecificProduct> getPmsSpecificProducts() {
//        return pmsSpecificProducts;
//    }
//
//    public void setPmsSpecificProducts(Set<PmsSpecificProduct> pmsSpecificProducts) {
//        this.pmsSpecificProducts = pmsSpecificProducts;
//    }

    public PmsProductCategory getCategory() {
        return category;
    }

    public void setCategory(PmsProductCategory category) {
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getLowStock() {
        return lowStock;
    }

    public void setLowStock(Integer lowStock) {
        this.lowStock = lowStock;
    }

    public Integer getToBeOutbound() {
        return toBeOutbound;
    }

    public void setToBeOutbound(Integer toBeOutbound) {
        this.toBeOutbound = toBeOutbound;
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

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
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

    public Integer getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(Integer isUrgent) {
        this.isUrgent = isUrgent;
    }

    public PmsAbstractProduct(Integer id) {
        this.id = id;
    }

    public PmsAbstractProduct() {
    }
}
