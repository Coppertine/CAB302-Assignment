package com.cab302qut.java.util;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.Date;

public class AssetPriceHistoryObj {
    private ObjectProperty<Date> date;
    private DoubleProperty price;

    public Date getDate() {
        return date.get();
    }

    public Double getPrice() {
        return price.get();
    }

    public AssetPriceHistoryObj(Date date, Double price){
        this.date = new SimpleObjectProperty<>(date);
        this.price = new SimpleDoubleProperty(price);
    }

}
