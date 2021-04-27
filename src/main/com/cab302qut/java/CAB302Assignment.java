package com.cab302qut.java;

import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Organisation.Organisation;
import com.cab302qut.java.Trades.Trade;
import com.cab302qut.java.Users.User;

import javafx.application.Application;
import javafx.stage.Stage;
import main.com.cab302qut.java.Users.UserType;

import java.io.IOException;
import java.util.Scanner;

public class CAB302Assignment { //extends Application {


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        //launch(args);
        User mainUser = new User("John", "JohnMainUser", "password", UserType.Administrator);
        User tradeUser = new User("Ben", "BenTrade", "password2", UserType.Default);
        Asset asset1 = new Asset("emojis", 1, 10);
        Asset asset2 = new Asset("CPU", 2, 5);



        Organisation organisation1 = new Organisation("main Organisation");
        organisation1.addUser(mainUser);
        mainUser.setOrganisation(organisation1);
        organisation1.addCredits(1000);

        System.out.println("create a new user");
        System.out.println("y/n");

        //String agree = scanner.nextLine();


        System.out.println("name");
        String name = scanner.nextLine();
        System.out.println("username");
        String username = scanner.nextLine();
        System.out.println("password");
        String password = scanner.nextLine();

        User createUser = new User(name, username, password, UserType.Default);

        System.out.println(createUser.getName() + createUser.getUsername() + createUser.getPassword() + createUser.getUserType().toString());

    }
/*
    @Override
    public void start(Stage primaryStage) throws Exception {

    }
*/
}
