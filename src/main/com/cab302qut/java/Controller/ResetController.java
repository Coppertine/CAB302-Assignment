package com.cab302qut.java.Controller;

import com.cab302qut.java.Users.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class ResetController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button resetButton;
    @FXML
    private Button backButton;
    @FXML
    private Label helperLabel;

    private String password;
    private String username;
    private boolean correctUser = false;

    /**
     * @param actionEvent
     * @throws IOException
     */
    public void resetPassword(ActionEvent actionEvent) throws IOException {
        checkLogin();
    }

    /**
     * checks user login is correct and submits to server
     */
    private void checkLogin() {

        if (username == "" && password != "") {
            helperLabel.setText("Please enter a Username");
        } else if (password == "" && username != "") {
            helperLabel.setText("Please enter a password");
        } else if (password == "" && username == "") {
            helperLabel.setText("Please enter a username and password");
        } else {
            System.out.println(password + " " + username);
            User checkUser = new User(username, password);
            checkUser.setPassword(password);
            System.out.println(checkUser.getUsername() + " " + checkUser.getPassword());
            correctUser = true;
        }

        if (!correctUser) {
            helperLabel.setText("The Username or Password was incorrect");
        }
    }


    public void back(ActionEvent actionEvent) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}