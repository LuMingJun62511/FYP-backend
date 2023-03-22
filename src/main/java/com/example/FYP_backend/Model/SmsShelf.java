package com.example.FYP_backend.Model;

import javax.persistence.*;

@Entity
@Table(name = "sms_shelf")
public class SmsShelf {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "row_num")
    private Integer rowNum;

    @Column(name = "col_num")
    private Integer colNum;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "note", length = 200)
    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Integer getColNum() {
        return colNum;
    }

    public void setColNum(Integer colNum) {
        this.colNum = colNum;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
