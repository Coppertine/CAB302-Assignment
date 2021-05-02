package com.cab302qut.java;

import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Organisation.Organisation;
import com.cab302qut.java.Trades.BuyOrder;
import com.cab302qut.java.Trades.SellOrder;
import com.cab302qut.java.Trades.Trade;
import com.cab302qut.java.Trades.TradeType;
import com.cab302qut.java.Users.User;

import javafx.application.Application;
import javafx.stage.Stage;
import main.com.cab302qut.java.Users.UserType;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.*;

public class CAB302Assignment { //extends Application {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        //launch(args);
        User mainUser = new User("John", "JohnMainUser", "password", UserType.Administrator);
        User tradeUser = new User("Ben", "BenTrade", "password2", UserType.Default);
        Asset asset1 = new Asset("emojis", 1, 10);
        Asset asset2 = new Asset("CPU", 2, 5);

        ArrayList<SellOrder> sellOrders = new ArrayList<SellOrder>();
        ArrayList<BuyOrder> buyOrders = new ArrayList<BuyOrder>();
        //ArrayList<Trade> trades = new ArrayList<Trade>();

        Organisation organisation1 = new Organisation("Organisation 1");
        organisation1.addUser(mainUser);
        mainUser.setOrganisation(organisation1);
        organisation1.addCredits(10000);

        System.out.println(organisation1.getCredits() + organisation1.getName());

        Organisation organisation2 = new Organisation("Organisation 2");
        organisation2.addUser(mainUser);
        mainUser.setOrganisation(organisation2);
        organisation2.addCredits(20000);
        System.out.println(organisation2.getCredits() + organisation2.getName());


        System.out.println("create a new user");

        System.out.println("name");
        String name = "john";//scanner.nextLine();
        System.out.println("username");
        String username = "smith";//scanner.nextLine();
        System.out.println("password");
        String password = "password";//scanner.nextLine();

        User createUser = new User(name, username, password, UserType.Default);
        createUser.setPassword(password);

        System.out.println(createUser.getName() + createUser.getUsername() + createUser.getPassword() + createUser.getUserType().toString());

        SellOrder sellOrder = new SellOrder(asset1, 100, 10, createUser, null);
        sellOrders.add(sellOrder);

        SellOrder sellOrder3 = new SellOrder(asset2, 1000, 1, tradeUser, null);
        sellOrders.add(sellOrder3);

        Random rnd = new Random();


        for (int i = 0; i < 6; i++) {
            int numberToSell = rnd.nextInt(1000);
            int numberToBuy = rnd.nextInt(1000);
            int price = rnd.nextInt(10);
            SellOrder sellorder = new SellOrder(asset2, numberToSell, price, tradeUser, null);
            sellOrders.add(sellorder);

            BuyOrder buyOrder = new BuyOrder(asset2, numberToBuy, price, tradeUser, null);
            buyOrders.add(buyOrder);

        }

        //System.out.println(sellOrders.get(0).getQuantityToTrade());
        for (int i = 0; i < sellOrders.size(); i++) {
            System.out.println(sellOrders.get(i).getQuantityToTrade() + " " + sellOrders.get(i).getTradeAsset().getAssetName());
        }

        CheckOrders(sellOrders, buyOrders);
    }

    public static void CheckOrders(ArrayList<SellOrder> sellOrders, ArrayList<BuyOrder> buyOrders) {
        for (int i = 0; i < sellOrders.size(); i++) {
            for (int j = 0; j < buyOrders.size(); j++) {
                if (sellOrders.get(i).getTradeAsset().getAssetName() == buyOrders.get(j).getTradeAsset().getAssetName() && sellOrders.get(i).getQuantityToTrade() >= buyOrders.get(j).getQuantityToTrade() && sellOrders.get(i).getPrice() >= buyOrders.get(j).getPrice()) {

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


