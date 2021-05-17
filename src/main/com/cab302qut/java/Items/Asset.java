package com.cab302qut.java.Items;

import com.cab302qut.java.Trades.Trade;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

public class Asset {
    private String assetName;
    private Integer id;
    private Integer price;
    private ArrayList<Trade> tradeHistory;

    public Asset(String assetName, Integer id, Integer price) {
        this.assetName = assetName;
        this.id = id;
        this.price = price;
    }

    public Asset() {

    }

    public Asset(String assetName) {
        this.assetName = assetName;
    }

    public ArrayList<Trade> getTradeHistory() {
        return tradeHistory;
    }

    public Integer getAssetPrice() {
        return this.price;
    }

    public String getAssetName() {
        return assetName;
    }
    public Integer getAssetId() {
        return id;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public void addTrade(Trade trade) {

    }


}
