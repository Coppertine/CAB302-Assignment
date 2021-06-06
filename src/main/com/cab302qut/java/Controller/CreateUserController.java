package com.cab302qut.java.Controller;

import com.cab302qut.java.CAB302Assignment;
import com.cab302qut.java.Organisation.Organisation;
import com.cab302qut.java.Users.User;
import com.cab302qut.java.Users.UserType;
import com.cab302qut.java.util.Message;
import com.cab302qut.java.util.StaticVariables;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {
    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField confirmPasswordField;

    @FXML
    public ChoiceBox<String> organisationDropDown;

    @FXML
    private ChoiceBox<UserType> userTypeDropdown;

    private String chosenOrg;

    private String chosenAsset;

    private ObservableList<String> existingOrgs;

    private ObservableList<String> existingOrgAssets;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateDropdowns();
    }

    private void populateDropdowns() {
        populateOrganisation();
        populateUserType();
    }

    private void populateUserType() {
        userTypeDropdown.getItems().addAll(UserType.values());
    }

    private void populateOrganisation() {
        try {
            existingOrgs = FXCollections.observableArrayList();
            for (ArrayList<String> row: CAB302Assignment.currentOrganisations) {
                existingOrgs.add(row.get(0)); // get orgName
            }
            organisationDropDown.setItems(FXCollections.observableArrayList(existingOrgs));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void CreateUser(ActionEvent actionEvent) {
        //Verify user input
        if (verifyInput()) {
            try {
                User tmpUser = new User(usernameField.getText(),User.HashPassword(passwordField.getText()),userTypeDropdown.getValue(),
                        new Organisation(organisationDropDown.getValue()));
                Message msg = new Message("CreateUser",tmpUser);
                CAB302Assignment.tradeClient.sendMessage(msg);
                System.out.println("Send org credit update");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean verifyInput() {
        if(organisationDropDown.getValue() == null)
            organisationDropDown.setStyle("-fx-border-color: red");
        if(userTypeDropdown.getValue() == null)
            userTypeDropdown.setStyle("-fx-border-color: red");
        if(usernameField.editableProperty().getValue() == null)
        {
            usernameField.setStyle("-fx-border-color: red");
        }
        return true;
    }
}