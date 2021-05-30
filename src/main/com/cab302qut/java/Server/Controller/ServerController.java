package com.cab302qut.java.Server.Controller;

import com.cab302qut.java.CAB302Assignment;
import com.cab302qut.java.Server.Connection.TradeServer;
import com.cab302qut.java.util.ServerConfiguration;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {
    @FXML
    private TextArea consoleField;

    private TradeServer server;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        server = new TradeServer(CAB302Assignment.getConfig(),this);
        Thread thread = new Thread(server, "thread");
        thread.start();
    }

//    public void printToMessageScreen(String msg) {
//        consoleField.setText(consoleField.getText() + "\n" + msg);
//    }

    //public void printToMessageScreen(String msg) {

    //}
}
