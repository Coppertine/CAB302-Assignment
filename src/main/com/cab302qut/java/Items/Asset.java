package com.cab302qut.java.Items;

import com.cab302qut.java.Trades.Trade;

import java.sql.Date;
import java.util.ArrayList;

public class Asset {
    private String assetName;
    private ArrayList<Trade> tradeHistory;

    public Asset(String assetName) {
        this.assetName = assetName;
    }

    public Asset() {

    }

    public ArrayList<Trade> getTradeHistory() {
        return tradeHistory;
    }

    public double getAssetPrice() {
        return 0;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public void addTrade(Trade trade) {

    }


}
