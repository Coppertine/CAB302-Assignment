//package com.cab302qut.java.Client.Connection;
//
//import java.io.IOException;
//import java.net.Socket;
//
//public class ClientThread extends Thread {
//
//    private TradeClient client;
//
//    private final int clientID;
//    private final int clientPort;
//
//    /**
//     *
//     */
//    public volatile boolean stopped = false;
//
//    /**
//     * The constructor for the Client Thread.
//     *
//     * @param aThis       The traffic client instance.
//     * @param socketInput The socket for the client.
//     * @param clientInput The client location id.
//     */
//    public ClientThread(final TradeClient aThis, final Socket socketInput,
//                        final int clientInput) {
//        super();
//
//        this.clientID = clientInput;
//        this.clientPort = 0;
//        this.client = aThis;
//
//        this.start();
//    }
//
//    @Override
//    public final void run() {
//        System.out.println("Start Running");
//        while (!stopped) { // Why? just, why?
//            try {
//                client.handle(client.getStreamIn().readUTF());
////                System.out.println(client.getStreamIn().readUTF());
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//
//    /**
//     * Returns the Traffic Client
//     *
//     * @return The traffic client instance.
//     */
//    public TradeClient getClient() {
//        return client;
//    }
//
//    /**
//     * Returns the client location.
//     *
//     * @return The integer representation of the location id.
//     */
//    public final int getClientID() {
//        return clientID;
//    }
//
//    /**
//     * Returns the client port number.
//     *
//     * @return The integer representation of the port number.
//     */
//    public final int getClientPort() {
//        return clientPort;
//    }
//}
