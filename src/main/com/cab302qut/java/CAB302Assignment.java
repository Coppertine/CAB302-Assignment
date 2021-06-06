package com.cab302qut.java;

import com.cab302qut.java.Client.TradeClient;
import com.cab302qut.java.util.Message;
import com.cab302qut.java.util.ServerConfiguration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import com.cab302qut.java.Organisation.OrganisationException;
import com.cab302qut.java.Trades.Order;
import com.cab302qut.java.Users.User;

import javafx.stage.Stage;


import java.util.ArrayList;

public class CAB302Assignment extends Application {
    private static ServerConfiguration config;
    private static String[] Args;
    private static final String configFile =  "E:\\Uni 2021\\CAB302\\src\\main\\resources\\defaultconfig.ini";//String.valueOf(CAB302Assignment.class.getClassLoader().getResource("defaultconfig.ini"));
    private static User mainUser;

    public static TradeClient tradeClient;

    public static Message receivedMsg;

    // For IT Admin to have a single collection for entire program duration
    // instead of repeated server requests
    public static ArrayList<ArrayList<String>> currentOrganisations;

    public static ArrayList<ArrayList<String>> currentOrgsAssets;

    public static Message assetData;

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

    public static void main(String[] args) {
        Args = args;


        ServerConfiguration configTemp = new ServerConfiguration();
        try {
            configTemp.reloadConfiguration(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        config = configTemp;

        launch(args);
    }

    public static void CheckOrders(ArrayList<Order> sellOrders, ArrayList<Order> buyOrders) {
        for (int i = 0; i < sellOrders.size(); i++) {
            for (int j = 0; j < buyOrders.size(); j++) {
                if (sellOrders.get(i).getTradeAsset().getAssetName() == buyOrders.get(j).getTradeAsset().getAssetName() && sellOrders.get(i).getQuantityToTrade() >= buyOrders.get(j).getQuantityToTrade() && sellOrders.get(i).getPrice() == buyOrders.get(j).getPrice()) {

                    Double buyOrganisationCredits = buyOrders.get(j).getUser().getOrganisation().getCredits();
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
        //      TrayNotification tray = new TrayNotification("Hello World", "You got Mail!", NotificationType.INFORMATION);
//       tray.setAnimationType(AnimationType.POPUP);
//        tray.showAndDismiss(Duration.seconds(2));
        Parent root;

        root = Args.length >= 1 && Args[0].startsWith("-server") ? FXMLLoader.load(getClass().getClassLoader().getResource("server.fxml")) : FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));


        Scene scene = new Scene(root);
        primaryStage.setTitle("Assignment");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}