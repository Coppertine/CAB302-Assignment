package com.cab302qut.java;

import com.cab302qut.java.Client.Connection.TradeClient;
import com.cab302qut.java.util.Message;
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
import com.cab302qut.java.Users.UserType;

import javafx.event.EventHandler;
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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
public class CAB302Assignment extends Application {
    private static ServerConfiguration config;
    private static String[] Args;
    private static final String configFile = String.valueOf(CAB302Assignment.class.getClassLoader().getResource("defaultconfig.ini"));
    private static User mainUser;

    public static TradeClient tradeClient;

    public static Message receivedMsg;

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
        config=configTemp;

        launch(args);
    }

    public static void CheckOrders(ArrayList<Order> sellOrders, ArrayList<Order> buyOrders) {
        for (int i = 0; i < sellOrders.size(); i++) {
            for (int j = 0; j < buyOrders.size(); j++) {
                if (sellOrders.get(i).getTradeAsset().getAssetName() == buyOrders.get(j).getTradeAsset().getAssetName() && sellOrders.get(i).getQuantityToTrade() >= buyOrders.get(j).getQuantityToTrade() && sellOrders.get(i).getPrice() == buyOrders.get(j).getPrice()) {

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
        //scene.getStylesheets().add(getClass().getResource("styles/main.css").toExternalForm());

        primaryStage.setTitle("Assignment");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}