package com.cab302qut.java.Items;

import com.cab302qut.java.Trades.Trade;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class to define an Asset object and the
 * specific asset's details
 *
 * @author Giane
 * */
public class Asset {
    private String assetName;
    private int id; // TODO: assuming asset ID is unique, implement measures before
                    //       instantiation process to ensure uniqueness
    private int price;

    /**
     * No default constructor to enforce a valid asset is being made
     * */
    public Asset(String assetName, int id, int price) {
        this.assetName = assetName;
        this.id = id;
        this.price = price;
    }

    public int getAssetPrice() {
        return this.price;
    }
    public String getAssetName() {
        return assetName;
    }
    public int getAssetId() {
        return id;
    }
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

}
