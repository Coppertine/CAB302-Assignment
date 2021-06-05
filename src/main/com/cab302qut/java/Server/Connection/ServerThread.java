package com.cab302qut.java.Server.Connection;

import com.cab302qut.java.util.Debug;

import java.io.*;
import java.net.Socket;
import com.cab302qut.java.util.*;

public class ServerThread extends Thread {

    /**
     * The server instance.
     */
    private final TradeServer client;
    /**
     * The server socket to send and receive information.
     */
    private final Socket socket;
    /**
     * The client location ID.
     */
    private final int clientID;
    /**
     * The port number of the client.
     */
    private final int clientPort;
    /**
     * The incoming stream of information from the clients.
     */
    private DataInputStream streamIn;
    /**
     * The outgoing stream of information to the client.
     */
    private DataOutputStream streamOut;


    private ObjectOutputStream objectOutputStream;

    private ObjectInputStream objectInputStream;

    /**
     * A volatile boolean to prevent exceptions from thread closure.
     */
    public volatile boolean stopped = false;

    /**
     * The constructor of the Office Thread.
     *
     * @param aThis The office Server instance.
     * @param socketInput The socket for the server.
     * @param clientIdInput The client location ID.
     */
    public ServerThread(final TradeServer aThis, final Socket socketInput,
                        final int clientIdInput) {
        super();
        this.client = aThis;
        this.socket = socketInput;
        this.clientPort = socket.getPort();
        this.clientID = clientIdInput;
    }

    @Override
    public final void run() {
       while (!stopped) { // Why? just, why?
            try {
                client.handle(clientID,objectInputStream.readObject());
                System.out.println(objectInputStream.readObject());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sends a specific message to the thread in question.
     *
     * @param msg The string representation of the message from the client
     */
    public final void send(final String msg) {
        try {
            System.out.println("Sending " + msg);
            streamOut.writeUTF(msg);
            streamOut.flush();
        } catch (IOException e) {
            Debug.log(e.toString());
            client.remove(clientID);
            this.interrupt();
        }
    }

    public final void sendMessage(Message msg) {
        try {
            System.out.println("Send to client: " + msg.getMessageType());
            objectOutputStream.writeObject(msg);
            objectOutputStream.flush();
        } catch (Exception e){
            if (e instanceof IOException) {
                e.printStackTrace();
            }
            System.out.println(e.getMessage());
        }
    }

    /**
     * Opens the thread with streams loaded.
     *
     * @throws IOException if DataInputStreams can not be created.
     */
    public final void open() throws IOException {
        objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        objectOutputStream.flush();
        objectInputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    /**
     * Gets the server instance.
     *
     * @return The Office Server instance.
     */
    public final TradeServer getServer() {
        return client;
    }

    /**
     * Returns the socket of the client thread.
     *
     * @return The client socket instance.
     */
    public final Socket getSocket() {
        return socket;
    }

    /**
     * Returns the client location.
     *
     * @return The integer representation of the location id.
     */
    public final int getClientID() {
        return clientID;
    }

    /**
     * Returns the client port number.
     *
     * @return The integer representation of the port number.
     */
    public final int getClientPort() {
        return clientPort;
    }

    /**
     * Returns the input stream of the client.
     *
     * @return The data input stream.
     */
    public final DataInputStream getStreamIn() {
        return streamIn;
    }
}
