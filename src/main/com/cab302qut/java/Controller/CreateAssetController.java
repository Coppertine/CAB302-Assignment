package com.cab302qut.java.Controller;

import com.cab302qut.java.CAB302Assignment;
import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Organisation.Organisation;
import com.cab302qut.java.Users.User;
import com.cab302qut.java.Users.UserType;
import com.cab302qut.java.util.Message;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateAssetController implements Initializable {
    @FXML
    private TextField nameField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }




    public void CreateItem(ActionEvent actionEvent) {
        //Verify user input
        if (verifyInput()) {
            try {
                Asset tmpAsset = new Asset(nameField.getText(), 0);
                Message msg = new Message("CreateAsset",tmpAsset);
                CAB302Assignment.tradeClient.sendMessage(msg);
                System.out.println("Send Create Asset");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean verifyInput() {
        if(nameField.getText() == null) {
            nameField.setStyle("-fx-border-color: red");
            return false;
        }
        else {
            return true;
        }
    }

    public void Back() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainITAdmin.fxml"));
            Stage window = (Stage) nameField.getScene().getWindow();
            Scene scene = new Scene(root);
            window.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
