package com.example.fypTest.Model;

import javax.persistence.*;

@Entity
@Table(name = "pms_product_category")
public class PmsProductCategory {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 64)
    private String name;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "level")
    private Integer level;

    @Column(name = "product_count")
    private Integer productCount;

    @Column(name = "icon")
    private String icon;

    @Lob
    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
