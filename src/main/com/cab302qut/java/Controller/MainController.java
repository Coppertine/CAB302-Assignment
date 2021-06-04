package com.cab302qut.java.Controller;

import com.cab302qut.java.Items.Asset;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Text orgLabel;

    @FXML
    private Text userLabel;

    @FXML
    private Button logoutButton;
    @FXML
    private Button tradeButton;

    Asset[] assetList = new Asset[5];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // organisationLabel = new TextFlow(new Text("Testing"));
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        try {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void createTrade(ActionEvent actionEvent) throws IOException {
        try {

            assetList[0] = new Asset("test1",1);
            assetList[1] = new Asset("test2",2);
            assetList[2] = new Asset("test3",3);
            assetList[3] = new Asset("test4",4);
            assetList[4] = new Asset("test5",5);

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Trade.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            TradeController tradeController = loader.getController();
            tradeController.setChoiceBox(assetList);
            stage.setScene(new Scene(root));
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setOrganisationLabel(String organisationText){
        orgLabel.setText(organisationText);
    }
    public void setUserLabel(String userText){
        userLabel.setText(userText);
    }
}
