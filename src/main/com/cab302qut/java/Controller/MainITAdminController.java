package com.cab302qut.java.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainITAdminController implements Initializable {
    @FXML
    Button btn_createOrg;
    @FXML
    Button btn_editOrg;
    @FXML
    Button btn_addAsset;
    @FXML
    Button btn_addUser;

    public void OpenCreateOrg() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("CreateOrg.fxml"));
        Stage window = (Stage) btn_createOrg.getScene().getWindow();
        Scene scene = new Scene(root);
        window.setScene(scene);
    }

    public void OpenEditOrg() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("EditOrg.fxml"));
        Stage window = (Stage) btn_createOrg.getScene().getWindow();
        Scene scene = new Scene(root);
        window.setScene(scene);
    }

    public void OpenAddAsset() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AddAsset.fxml"));
        Stage window = (Stage) btn_createOrg.getScene().getWindow();
        Scene scene = new Scene(root);
        window.setScene(scene);
    }

    public void OpenNewUser() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("CreateUser.fxml"));
        Stage window = (Stage) btn_createOrg.getScene().getWindow();
        Scene scene = new Scene(root);
        window.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
