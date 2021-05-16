package com.cab302qut.java.util;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.stream.Stream;

public class ServerConfiguration {
    private DatabaseCredentials credentials;
    private Socket socket;

    public void reloadConfiguration(String filePath) throws IOException {
        File configFile = new File(filePath);
        if (!configFile.exists()) {
            configFile.createNewFile();
        }

        // Read Config File
        Stream<String> lines = Files.lines(configFile.toPath());
        lines.forEach((line) -> {
            credentials = new DatabaseCredentials();
            if(line.startsWith("Address:"))
                credentials.address = line.substring("Address:".length() + 1);
            else if(line.startsWith("Port:"))
                credentials.port = Integer.parseInt(line.substring("Port:".length() + 1));
            else if(line.startsWith("Username:"))
                credentials.username = line.substring("Username:".length() + 1);
            else if(line.startsWith("Password:"))
                credentials.password = line.substring("Password:".length() + 1);
            else if(line.startsWith("Schema:"))
                credentials.defaultSchema = line.substring("Schema:".length() + 1);
        });

    }

    public String getAddress() {
        return credentials.address;
    }

    public int getPort() {
        return credentials.port;
    }

    public String getUsername() {
        return credentials.username;
    }

    public String getPassword() {
        return credentials.password;
    }

    public String getSchema() {
        return credentials.defaultSchema;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    private static class DatabaseCredentials
    {
        private String address;
        private int port;
        private String username;
        private String password;
        private String defaultSchema;
    }

}
