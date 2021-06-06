package com.cab302qut.java.Controller;

import com.cab302qut.java.CAB302Assignment;
import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Trades.Order;
import com.cab302qut.java.Trades.OrderType;
import com.cab302qut.java.Users.User;
import com.cab302qut.java.util.Message;
import com.cab302qut.java.util.StaticVariables;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import com.cab302qut.java.Trades.Trade;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class TradeController implements Initializable {

    @FXML
    private ChoiceBox assetChoice;
    @FXML
    private ToggleButton buyButton;
    @FXML
    private ToggleButton sellButton;
    @FXML
    private TextField assetQuantity;
    @FXML
    private TextField assetPrice;
    @FXML
    private Button backButton;
    @FXML
    private Button sendOrder;

    @FXML
    private Label creditAmount;
    private Asset asset ;
    private int quantity;
    private double price;
    private ObservableList<String> existingOrgAssets;
    private boolean orderCorrect;
    Order order;
//need to add different assets to choice box
//then when clicked they are displayed to the user


    public void sendOrder(ActionEvent actionEvent) throws IOException
    {
        checkOrder();
    }

    public void back(ActionEvent actionEvent) throws IOException
    {
        try
        {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void setCreditAmount(String creditAmountInput)
    {
        creditAmount.setText(creditAmountInput);
    }

    public void setChoiceBox()
    {
        String orgName = StaticVariables.user.getOrganisation().getName();
//        Message msg = new Message("GetOrgsAsset", orgName);
//        CAB302Assignment.tradeClient.sendMessage(msg);
//        System.out.println("Sent edit org credits num");

        if (StaticVariables.orgsAssets == null || StaticVariables.orgsAssets.isEmpty()) {
            StaticVariables.orgsAssets = null; //reset for new org selected
            Message msg = new Message("GetOrgsAsset", orgName);
            CAB302Assignment.tradeClient.sendMessage(msg);
        }
        try
        {
            while (StaticVariables.orgsAssets == null)
            {
                System.out.println("Waiting for orgs assets on trade controller");
            }

            existingOrgAssets = FXCollections.observableArrayList();
            for (ArrayList<String> row : StaticVariables.orgsAssets)
            {
                System.out.println(row.get(0));
                existingOrgAssets.add(row.get(0)); // get orgName
            }
            assetChoice.setItems(FXCollections.observableArrayList(existingOrgAssets));

        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * checks order and then submits order to server
     */
    private void checkOrder()
    {

        asset = new Asset(assetChoice.getSelectionModel().getSelectedItem().toString(), 0);
        if (asset == null)
        {
            //throw error
        }
        Date date = new Date();
        try
        {
            quantity = Integer.parseInt(assetQuantity.getText().toString());
        } catch (NumberFormatException ex)
        {
            ex.printStackTrace();
        }
        try
        {
            price = Double.parseDouble(assetPrice.getText().toString());
        } catch (NumberFormatException ex)
        {
            ex.printStackTrace();
        }
        if (buyButton.isSelected())
        {
            order = new Order(asset, OrderType.BUY, quantity, price, StaticVariables.user, date);
            System.out.println(order.getPrice() + " " + order.getTradeAsset() + order.getQuantityToTrade() + OrderType.BUY);
            orderCorrect = true;

        } else if (sellButton.isSelected())
        {
            order = new Order(asset, OrderType.SELL, quantity, price, StaticVariables.user, date);
            System.out.println(order.getPrice() + " " + order.getTradeAsset() + order.getQuantityToTrade() + OrderType.SELL);
            orderCorrect = true;
        } else
        {
            //throw error
        }

        if (orderCorrect){
            orderCorrect = false;
            try {
                    Message msg = new Message("CreateTrade",order);
                    CAB302Assignment.tradeClient.sendMessage(msg);

            } catch (Exception e) {

            }

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setChoiceBox();

    }
}
