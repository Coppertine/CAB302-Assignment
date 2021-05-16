package com.cab302qut.java.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import com.cab302qut.java.Users.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class TestController{

    public TestController() {

    }

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button submitButton;
    @FXML
    private Label helperLabel;

    private String password;
    private String username;

    public void userLogin(ActionEvent actionEvent) throws IOException {
        checkLogin();
    }

    private void checkLogin(){

        username = usernameField.getText().toString();
        password = passwordField.getText().toString();
        System.out.println(password+ " "+ username);
        User test = new User(username,password);
        test.setPassword(password);

        System.out.println(test.getUsername()+ " "+ test.getPassword());

    }

//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        usernameField.setPromptText("username");
//        passwordField.setPromptText("password");
//    }
}