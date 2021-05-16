package com.cab302qut.java.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Text organisationLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //organisationLabel = new TextFlow(new Text("Testing"));
    }
}