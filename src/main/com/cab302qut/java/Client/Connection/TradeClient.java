package com.cab302qut.java.Client.Connection;

import com.cab302qut.java.Client.Controller.MainController;
import com.cab302qut.java.util.ServerConfiguration;
import com.cab302qut.java.util.*;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TradeClient implements Runnable {
    private int clientID;
    private ServerConfiguration config = new ServerConfiguration();
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Socket socket;
    private ClientThread thread;
    private MainController controller;

    public final void run(final ServerConfiguration inputConfig)
    {
        try {
            config = inputConfig;
            config.setSocket(
                    new Socket(config.getAddress(), config.getPort()));
            socket = config.getSocket();
            // TODO: Tell user that client is connected to server. Don't tell port or address.
            System.out.println("Connected: " + config.getSocket());
            open();
            thread = new ClientThread(this, socket, socket.getPort());
        }catch (UnknownHostException uhe) {
            Debug.log("Host unknown: " + uhe.getMessage());
        } catch (IOException ioe) {
            Debug.log("Unexpected exception: " + ioe.getMessage());
        }
    }

    /**
     * Handles the message coming from the server thread.
     *
     * @param msg The message from the server.
     */
    public final void handle(final String msg) {
        //TODO:  Handle other commands other than the usual, like Trades and such.
        if (msg.startsWith("id: ")) {
            clientID = Integer.parseInt(msg.substring("id: ".length()));
        } else if (msg.startsWith("exit")) {
            thread.stopped = true;
        } else if (msg.startsWith("status")) {
            System.out.println("Status found");
            send("status ready");
        } else {
            System.out.println(msg);
        }
    }


    @Override
    public final void run() {
        config = new ServerConfiguration();
        run(config);
    }

    /**
     * Sends a specific message to the thread in question.
     *
     * @param msg The string representation of the message from the client
     */
    public final void send(final String msg) {
        try {
            System.out.println("Sending: " + msg);
            outputStream.writeUTF(msg);
            outputStream.flush();
        } catch (IOException e) {
            Debug.log(e.toString());
        }
    }

    /**
     * Opens the thread with streams loaded.
     *
     * @throws IOException if DataInputStreams can not be created.
     */
    public final void open() throws IOException {
        inputStream = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));
        outputStream = new DataOutputStream(
                new BufferedOutputStream(socket.getOutputStream()));
    }

    /**
     *
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
}
