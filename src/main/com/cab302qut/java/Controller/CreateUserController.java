package com.cab302qut.java.Controller;

import com.cab302qut.java.Users.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {

    @FXML
    public ChoiceBox<String> organisationDropDown;

    @FXML
    private ChoiceBox<UserType> userTypeDropdown;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateDropdowns();
    }

    private void populateDropdowns() {
        populateOrganisation();
        populateUserType();
    }

    private void populateUserType() {
        userTypeDropdown = new ChoiceBox<>();
        userTypeDropdown.getItems().setAll(UserType.values());
    }

    private void populateOrganisation() {

    }


    public void CreateUser(ActionEvent actionEvent) {
    }
}
