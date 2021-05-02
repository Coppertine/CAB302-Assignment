package com.cab302qut.java.util;

import com.cab302qut.java.CAB302Assignment;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Connection connection;


    public static void establishConnection() {
        try {
            ServerConfiguration serverConfig = CAB302Assignment.getConfig();
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("MariaDB JDBC Driver Registered!");
            connection = DriverManager.getConnection(
                    "jdbc:mariadb://"+ serverConfig.getAddress()
                            + ":" + serverConfig.getPort() +
                            "/" + serverConfig.getName("database.name"),
                    serverConfig.getUsername("database.username"),
                    serverConfig.getPassword("database.password"));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
