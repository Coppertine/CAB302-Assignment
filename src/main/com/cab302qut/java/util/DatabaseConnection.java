package com.cab302qut.java.util;

import com.cab302qut.java.CAB302Assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;


    /**
     * Attempts to establish the connection using the config file.
     */
    public static void establishConnection() {
        try {
            ServerConfiguration serverConfig = CAB302Assignment.getConfig();
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("MariaDB JDBC Driver Registered!");
            connection = DriverManager.getConnection(
                    "jdbc:mariadb://"+ serverConfig.getAddress()
                            + ":" + serverConfig.getPort() +
                            "/" + serverConfig.getSchema(),
                    serverConfig.getUsername(),
                    serverConfig.getPassword());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
