package com.example.fypTest.Model;

import javax.persistence.Entity;
import java.math.BigDecimal;

public class ChartOrderData {
    private int week;
    private double sale;
    private int amount;

    public ChartOrderData(int week, double sale, int amount) {
        this.week = week;
        this.sale = sale;
        this.amount = amount;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
