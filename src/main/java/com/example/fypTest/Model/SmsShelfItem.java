package com.example.fypTest.Model;

import javax.persistence.*;

@Entity
@Table(name = "sms_shelf_items")
public class SmsShelfItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shelf_id", nullable = false)
    private SmsShelf shelf;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "abstract_product_id")
    private PmsAbstractProduct abstractProduct;

    @Column(name = "position_row")
    private Integer positionRow;

    @Column(name = "position_column")
    private Integer positionColumn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

}
