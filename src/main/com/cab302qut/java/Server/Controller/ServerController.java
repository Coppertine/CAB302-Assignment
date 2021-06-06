package com.cab302qut.java.Server.Controller;

import com.cab302qut.java.CAB302Assignment;
import com.cab302qut.java.Server.Connection.TradeServer;
import com.cab302qut.java.util.Debug;
import com.cab302qut.java.util.ServerConfiguration;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable {

    TradeServer tradeSystemServer;

    @FXML
    private TextArea consoleField;
    @FXML
    private Button startServerBtn;
    @FXML
    private Button stopServerBtn;


    public void StartServer() {
        try {
            ServerConfiguration theServerConfig = CAB302Assignment.getConfig();
            tradeSystemServer = new TradeServer(theServerConfig, this);
            Thread thread = new Thread(tradeSystemServer, "serverThread");
            thread.start();
            startServerBtn.disableProperty().setValue(true);
            stopServerBtn.disableProperty().setValue(false);
        } catch (Exception e){
            printToMessageScreen(e.getMessage());
        }
    }

    public void StopServer(){
        tradeSystemServer.stop();

        startServerBtn.disableProperty().setValue(false);
        stopServerBtn.disableProperty().setValue(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void printToMessageScreen(String msg) {
        String currentMessages = consoleField.getText();
        currentMessages = currentMessages.concat("\n" + msg);
        consoleField.setText(currentMessages);
    }
}
