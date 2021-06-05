package com.cab302qut.java.Controller;

import com.cab302qut.java.util.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateOrgController implements Initializable {

    @FXML
    TextField orgName;
    @FXML
    TextField orgCredits;

    @FXML
    Label helperLabel;

    private boolean validDetails = false;

    public void CheckDetails() {
        String theOrgName = orgName.getText().toString();
        String theOrgCredits = orgCredits.getText().toString();
        if (theOrgName.isBlank() && !theOrgCredits.isBlank()) {
            helperLabel.setText("Please enter a Username");
        } else if (!theOrgName.isBlank() && theOrgCredits.isBlank()) {
            helperLabel.setText("Please enter a valid number of credits");
        } else if (theOrgName.isBlank() && theOrgCredits.isBlank()) {
            helperLabel.setText("Please enter an Organisation name and their initial credits");
        } else {
            try {
                Double credits;
                credits = Double.parseDouble(theOrgCredits);
                if (credits < 0) {
                    throw new Exception("NegativeNum");
                }
                ArrayList<String> orgDetails = new ArrayList<>();
                orgDetails.add(theOrgName);
                orgDetails.add(theOrgCredits);
                Message msg = new Message("CreateOrg",orgDetails);
                validDetails = true;
                System.out.println("Sent to server");
            } catch (Exception n) {
                if (n instanceof NumberFormatException) {
                    helperLabel.setText("Please provide a valid number.");
                }
                else if (n.getMessage().equals("NegativeNum")) {
                    helperLabel.setText("Please provide a positive number.");
                }
            }
        }
        if (validDetails) {
            try {
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainITAdmin.fxml"));
                Stage window = (Stage) orgName.getScene().getWindow();
                Scene scene = new Scene(root);
                window.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void Back() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainITAdmin.fxml"));
            Stage window = (Stage) orgName.getScene().getWindow();
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        orgName.setText("");
//        orgCredits.setText("");
    }
}
