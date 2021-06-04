package com.cab302qut.java.Controller;

import com.cab302qut.java.CAB302Assignment;
import com.cab302qut.java.Client.Connection.TradeClient;
import com.cab302qut.java.util.Message;
import com.cab302qut.java.util.ServerConfiguration;
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
import java.util.ArrayList;
import java.util.Calendar;


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
    private String userOrganisation = "testOrganisation";
    private boolean correctUser = false;

    /**
     * @param actionEvent
     * @throws IOException
     */
    public void userLogin(ActionEvent actionEvent) throws IOException {
        checkLogin(actionEvent);
    }

    /**
     * checks user login is correct and submits to server
     */
    private void checkLogin(ActionEvent actionEvent) {

        username = usernameField.getText().toString();
        password = passwordField.getText().toString();
        if (username == "" && password != "") {
            helperLabel.setText("Please enter a Username");
        } else if (password == "" && username != "") {
            helperLabel.setText("Please enter a password");
        } else if (password == "" && username == "") {
            helperLabel.setText("Please enter a username and password");
        } else {
            try {
                ServerConfiguration serverConfig = CAB302Assignment.getConfig();
                CAB302Assignment.tradeClient = new TradeClient();
                CAB302Assignment.tradeClient.run(serverConfig);

                ArrayList<String> credentials = new ArrayList<>();
                credentials.add(username);
                credentials.add(password);
                Message msg = new Message("Login",credentials);
                CAB302Assignment.tradeClient.sendMessage(msg);

                //CAB302Assignment.tradeClient.send("Login: " + username + "-" + password);


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            //check with database for correct username and password.
            System.out.println(password + " " + username);
            User checkUser = new User(username, password);
            checkUser.setPassword(password);
            System.out.println(checkUser.getUsername() + " " + checkUser.getPassword());
            correctUser = true;

            try {

                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Main.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                MainController mainController = loader.getController();
                mainController.setOrganisationLabel(userOrganisation);
                mainController.setUserLabel(username);
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!correctUser) {
            helperLabel.setText("The Username or Password was incorrect");
        }
    }

    public void passwordReset(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ResetPassword.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}