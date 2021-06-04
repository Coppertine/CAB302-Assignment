package com.cab302qut.java.Controller;

import com.cab302qut.java.Client.Connection.TradeClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateOrgController implements Initializable {

    @FXML
    TextField orgName;
    @FXML
    TextField orgCredits;

    @FXML
    Label helperLabel;

    public void CheckDetails() {
        String theOrgName = orgName.getText().toString();
        String theOrgCredits = orgCredits.getText().toString();
        if (theOrgName.isBlank() && !theOrgCredits.isBlank()) {
            helperLabel.setText("Please enter a Username");
        } else if (!theOrgName.isBlank() && theOrgCredits.isBlank()) {
            helperLabel.setText("Please enter a valid number of credits");
        } else if (theOrgName.isBlank() && theOrgCredits.isBlank()) {
            helperLabel.setText("Please enter an Organisation name and their initial credits");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
