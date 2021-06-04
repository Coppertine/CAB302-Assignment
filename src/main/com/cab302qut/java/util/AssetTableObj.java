package com.cab302qut.java.util;

import java.io.Serializable;
import java.sql.Date;

public class AssetTableObj implements Serializable {
    private Date date;
    private Double price;

    public AssetTableObj(Date date, Double price) {
        this.date = date;
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public Double getPrice() {
        return price;
    }
}
