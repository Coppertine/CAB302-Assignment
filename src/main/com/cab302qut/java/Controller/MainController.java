package com.cab302qut.java.Controller;

import com.cab302qut.java.CAB302Assignment;
import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.util.Message;
import com.cab302qut.java.util.StaticVariables;
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
import java.util.ArrayList;
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

    ArrayList<String> assetList = new ArrayList<String>();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        System.out.println(StaticVariables.user.getName() +" Name " + StaticVariables.user.getOrganisation().getName() + "User org name");
        Message msg = new Message("GetOrgsAsset", StaticVariables.user.getOrganisation().getName());
        CAB302Assignment.tradeClient.sendMessage(msg);
        System.out.println("Sent edit org credits num");
        // organisationLabel = new TextFlow(new Text("Testing"));
    }

    public void logout(ActionEvent actionEvent) throws IOException
    {
        try
        {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void createTrade(ActionEvent actionEvent) throws IOException
    {

        try
        {

            while (StaticVariables.orgsAssets == null)
            {
                System.out.println("Waiting for orgs assets main controller");
            }
            for (ArrayList<String> row : StaticVariables.orgsAssets)
            {
                assetList.add(row.get(0));
            }

            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Trade.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            //TradeController tradeController = loader.getController();
            //tradeController.setChoiceBox(assetList);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setOrganisationLabel(String organisationText)
    {
        orgLabel.setText(organisationText);
    }

    public void setUserLabel(String userText)
    {
        userLabel.setText(userText);
    }
}
