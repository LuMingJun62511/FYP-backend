package com.example.FYP_backend.Model;

import java.time.Instant;

public class ChartProductData {
    private Instant weekBeginning;

    private int week;
    private int productID;
    private int productName;
    private int amount;
    private int sale;

    public Instant getWeekBeginning() {
        return weekBeginning;
    }

    public void setWeekBeginning(Instant weekBeginning) {
        this.weekBeginning = weekBeginning;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getProductName() {
        return productName;
    }

    public void setProductName(int productName) {
        this.productName = productName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public ChartProductData(int week, int amount) {
        this.week = week;
        this.amount = amount;
    }
}
