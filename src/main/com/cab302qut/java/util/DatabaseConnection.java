package com.cab302qut.java.util;

import com.cab302qut.java.CAB302Assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;


    /**
     * Attempts to establish the connection using the config file.
     */
    public void establishConnection() {
        try {
            ServerConfiguration serverConfig = CAB302Assignment.getConfig();
            connection = DriverManager.getConnection(
                    "jdbc:mysql://"+ serverConfig.getAddress()
                            + ":" + serverConfig.getPort() +
                            "/" + serverConfig.getSchema(),
                    serverConfig.getUsername(),
                    serverConfig.getPassword());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeStatement(String statement) throws SQLException {
        return connection.createStatement().executeQuery(statement);
    }

    public void CloseConnection() throws SQLException {
        connection.close();
    }


}
