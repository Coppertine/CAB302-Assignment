package com.cab302qut.java.Trades;

import java.util.Date;
import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Users.User;

public class BuyOrder {
        private Asset tradeAsset;
        private float pricePerItem;
        private int quantityToTrade;
        private User sellingUser;
        private Date tradeDate;

        public BuyOrder(Asset tradeAsset, int quantityToTrade, float pricePerItem, User sellingUser, Date tradeDate) {
            this.tradeAsset = tradeAsset;
            this.quantityToTrade = quantityToTrade;
            this.sellingUser = sellingUser;
            this.tradeDate = tradeDate;
            this.pricePerItem = pricePerItem;
        }

        public Asset getTradeAsset() {
            return tradeAsset;
        }

        public void setTradeAsset(Asset tradeAsset){
            this.tradeAsset = tradeAsset;
        }

        public User getSellingUser() {
            return sellingUser;
        }

        public void setSellingUser(User sellingUser) {
            this.sellingUser = sellingUser;
        }

        public Date getTradeDate() {
            return tradeDate;
        }

        public void setTradeDate(Date tradeDate) {
            this.tradeDate = tradeDate;
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

        public void setQuantityToTrade(int quantityToTrade){
            this.quantityToTrade = quantityToTrade;
        }
    }
