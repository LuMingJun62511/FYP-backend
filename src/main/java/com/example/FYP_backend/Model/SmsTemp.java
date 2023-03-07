package com.example.FYP_backend.Model;

import java.util.Date;

public class SmsTemp {
    private int id;

    private int sales;

    private int urgent;

    private Date created;

    public int getUrgent() {
        return this.urgent;
    }

    public void setUrgent(int urgent) {
        this.urgent = urgent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }


    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
