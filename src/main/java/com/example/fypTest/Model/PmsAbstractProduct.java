package com.example.fypTest.Model;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private PmsProductCategory category;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "low_stock")
    private Integer lowStock;

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

    @OneToMany(mappedBy = "abstractProduct")
    private Set<PmsSpecificProduct> pmsSpecificProducts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "abstractProduct")
    private Set<PmsBatch> pmsBatches = new LinkedHashSet<>();

    @OneToMany(mappedBy = "abstractProduct")
    @JsonIgnore      //所以，在find得实例的过程中，抽象商品里就不会有在货架位置的信息了
    private Set<SmsShelfItem> smsShelfItems = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    @JsonIgnore      //所以，在find得实例的过程中，抽象商品里就不会有它被哪个用户收藏的信息了
    private Set<UmsMemberProductRelation> umsMemberProductRelations = new LinkedHashSet<>();

    public Set<UmsMemberProductRelation> getUmsMemberProductRelations() {
        return umsMemberProductRelations;
    }

    public void setUmsMemberProductRelations(Set<UmsMemberProductRelation> umsMemberProductRelations) {
        this.umsMemberProductRelations = umsMemberProductRelations;
    }

    public Set<SmsShelfItem> getSmsShelfItems() {
        return smsShelfItems;
    }

    public void setSmsShelfItems(Set<SmsShelfItem> smsShelfItems) {
        this.smsShelfItems = smsShelfItems;
    }

    public Set<PmsBatch> getPmsBatches() {
        return pmsBatches;
    }

    public void setPmsBatches(Set<PmsBatch> pmsBatches) {
        this.pmsBatches = pmsBatches;
    }

    public Set<PmsSpecificProduct> getPmsSpecificProducts() {
        return pmsSpecificProducts;
    }

    public void setPmsSpecificProducts(Set<PmsSpecificProduct> pmsSpecificProducts) {
        this.pmsSpecificProducts = pmsSpecificProducts;
    }

    public Integer getIsUrgent() {
        return isUrgent;
    }

    public void setIsUrgent(Integer isUrgent) {
        this.isUrgent = isUrgent;
    }

    public Integer getIsLow() {
        return isLow;
    }

    public void setIsLow(Integer isLow) {
        this.isLow = isLow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PmsProductCategory getCategory() {
        return category;
    }

    public void setCategory(PmsProductCategory category) {
        this.category = category;
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

}
