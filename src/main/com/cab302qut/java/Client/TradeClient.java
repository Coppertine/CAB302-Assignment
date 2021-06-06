package com.cab302qut.java.Client;

import com.cab302qut.java.CAB302Assignment;
import com.cab302qut.java.Controller.MainController;
import com.cab302qut.java.Users.User;
import com.cab302qut.java.util.Debug;
import com.cab302qut.java.util.ServerConfiguration;
import com.cab302qut.java.util.*;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class TradeClient implements Runnable {
    private int clientID;
    private ServerConfiguration config = new ServerConfiguration();
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Socket socket;
    private ClientThread thread;

    private MainController controller;

    public final void run(final ServerConfiguration inputConfig) {
        try {
            config = inputConfig;
            config.setSocket(new Socket(config.getAddress(), config.getPort()));
            socket = config.getSocket();
            // TODO: Tell user that client is connected to server. Don't tell port or address.
            System.out.println("TradeClient--Connected: " + config.getSocket());
            open();
            thread = new ClientThread(this, socket, socket.getPort());

        } catch (UnknownHostException uhe) {
            Debug.log("Host unknown: " + uhe.getMessage());
        } catch (IOException ioe) {
            Debug.log("Unexpected exception: " + ioe.getMessage());
        }
    }


    /**
     * Handles all incoming messages from the server instance.
     * @param msg The type of message as well as the object payload.
     */
    public final void handleMsg(Message msg) {
        if (msg != null) {
            System.out.println("REC from Server: " + msg.getMessageType());
            //CAB302Assignment.receivedMsg = theMsg; // the static field
            // is available for controllers to access
            switch (msg.getMessageType()) {
                case "id" -> {
                    clientID = (Integer) msg.getMessageObject();
                    System.out.println("ClientID: " + clientID);
                }
                case "exit" -> thread.stopped = true;
                case "StatusCheck" -> {
                    System.out.println("Status found");
                    send("status ready");
                }
                case "UserAccepted" -> {
                    CAB302Assignment.assetData = msg;
                    StaticVariables.user = (User) msg.getMessageObject();
                    System.out.println(StaticVariables.user.getOrganisation() + "User Organisation");
                    StaticVariables.loginSuccessful = true;
                    StaticVariables.login = true;
                    StaticVariables.userOrganisation = ((User) msg.getMessageObject()).getOrganisation();
                    System.out.println("user Agree");
                    send("status ready");
                }
                case "UserDenied" -> {
                    StaticVariables.loginSuccessful = false;
                    StaticVariables.login = true;
                    System.out.println("user Agree");
                    send("status ready");
                }
                case "OrgsList" -> {
                    //noinspection unchecked
                    StaticVariables.organisationList = (ArrayList<ArrayList<String>>)msg.getMessageObject();
                }

                default -> {
                    CAB302Assignment.assetData = msg;
                    System.out.println(msg.getMessageObject().getClass());
                }
            }
        }
    }

    @Override
    public final void run() {
        config = CAB302Assignment.getConfig();
        run(config);
    }

    /**
     * Sends a specific message to the thread in question.
     *
     * @param msg The string representation of the message from the client
     */
    public final void send(final String msg) {
        try {
            System.out.println("Sending to server: " + msg);
            outputStream.writeUTF(msg);
            outputStream.close();
        } catch (IOException e) {
            Debug.log(e.toString());
        }
    }

    public final void sendMessage(Message obj) {
        try {
            System.out.println("Send to server: " + obj.getClass());
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
        } catch (Exception e) {
            Debug.log(e.getMessage());
        }
    }

    /**
     * Opens the thread with streams loaded.
     *
     * @throws IOException if DataInputStreams can not be created.
     */
    public final void open() throws IOException {
//        inputStream = new DataInputStream(
//                new BufferedInputStream(socket.getInputStream()));
//        outputStream = new DataOutputStream(
//                new BufferedOutputStream(socket.getOutputStream()));

        objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        objectOutputStream.flush();
        objectInputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    /**
     * Attempts to remove the client from the server.
     *
     * @param clientID The Location Id.
     * @deprecated Unimplemented
     */
    @Deprecated
    void remove(final int clientID) {
    }

    /**
     * Returns the integer representation of the location Id.
     *
     * @return The client location Id.
     */
    public final int getClientID() {
        return clientID;
    }

    /**
     * Sets the client location Id.
     *
     * @param clientIDInput The client location Id.
     */
    public final void setClientID(final int clientIDInput) {
        this.clientID = clientIDInput;
    }

    /**
     * Returns the client connection configuration object.
     *
     * @return The client configuration instance.
     */
    public final ServerConfiguration getConfig() {
        return config;
    }

    /**
     * Sets the client connection configuration object.
     *
     * @param configInput The client configuration instance.
     */
    public final void setConfig(final ServerConfiguration configInput) {
        this.config = configInput;
    }

    /**
     * Returns the input stream of the server.
     *
     * @return The data input stream.
     */
    public final DataInputStream getStreamIn() {
        return inputStream;
    }

    /**
     * Sets the input stream of the server.
     *
     * @param streamInInput The data input stream.
     */
    public final void setStreamIn(final DataInputStream streamInInput) {
        this.inputStream = streamInInput;
    }

    /**
     * Returns the output stream of the server.
     *
     * @return The data output stream.
     */
    public final DataOutputStream getStreamOut() {
        return outputStream;
    }

    /**
     * Sets the output stream object.
     *
     * @param streamOutInput The data output stream.
     */
    public final void setStreamOut(final DataOutputStream streamOutInput) {
        this.outputStream = streamOutInput;
    }

    public final ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public final ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }
}
