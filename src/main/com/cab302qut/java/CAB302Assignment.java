package com.cab302qut.java;

import com.cab302qut.java.Client.Connection.TradeClient;
import com.cab302qut.java.util.ServerConfiguration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Organisation.Organisation;
import com.cab302qut.java.Organisation.OrganisationException;
import com.cab302qut.java.Trades.Order;
import com.cab302qut.java.Trades.OrderType;
import com.cab302qut.java.Trades.SellOrder;
import com.cab302qut.java.Users.User;
import com.cab302qut.java.util.ServerConfiguration;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class CAB302Assignment extends Application {
    private static ServerConfiguration config;
    private static String[] Args;
    private static final String configFile = "/config.ini";

    public static void main(String[] args) {
        Args = args;

        ServerConfiguration configTemp = new ServerConfiguration();
        try {
            configTemp.reloadConfiguration(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        config=configTemp;
        launch(args);
    }

    public static ServerConfiguration getConfig() {
        return config;
    }

    public static void setConfig(ServerConfiguration config) {
        CAB302Assignment.config = config;
        try {
            config.reloadConfiguration(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static TradeClient tradeClient;

    private static String[] Args;

    public static void main(String[] args) {
        launch(args);
        Args = args;

        PopulateUsers();
        //creates default users.

        Organisation organisation2 = new Organisation("Organisation 2");

        User tradeUser = new User("Ben", organisation2, "BenTrade", "password2", UserType.Default);
        Asset asset1 = new Asset("emojis", 1);
        Asset asset2 = new Asset("CPU", 2);


        User test = new User("t", "Username","password", UserType.Default);
        test.setPassword(test.getPassword().toString());
        //System.out.println(test.getName() + test.getUsername() + test.getPassword() + test.getUserType().toString());
        //arrays of different orders
        ArrayList<Order> sellOrders = new ArrayList<Order>();
        ArrayList<Order> orders = new ArrayList<Order>();
        ArrayList<Order> buyOrders = new ArrayList<Order>();

        Random rnd = new Random();
        //
        for (int i = 0; i < 6; i++) {
            int numberToSell = rnd.nextInt(1000);
            int numberToBuy = rnd.nextInt(1000);
            int price = rnd.nextInt(10);
            Order sellOrder = new Order(asset2, OrderType.SELL, numberToSell, price, tradeUser, null);
            sellOrders.add(sellOrder);

            Order buyOrder = new Order(asset2, OrderType.BUY, numberToBuy, price, mainUser, null);
            buyOrders.add(buyOrder);
        }

        CheckOrders(sellOrders, buyOrders);
    }

    private static void PopulateUsers() {
        Organisation organisation1 = new Organisation("DefaultOrg");
        User mainUser = new User("John", organisation1, "JohnMainUser", "password", UserType.Administrator);
    }

    public static void CheckOrders(ArrayList<Order> sellOrders, ArrayList<Order> buyOrders) {
        for (int i = 0; i < sellOrders.size(); i++) {
            for (int j = 0; j < buyOrders.size(); j++) {
                if (sellOrders.get(i).getTradeAsset().getAssetName() == buyOrders.get(j).getTradeAsset().getAssetName() && sellOrders.get(i).getQuantityToTrade() >= buyOrders.get(j).getQuantityToTrade() && sellOrders.get(i).getPrice() == buyOrders.get(j).getPrice()) {

                    //System.out.println(sellOrders.get(i).getTradeAsset().getAssetName() + " " + sellOrders.get(i).getQuantityToTrade() + " " + sellOrders.get(i).getPrice());
                    //System.out.println(buyOrders.get(j).getTradeAsset().getAssetName() + " " + buyOrders.get(j).getQuantityToTrade() + " " + buyOrders.get(j).getPrice());

                    int buyOrganisationCredits = buyOrders.get(j).getUser().getOrganisation().getCredits();
                    double tradePrice;

                    tradePrice = buyOrders.get(j).getPrice() * buyOrders.get(j).getQuantityToTrade();
                    System.out.println(tradePrice);
                    if (buyOrganisationCredits >= tradePrice) {


                        try {
                            sellOrders.get(i).getUser().getOrganisation().addCredits((int) tradePrice);
                        } catch (OrganisationException e) {
                            e.printStackTrace();
                        }
                        try {
                            buyOrders.get(j).getUser().getOrganisation().removeCredits((int) tradePrice);
                        } catch (OrganisationException e) {
                            e.printStackTrace();
                        }

                        int sellAmount = sellOrders.get(i).getQuantityToTrade() - buyOrders.get(j).getQuantityToTrade();

                        //System.out.println(sellAmount + " The sell amount");
                        System.out.println(sellOrders.get(i).getUser().getOrganisation().getCredits() + " Sellers credits");
                        System.out.println(buyOrders.get(j).getUser().getOrganisation().getCredits() + " buyers credits");

                        if (sellAmount < 0) {
                            //throw error
                        } else {
                            sellOrders.get(i).setQuantityToTrade(sellAmount);
                        }
                    }

                }
            }
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        //System.out.println(getClass().getResource("main.fxml").getPath());
        URL fxmlURL = getClass().getClassLoader().getResource("assetTradeHistory.fxml");
        if (Args.length > 0 && Args[0].equals("-server")) {
            fxmlURL = getClass().getClassLoader().getResource("server.fxml");
        }
        assert fxmlURL != null;
        Parent root = FXMLLoader.load(fxmlURL);

        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("styles/main.css").toExternalForm());
//        TrayNotification tray = new TrayNotification("Hello World", "You got Mail!", NotificationType.INFORMATION);
//        tray.setAnimationType(AnimationType.POPUP);
//        tray.showAndDismiss(Duration.seconds(2));
        Parent root = Args.length >= 1 && Args[0].startsWith("-server") ? FXMLLoader.load(getClass().getClassLoader().getResource("server.fxml")) : FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));

        primaryStage.setTitle("Assignment");
        primaryStage.setScene(scene);
        primaryStage.show();
//        TrayNotification tray = new TrayNotification("Hello World", "You got Mail!",
//                NotificationType.INFORMATION);
//        tray.setAnimationType(AnimationType.POPUP);
//        tray.showAndDismiss(Duration.seconds(2));

    }
}