package com.cab302qut.java.Client;

import com.cab302qut.java.CAB302Assignment;
import com.cab302qut.java.Controller.MainController;
import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Organisation.Organisation;
import com.cab302qut.java.Users.User;
import com.cab302qut.java.util.Debug;
import com.cab302qut.java.util.ServerConfiguration;
import com.cab302qut.java.util.*;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
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

    public final void run(final ServerConfiguration inputConfig) {
        //RefreshData();
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


    public void RefreshData() {
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("Task performed on: " + new Date() + " in" +
                        "Thread's name: " + Thread.currentThread().getName());

            }
        };
        Timer timer = new Timer("Timer");

        long delay = 20000L;
        timer.schedule(task, delay, delay);
    }

    /**
     * Handles all incoming messages from the server instance.
     *
     * @param msg The type of message as well as the object payload.
     */
    public final void handleMsg(Message msg) {
        if (msg != null) {
            System.out.println("REC from Server: " + msg.getMessageType());
            //CAB302Assignment.receivedMsg = theMsg; // the static field
            // is available for controllers to access

            if (msg instanceof Message) {
                Message theMsg = (Message) msg;
                String msgType = theMsg.getMessageType();
                System.out.println("REC from Server: " + theMsg.getMessageType());
                if (theMsg.getMessageType().equals("id")) {
                    clientID = (Integer) theMsg.getMessageObject();
                    System.out.println("ClientID: " + clientID);
                } else if (theMsg.getMessageType().equals("exit")) {
                    thread.stopped = true;
                } else if (theMsg.getMessageType().equals("StatusCheck")) {
                    System.out.println("Status found");
                    //TODO add all of the different trade server commands to allow for each return to assign static variables correctly.
                } else if (theMsg.getMessageType().equals("Trades")) {

                } else if (theMsg.getMessageType().equals("Assets")) {
                    StaticVariables.assets = (Asset[]) theMsg.getMessageObject();
                } else if (theMsg.getMessageType().equals("UserOrg")) {
                    //StaticVariables.organisationsAssets = (ArrayList<ArrayList<String>>) theMsg.getMessageObject();
                } else if (msgType.equals("UpdateOrgsCredits")) {
                    StaticVariables.orgCreditsUpdateMsg = null;
                    StaticVariables.orgCreditsUpdateMsg = (ArrayList<String>) theMsg.getMessageObject();
                } else if (msgType.equals("UpdateOrgsAssetNum")) {
                    StaticVariables.orgAssetNumUpdateMsg = null;
                    StaticVariables.orgAssetNumUpdateMsg = (ArrayList<String>) theMsg.getMessageObject();
                } else if (msgType.equals("OrgsList")) {
                    StaticVariables.organisationList = null; // reset orgs list as controllers
                    // using while loops wont recognise new list
                    StaticVariables.organisationList = (ArrayList<ArrayList<String>>) theMsg.getMessageObject();
                } else if (msgType.equals("OrgsCurrentAssets")) {
                    StaticVariables.orgsAssets = null;
                    StaticVariables.orgsAssets = (ArrayList<ArrayList<String>>) theMsg.getMessageObject();
                } else if (msgType.equals("")) {
                    CAB302Assignment.receivedMsg = theMsg; // the static field
                    // is available for controllers to access
                    //send("status ready");
                } else if (theMsg.getMessageType().equals("UserAccepted")) {
                    CAB302Assignment.assetData = theMsg;
                    StaticVariables.user = (User) theMsg.getMessageObject();
                    System.out.println(StaticVariables.user.getOrganisation() + "User Organisation");
                    StaticVariables.loginSuccessful = true;
                    StaticVariables.login = true;
                    StaticVariables.userOrganisation = ((User) theMsg.getMessageObject()).getOrganisation();
                    System.out.println(StaticVariables.user );
                    System.out.println(StaticVariables.userOrganisation);
                    System.out.println("user Agree");
                    //send("status ready");
                } else if (theMsg.getMessageType().equals("UserDenied")) {
                    StaticVariables.loginSuccessful = false;
                    StaticVariables.login = true;
                    System.out.println("user Agree");
                    //send("status ready");
                } else {
                    //CAB302Assignment.receivedMsg = theMsg; // the static field
                    // is available for controllers to access
                    CAB302Assignment.assetData = theMsg;
                    System.out.println(theMsg.getMessageObject().getClass());
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
        public final void send ( final String msg){
            try {
                System.out.println("Sending to server: " + msg);
                outputStream.writeUTF(msg);
                outputStream.close();
            } catch (IOException e) {
                Debug.log(e.toString());
            }
        }

        public final void sendMessage (Message obj){
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
        public final void open () throws IOException {
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
        void remove ( final int clientID){
        }

        /**
         * Returns the integer representation of the location Id.
         *
         * @return The client location Id.
         */
        public final int getClientID () {
            return clientID;
        }

        /**
         * Sets the client location Id.
         *
         * @param clientIDInput The client location Id.
         */
        public final void setClientID ( final int clientIDInput){
            this.clientID = clientIDInput;
        }

        /**
         * Returns the client connection configuration object.
         *
         * @return The client configuration instance.
         */
        public final ServerConfiguration getConfig () {
            return config;
        }

        /**
         * Sets the client connection configuration object.
         *
         * @param configInput The client configuration instance.
         */
        public final void setConfig ( final ServerConfiguration configInput){
            this.config = configInput;
        }

        /**
         * Returns the input stream of the server.
         *
         * @return The data input stream.
         */
        public final DataInputStream getStreamIn () {
            return inputStream;
        }

        /**
         * Sets the input stream of the server.
         *
         * @param streamInInput The data input stream.
         */
        public final void setStreamIn ( final DataInputStream streamInInput){
            this.inputStream = streamInInput;
        }

        /**
         * Returns the output stream of the server.
         *
         * @return The data output stream.
         */
        public final DataOutputStream getStreamOut () {
            return outputStream;
        }

        /**
         * Sets the output stream object.
         *
         * @param streamOutInput The data output stream.
         */
        public final void setStreamOut ( final DataOutputStream streamOutInput){
            this.outputStream = streamOutInput;
        }

        public final ObjectInputStream getObjectInputStream () {
            return objectInputStream;
        }

        public final ObjectOutputStream getObjectOutputStream () {
            return objectOutputStream;
        }
    }
