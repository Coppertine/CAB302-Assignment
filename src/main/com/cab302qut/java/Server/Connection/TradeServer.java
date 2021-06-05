package com.cab302qut.java.Server.Connection;

//import com.cab302qut.java.Client.Connection.ClientThread;
import com.cab302qut.java.CAB302Assignment;
import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Server.Controller.ServerController;
import com.cab302qut.java.Trades.Trade;
import com.cab302qut.java.util.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.NoSuchElementException;

public class TradeServer implements Runnable {

    Date date = new Date();

    /**
     * Exited boolean to prevent thread exceptions when shutting down a server.
     */
    private volatile boolean exited = false;
    /**
     * The list of client threads for the server.
     */
    private ArrayList<ServerThread> clients = new ArrayList<>();
    /**
     * The server configuration.
     */
    private ServerConfiguration config;
    /**
     * The server socket, initially null to check if the server is running.
     */
    private ServerSocket server = null;
    /**
     * The FXML Controller.
     */
    private ServerController controller;

    /**
     * Database connection used to keep track of all database input and output.
     */
    private DatabaseConnection connection;

    /**
     * The Constructor of the Trade Server.
     *
     * @param inputConfig The server configuration.
     * @param controllerInput The FXML controller.
     */
    public TradeServer (
            final ServerConfiguration inputConfig,
            final ServerController controllerInput) {
        try {
            System.out.println(
                    "Starting server at: " + inputConfig.getPort());
            this.config = inputConfig;
            this.controller = controllerInput;
            server = new ServerSocket(config.getPort());
            System.out.println(server);
            System.out.println("Attempting to connect to database");
            this.connection = new DatabaseConnection();
            connection.establishConnection();
        } catch (IOException e) {
            Debug.log(e.toString());
        }
    }

    /**
     * Attempts to stop the Trade Server.
     */
    public final void stop() {
        exited = true;
    }

    /**
     * Creates a new Client thread from the socket that connects through.
     *
     * @param socket The client socket that connects to the server.
     */
    public final void addThread(final Socket socket) {
        Debug.log("Client Accepted: " + socket);
        ServerThread client = new ServerThread(this, socket, clients.size() + 1);
        clients.add(client);
        try {
            client.open();
            client.start();
            //client.send("id: " + clients.indexOf(client));
            Message msg = new Message("id",clients.indexOf(client));
            client.sendMessage(msg);
        } catch (IOException e) {
            Debug.log(e.toString());
        }
    }

    @Override
    public final void run() {
        while (!exited) {
            try {
                System.out.println(date.getTime());
                addThread(server.accept());
                //Thread.sleep(1000);
            } catch (IOException ignored) {

            }
        }
        Debug.log("Server is stopped.");
    }

    /**
     * Handles the message coming from the client thread.
     * @param Id The location ID of the client.
     * @param input The message from the client.
     */
    public final synchronized void handle(final int Id, final Object input) {
        Message clientMsg = (Message) input;
        switch (clientMsg.getMessageType()) {
            case "exit" -> {
                findClient(Id).send("exit");
                remove(Id);
            }
            case "status ready" -> controller.printToMessageScreen("Client "
                    + Id + "Ready");
            default -> {
                System.out.println(Id + ": " + input);
//                clients.forEach((client) -> {
//                    client.send(Id + ": " + input);
//                });
                handleCommands(Id, input);
            }
        }
    }

    /**
     * Parses the traffic information to the controller.
     * @param ID The location number of the client.
     * @param input  The string input in CSV format starting with
     * {@code Traffic: }
     */
    private void handleCommands(final int ID, final Object input) {
        try {
            // Get the client
            ServerThread theClientThread = findClient(ID);
            Message theClientMsg = (Message) input;
            if (theClientMsg.getMessageType().equals("Login")){
                System.out.println("Received Login details:" + ((ArrayList<String>) theClientMsg.getMessageObject()).get(0) + " " + ((ArrayList<String>) theClientMsg.getMessageObject()).get(1));
                //TODO: validate database records of details

            }

            else if (theClientMsg.getMessageType().equals("Trade")) {
                //Receive Trade Update, could be new trade or updated

            }
            else if (theClientMsg.getMessageType().equals("SellOrder")) {
                //Receive Trade Update, could be new trade or updated

            }
            else if (theClientMsg.getMessageType().equals("Order")) {
                //Receive Trade Update, could be new trade or updated

            }
            else if (theClientMsg.getMessageType().equals("GetOrgsList")) {
                ArrayList<ArrayList<String>> organisationsData = new ArrayList<>();
                ResultSet set = DatabaseConnection.executeStatement("SELECT * FROM `organisations`;");
                while (set.next()) {
                    ArrayList<String> row = new ArrayList<>();
                    row.add(set.getString("organisationName"));
                    row.add(set.getString("credits"));
                    organisationsData.add(row);
                }
                Message theMsg = new Message("OrgsList",organisationsData);
                theClientThread.sendMessage(theMsg);
            }
            else if (theClientMsg.getMessageType().equals("GetOrgsAsset")) {
                String theOrg = (String) theClientMsg.getMessageObject();
                ArrayList<ArrayList<String>> organisationsAssets = new ArrayList<>();
                ResultSet set = DatabaseConnection.executeStatement("SELECT * FROM `currentAssets` WHERE `organisationName` = '" + theOrg + "';");
                while (set.next()) {
                    ArrayList<String> row = new ArrayList<>();
                    row.add(set.getString("assetType"));
                    row.add(set.getString("quantity"));
                    organisationsAssets.add(row);
                }
                Message theMsg = new Message("OrgsCurrentAssets", organisationsAssets);
                theClientThread.sendMessage(theMsg);
            }
            else if (theClientMsg.getMessageType().equals("GetTrades"))
            {
                ArrayList<AssetTableObj> tradeData = new ArrayList<>();
                ResultSet set = connection.executeStatement(DatabaseStatements.GetYearTrades());
                while (set.next()){
                    tradeData.add(new AssetTableObj(set.getDate("date"),set.getDouble("price")));
                }
                Message theMsg = new Message("Trades",tradeData);
                theClientThread.sendMessage(theMsg);
            }

        } catch (NoSuchElementException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns the client with the same port as the ID.
     *
     * @param clientID Integer of the Port Number of the client to find.
     * @return The OfficeThread of the Client, if present.
     * @throws NoSuchElementException When no element is found of the same ID.
     */
    public final ServerThread findClient(final int clientID) throws NoSuchElementException {
        return clients.stream()
                .filter(c -> c.getClientID() == clientID)
                .findFirst()
                .get();
    }

    /**
     * Attempts to remove the client thread from the server.
     * @param clientID The client location id.
     */
    public final void remove(final int clientID) {
        ServerThread toRemove;
        try {
            toRemove = findClient(clientID);
            clients.remove(toRemove);
        } catch (NoSuchElementException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Sends a status check to all clients of the server.
     */
    public final void statusCheck() {
        clients.forEach((thread) -> {
            Message msg = new Message("StatusCheck");
            thread.sendMessage(msg);
        });
    }
}
