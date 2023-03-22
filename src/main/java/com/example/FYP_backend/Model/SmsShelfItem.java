package com.example.FYP_backend.Model;

import javax.persistence.*;

@Entity
@Table(name = "sms_shelf_items")
public class SmsShelfItem {
    @EmbeddedId
    private SmsShelfItemId id;

    @MapsId("shelfId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shelf_id", nullable = false)
    private SmsShelf shelf;

    @MapsId("abstractProductId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "abstract_product_id", nullable = false)
    private PmsAbstractProduct abstractProduct;

    @Column(name = "position_row")
    private Integer positionRow;

    @Column(name = "position_column")
    private Integer positionColumn;

    public SmsShelfItemId getId() {
        return id;
    }

    public void setId(SmsShelfItemId id) {
        this.id = id;
    }

    public SmsShelf getShelf() {
        return shelf;
    }

    public void setShelf(SmsShelf shelf) {
        this.shelf = shelf;
    }

    public PmsAbstractProduct getAbstractProduct() {
        return abstractProduct;
    }

    public void setAbstractProduct(PmsAbstractProduct abstractProduct) {
        this.abstractProduct = abstractProduct;
    }

    public Integer getPositionRow() {
        return positionRow;
    }

    public void setPositionRow(Integer positionRow) {
        this.positionRow = positionRow;
    }

    public Integer getPositionColumn() {
        return positionColumn;
    }

    public void setPositionColumn(Integer positionColumn) {
        this.positionColumn = positionColumn;
    }

    public SmsShelfItem() {
    }

    public SmsShelfItem(SmsShelfItemId id, SmsShelf shelf, PmsAbstractProduct abstractProduct, Integer positionRow, Integer positionColumn) {
        this.id = id;
        this.shelf = shelf;
        this.abstractProduct = abstractProduct;
        this.positionRow = positionRow;
        this.positionColumn = positionColumn;
    }
}
