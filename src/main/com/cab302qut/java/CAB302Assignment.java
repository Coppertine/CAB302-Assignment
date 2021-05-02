package com.cab302qut.java;

import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Organisation.Organisation;
import com.cab302qut.java.Trades.Order;
import com.cab302qut.java.Trades.OrderType;
import com.cab302qut.java.Trades.SellOrder;
import com.cab302qut.java.Users.User;

import com.cab302qut.java.Users.UserType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class CAB302Assignment { //extends Application {

    public static void main(String[] args){
        //launch(args);
        //creates default users.
        User mainUser = new User("John", "JohnMainUser", "password", UserType.Administrator);
        User tradeUser = new User("Ben", "BenTrade", "password2", UserType.Default);
        Asset asset1 = new Asset("emojis", 1, 10);
        Asset asset2 = new Asset("CPU", 2, 5);

        //arrays of different orders
        ArrayList<Order> sellOrders = new ArrayList<Order>();
        ArrayList<Order> orders = new ArrayList<Order>();
        ArrayList<Order> buyOrders = new ArrayList<Order>();

        //creates 2 different organisations.
        Organisation organisation1 = new Organisation("Organisation 1");
        organisation1.addUser(mainUser);
        mainUser.setOrganisation(organisation1);
        organisation1.addCredits(10000);
        //organisation1.addAsset(asset1);

        Organisation organisation2 = new Organisation("Organisation 2");
        organisation2.addUser(mainUser);
        mainUser.setOrganisation(organisation2);
        organisation2.addCredits(20000);
        //organisation2.addAsset(asset2);

        Random rnd = new Random();

        //
        for (int i = 0; i < 6; i++) {
            int numberToSell = rnd.nextInt(1000);
            int numberToBuy = rnd.nextInt(1000);
            int price = rnd.nextInt(10);
            Order sellOrder = new Order(asset2, OrderType.SELL, numberToSell, price, tradeUser, null);
            sellOrders.add(sellOrder);

            Order buyOrder = new Order(asset2, OrderType.BUY, numberToBuy, price, tradeUser, null);
            buyOrders.add(buyOrder);
        }

        CheckOrders(sellOrders,buyOrders);
    }

    public static void CheckOrders(ArrayList<Order> sellOrders,ArrayList<Order> buyOrders) {
        for (int i = 0; i < sellOrders.size(); i++) {
            for (int j = 0; j < buyOrders.size(); j++) {
                if (sellOrders.get(i).getTradeAsset().getAssetName() == buyOrders.get(j).getTradeAsset().getAssetName() && sellOrders.get(i).getQuantityToTrade() >= buyOrders.get(j).getQuantityToTrade() && sellOrders.get(i).getPrice() == buyOrders.get(j).getPrice()) {
                    System.out.println(sellOrders.get(i).getTradeAsset().getAssetName() + " " + sellOrders.get(i).getQuantityToTrade() + " " + sellOrders.get(i).getPrice());
                    System.out.println(buyOrders.get(j).getTradeAsset().getAssetName() + " " + buyOrders.get(j).getQuantityToTrade() + " " + buyOrders.get(j).getPrice());
                    int sellAmount = sellOrders.get(i).getQuantityToTrade() - buyOrders.get(j).getQuantityToTrade();
                    System.out.println(sellAmount + "The sell amount");
                    if (sellAmount < 0) {
                        //throw error
                    } else {
                        sellOrders.get(i).setQuantityToTrade(sellAmount);
                    }
                }
            }
        }
    }

/*
    @Override
    public void start(Stage primaryStage) throws Exception {

    }
*/
}