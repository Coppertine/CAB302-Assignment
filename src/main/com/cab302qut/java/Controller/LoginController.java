package com.cab302qut.java.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import com.cab302qut.java.Users.User;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
    
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button submitButton;
    @FXML
    private Button passwordResetButton;
    @FXML
    private Label helperLabel;

    private String password;
    private String username;
    private boolean correctUser = false;

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void userLogin(ActionEvent actionEvent) throws IOException {
        checkLogin();
    }

    /**
     * checks user login is correct and submits to server
     */
    private void checkLogin(){

        username = usernameField.getText().toString();
        password = passwordField.getText().toString();
        System.out.println(password+ " "+ username);
        User checkUser = new User(username,password);
        checkUser.setPassword(password);

        System.out.println(checkUser.getUsername()+ " "+ checkUser.getPassword());

        if (!correctUser){
            helperLabel.setText("The Username or Password was incorrect");
        }

    }

    public void passwordReset(ActionEvent actionEvent) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ResetPassword.fxml"));

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}