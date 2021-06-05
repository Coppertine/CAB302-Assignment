package com.cab302qut.java.Controller;

import com.cab302qut.java.CAB302Assignment;
import com.cab302qut.java.util.Message;
import com.cab302qut.java.util.StaticVariables;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.annotation.processing.Generated;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditOrgController implements Initializable {
    @FXML
    ChoiceBox<String> orgChoice;

    @FXML
    ChoiceBox<String> orgAssets;

    @FXML
    TextField editCredits;

    @FXML
    TextField editAssetNum;

    @FXML
    Label helperLabel;
    @FXML
    Label helperLabel2;

    @FXML
    Button btn_updateCredits;

    @FXML
    Button btn_updateAssets;

    private String chosenOrg;

    private String chosenAsset;

    private ObservableList<String> existingOrgs;

    private ObservableList<String> existingOrgAssets;


    public void setOrgChoiceBox() {
        try {
            existingOrgs = FXCollections.observableArrayList();
            for (ArrayList<String> row: StaticVariables.organisationList) {
                existingOrgs.add(row.get(0)); // get orgName
            }
            orgChoice.setItems(FXCollections.observableArrayList(existingOrgs));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setAssetChoiceBox() {
        try {
            while (StaticVariables.orgsAssets == null) {
                System.out.println("Waiting for orgs assets");
            }
            orgAssets.getItems().clear();

            existingOrgAssets = FXCollections.observableArrayList();
            for (ArrayList<String> row: StaticVariables.orgsAssets) {
                existingOrgAssets.add(row.get(0)); // get orgName
            }
            orgAssets.setItems(FXCollections.observableArrayList(existingOrgAssets));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Initialise the asset choice box change behaviour
     */
    public void initAssetChoiceBox() {
        try {
            orgAssets.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    if (!btn_updateAssets.isVisible()) {
                        btn_updateAssets.setVisible(true);
                    }
                    chosenAsset = existingOrgAssets.get(newValue.intValue());
                    helperLabel2.setText(chosenAsset);
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void initOrgChoiceBox() {
        orgChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (!btn_updateCredits.isVisible()) {
                    btn_updateCredits.setVisible(true);
                }
                if (!orgAssets.isVisible()) {
                    orgAssets.setVisible(true);
                }
                chosenOrg = existingOrgs.get(newValue.intValue());
                helperLabel.setText(chosenOrg);
                getOrgAssets();
                setAssetChoiceBox();
            }
        });
    }


    public boolean CheckCreditsInput() {
        try {
            if (editCredits.getText().isBlank()) {
                helperLabel.setText("Provide input for credits.");
                return false;
            }
            String input = editCredits.getText().toString();
            Double credits;
            credits = Double.parseDouble(input);
            if (credits < 0) {
                throw new Exception("NegativeNum");
            }
            return true;
        } catch (Exception n) {
            if (n instanceof NumberFormatException) {
                helperLabel.setText("Please provide a valid number input.");
            }
            else if (n.getMessage().equals("NegativeNum")) {
                helperLabel.setText("Please provide a positive number.");
            }
            return false;
        }
    }

    public boolean CheckAssetNumInput() {
        try {
            if (editAssetNum.getText().isBlank()) {
                helperLabel.setText("Please provide input for update asset quantity.");
                return false;
            }
            String input = editAssetNum.getText().toString();
            Integer num;
            num = Integer.parseInt(input);
            if (num < 0) {
                throw new Exception("NegativeNum");
            }
            return true;
        } catch (Exception n) {
            if (n instanceof NumberFormatException) {
                helperLabel.setText("Please provide a valid whole number input.");
            }
            else if (n.getMessage().equals("NegativeNum")) {
                helperLabel.setText("Please provide a whole positive number.");
            }
            return false;
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

    /**
     * Request list of assets of the chosen organisation from server.
     */
    public void getOrgAssets() {
        if (StaticVariables.orgsAssets == null || StaticVariables.organisationList.isEmpty()) {
            StaticVariables.orgsAssets = null; //reset for new org selected
            Message msg = new Message("GetOrgsAsset", chosenOrg);
            CAB302Assignment.tradeClient.sendMessage(msg);
        }
    }

    public void Back() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainITAdmin.fxml"));
            Stage window = (Stage) btn_updateCredits.getScene().getWindow();
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void SendCreditsUpdate() {
        try {
            // Check if input empty
            if (CheckCreditsInput()) {
                ArrayList<String> orgCreditsUpdate = new ArrayList<>();
                orgCreditsUpdate.add(chosenOrg);
                orgCreditsUpdate.add(editCredits.getText());
                Message msg = new Message("EditOrgCreditsNum",orgCreditsUpdate);
                CAB302Assignment.tradeClient.sendMessage(msg);
                System.out.println("Sent edit org credits num");
            }
        } catch (Exception e) {

        }
    }

    public void SendAssetUpdate() {
        try {
            // Check if input empty
            if (CheckAssetNumInput()) {
                ArrayList<String> orgAssetUpdate = new ArrayList<>();
                orgAssetUpdate.add(chosenOrg);
                orgAssetUpdate.add(chosenAsset);
                orgAssetUpdate.add(editAssetNum.getText());
                Message msg = new Message("EditOrgAssetNum",orgAssetUpdate);
                CAB302Assignment.tradeClient.sendMessage(msg);
                System.out.println("Send asset num update");
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getOrgsList();
        btn_updateAssets.setVisible(false);
        btn_updateCredits.setVisible(false);
        orgAssets.setVisible(false);
        editCredits.setText("");
        editAssetNum.setText("");
        // wait till we get list of orgs from server before showing window
        while (StaticVariables.organisationList == null) {
            System.out.println("Waiting");
        }
        initOrgChoiceBox();
        setOrgChoiceBox();
        initAssetChoiceBox();
    }
}
