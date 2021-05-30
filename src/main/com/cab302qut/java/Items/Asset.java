package com.cab302qut.java.Items;

import com.cab302qut.java.Trades.Trade;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class to define an Asset object and the
 *
 * @author Giane
 * */
public class Asset {
    private String assetName;
    private int id;

    private int price;

    public Asset(String assetName, int id,int price) {
        this.assetName = assetName;
        this.id = id;
        this.price = price;
    }

    public String getAssetName() {
        return assetName;
    }
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

}
