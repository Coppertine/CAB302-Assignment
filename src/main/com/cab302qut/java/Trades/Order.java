package com.cab302qut.java.Trades;

import java.util.Date;
import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Users.User;

public class Order {
        private Asset tradeAsset;
        private double pricePerItem;
        private int quantityToTrade;
        private User User;
        private Date tradeDate;
        private OrderType orderType;

        public Order(Asset tradeAsset,OrderType orderType, int quantityToTrade, double pricePerItem, User User, Date tradeDate) {
            this.tradeAsset = tradeAsset;
            this.quantityToTrade = quantityToTrade;
            this.User = User;
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
            return User;
        }

        public void setUser(User User) {
            this.User =User;
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
    }
