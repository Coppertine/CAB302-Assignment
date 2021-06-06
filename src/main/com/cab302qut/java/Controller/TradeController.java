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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class TradeController {

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

    private ObservableList<String> existingOrgAssets2;
    @FXML
    private Label assetBuyName;
    @FXML
    private Label assetSellName;
    @FXML
    private Label salePrice;
    @FXML
    private Label buyPrice;
    @FXML
    private Label creditAmount;

    private Asset asset;
    private int quantity;
    private double price;
    private User testUser;

//need to add different assets to choice box
//then when clicked they are displayed to the user

    /**
     * @param actionEvent
     * @throws IOException
     */
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

    public void setChoiceBox()
    {
        try
        {
            Message msg = new Message("CreateTrade");
            CAB302Assignment.tradeClient.sendMessage(msg);


            while (StaticVariables.orgsAssets == null)
            {
                System.out.println("Waiting for orgs assets");
            }

            existingOrgAssets2 = FXCollections.observableArrayList();
            for (ArrayList<String> row : StaticVariables.orgsAssets)
            {
                existingOrgAssets2.add(row.get(0)); // get orgName
            }
            assetChoice.setItems(FXCollections.observableArrayList(existingOrgAssets2));


        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * checks order and then submits order to server
     */
    private void checkOrder()
    {

        asset = (Asset) assetChoice.getSelectionModel().getSelectedItem();
        if (asset == null)
        {
            //throw error
        }
        Date date = new Date();

        if (buyButton.isSelected())
        {
            Order buyOrder = new Order(asset, OrderType.BUY, quantity, price, testUser, date);
            System.out.println(buyOrder.getPrice() + " " + buyOrder.getTradeAsset() + buyOrder.getQuantityToTrade() + OrderType.BUY);
        } else if (sellButton.isSelected())
        {
            Order sellOrder = new Order(asset, OrderType.SELL, quantity, price, testUser, date);
            System.out.println(sellOrder.getPrice() + " " + sellOrder.getTradeAsset() + sellOrder.getQuantityToTrade() + OrderType.SELL);
        } else
        {
            //throw error
        }
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


    }

}
