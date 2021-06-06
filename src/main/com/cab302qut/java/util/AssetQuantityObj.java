package com.cab302qut.java.util;


import javafx.beans.property.*;

import java.io.Serializable;
import java.sql.Date;

public class AssetQuantityObj implements Serializable {
    private SimpleStringProperty assetType;
    private IntegerProperty quantity;

    public String getAssetType() {
        return assetType.get();
    }

    public Integer getQuantity() {
        return quantity.get();
    }

    public AssetQuantityObj(String assetType, Integer price){
        this.assetType = new SimpleStringProperty(assetType);
        this.quantity = new SimpleIntegerProperty(price);
    }

}
