package com.cab302qut.java.Trades;

import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Organisation.OrganisationAsset;
import com.cab302qut.java.Users.User;

import java.util.Date;

/**
 * A transaction between a single or multiple {@link User} objects
 * across two {@link com.cab302qut.java.Organisation.Organisation}
 * over a specific {@link Asset}
 *
 * @author Nicholas Bishop
 */

public class Trade {
    private Asset tradeAsset;
    private float pricePerItem;
    private int quantityToTrade;
    private User sellingUser;
    private User buyingUser;
    private Date tradeDate;
    private TradeType tradeType;

    /**
     * @param tradeAsset
     * @param sellingUser
     * @param buyingUser
     * @param tradeDate
     * @param tradeType
     * @param pricePerItem
     * @param quantityToTrade
     */
    public Trade(Asset tradeAsset, int quantityToTrade, float pricePerItem, User sellingUser, User buyingUser, Date tradeDate, TradeType tradeType) {
        this.tradeAsset = tradeAsset;
        this.quantityToTrade = quantityToTrade;
        this.sellingUser = sellingUser;
        this.buyingUser = buyingUser;
        this.tradeDate = tradeDate;
        this.tradeType = tradeType;
        this.pricePerItem = pricePerItem;
    }

    public Trade(Asset tradeAsset, User sellingUser, int quantityToTrade, float pricePerItem) {
        this(tradeAsset,quantityToTrade, pricePerItem, sellingUser, null, null, TradeType.OPEN);
    }

    /**
     * Get's the current asset in this trade.
     *
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

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    /**
     * Sets the status of the current trade.
     *
     * @param tradeType The status of the current trade.
     * @throws TradeException If a closed trade is attempted to be opened.
     */
    public void setTradeType(TradeType tradeType) throws TradeException {
        if (this.tradeType == TradeType.CLOSED && tradeType == TradeType.OPEN)
            throw new TradeException("Unable to open a closed trade.");
        this.tradeType = tradeType;
    }

    public Object getIndividualPrice() {
        return null;
    }

    public float getPrice() {
        return pricePerItem;
    }

    public void setPrice(float price) {
        this.pricePerItem = price;
    }

    public int getQuantityToTrade(){
return quantityToTrade;
    }

    public void setQuantityToTrade(){
        this.quantityToTrade = quantityToTrade;
    }
}
