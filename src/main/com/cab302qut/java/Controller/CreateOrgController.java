package com.cab302qut.java.Controller;

import com.cab302qut.java.CAB302Assignment;
import com.cab302qut.java.util.Message;
import com.cab302qut.java.util.StaticVariables;
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
        else {
            try {
                Double credits;
                credits = Double.parseDouble(theOrgCredits);
                if (credits < 0) {
                    throw new Exception("NegativeNum");
                }

                // check if an org with same name already exists
                for (ArrayList<String> existingOrgs : StaticVariables.organisationList) {
                    if (theOrgName.equals(existingOrgs.get(0))) {throw new Exception("OrgNameAlreadyExists");}
                }
                ArrayList<String> orgDetails = new ArrayList<>();
                orgDetails.add(theOrgName);
                orgDetails.add(theOrgCredits);
                Message msg = new Message("CreateOrg",orgDetails);
                System.out.println("Sent to server create org");
                StaticVariables.organisationList = null;
                CAB302Assignment.tradeClient.sendMessage(msg);
                while (StaticVariables.organisationList == null) {
                    System.out.println("Waiting for updated org list");
                }
                helperLabel.setText("Success added organisation");
            } catch (Exception n) {
                if (n instanceof NumberFormatException) {
                    helperLabel.setText("Please provide a valid number.");
                }
                else if (n.getMessage().equals("NegativeNum")) {
                    helperLabel.setText("Please provide a positive number.");
                }
                else if (n.getMessage().equals("OrgNameAlreadyExists")) {
                    helperLabel.setText("Error: The provided name already belongs to an existing organisation.");
                }
            }
        }
    }

    /**
     * Request list of existing organisations from server.
     */
    public void getOrgsList() {
        if (StaticVariables.organisationList == null || StaticVariables.organisationList.isEmpty()) {
            Message msg = new Message("GetOrgsList");
            CAB302Assignment.tradeClient.sendMessage(msg);
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
        getOrgsList();
//        orgName.setText("");
//        orgCredits.setText("");

    }
}
