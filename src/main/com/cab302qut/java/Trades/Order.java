package com.cab302qut.java.Trades;

import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Users.User;

import java.util.Date;

public class Order {
    private Asset tradeAsset;
    private double pricePerItem;
    private int quantityToTrade;
    private User user;
    private Date tradeDate;
    private OrderType orderType;

    public Order(Asset tradeAsset,OrderType orderType, int quantityToTrade, double pricePerItem, User User, Date tradeDate) {
        this.tradeAsset = tradeAsset;
        this.quantityToTrade = quantityToTrade;
        this.user = User;
        this.tradeDate = tradeDate;
        this.pricePerItem = pricePerItem;
        this.orderType = orderType;
    }

    public Asset getTradeAsset() {
        return tradeAsset;
    }

    public void setTradeAsset(Asset tradeAsset){
        this.tradeAsset = tradeAsset;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User User) {
        this.user = User;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public double getPrice() {
        return pricePerItem;
    }

    public void setPrice(float price) {
        this.pricePerItem = price;
    }

    public int getQuantityToTrade(){
        return quantityToTrade;
    }

    public void setQuantityToTrade(int quantityToTrade){
        this.quantityToTrade = quantityToTrade;
    }

    public OrderType getOrderType(){
        return orderType;
    }

    public void SetOrderType(OrderType orderType){
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "Order:" +
                tradeAsset+"," +
                pricePerItem +"," +
                quantityToTrade +"," +
                user +"," +
                tradeDate+","  +
                orderType;
    }
}
