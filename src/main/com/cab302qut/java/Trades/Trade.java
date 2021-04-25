package com.cab302qut.java.Trades;

import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Organisation.OrganisationAsset;
import com.cab302qut.java.Users.User;

import java.util.Date;

public class Trade {
    private Asset tradeAsset;
    private User sellingUser;
    private User buyingUser;
    private Date tradeDate;
    private TradeType tradeType;

    /**
     * Get's the current asset in this trade.
     * @return The asset being traded.
     */
    public Asset getTradeAsset() {
        return tradeAsset;
    }

    public void setTradeAsset(Asset tradeAsset) {
        this.tradeAsset = tradeAsset;
    }

    public User getSellingUser() {
        return sellingUser;
    }

    public void setSellingUser(User sellingUser) {
        this.sellingUser = sellingUser;
    }

    public User getBuyingUser() {
        return buyingUser;
    }

    public void setBuyingUser(User buyingUser) {
        this.buyingUser = buyingUser;
    }
}
