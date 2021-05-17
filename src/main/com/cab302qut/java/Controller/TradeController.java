package com.cab302qut.java.Controller;

import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Trades.Order;
import com.cab302qut.java.Trades.OrderType;
import com.cab302qut.java.Users.User;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import com.cab302qut.java.Trades.Trade;

import java.io.IOException;
import java.time.LocalDate;
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

    private Asset asset;
    private int quantity;
    private double price;
    private User testUser;

//need to add different assets to choice box
//then when clicked they are displayed to the user

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void sendOrder(ActionEvent actionEvent) throws IOException {
        checkOrder();
    }

    /**
     * checks order and then submits order to server
     */
    private void checkOrder() {
        asset = (Asset) assetChoice.getSelectionModel().getSelectedItem();

        try {
            quantity = Integer.parseInt(assetQuantity.getText().toString());
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        try {
            price = Double.parseDouble(assetPrice.getText().toString());
        }
        catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        Date date = new Date();
        if (buyButton.isSelected()) {
            Order buyOrder = new Order(asset, OrderType.BUY, quantity, price, testUser, date);
            System.out.println(buyOrder.getPrice() + " " + buyOrder.getTradeAsset() + buyOrder.getQuantityToTrade() + OrderType.BUY);
        } else if (sellButton.isSelected()) {
            Order sellOrder = new Order(asset, OrderType.SELL, quantity, price, testUser, date);
            System.out.println(sellOrder.getPrice() + " " + sellOrder.getTradeAsset() + sellOrder.getQuantityToTrade()+ OrderType.SELL);
        } else {
            //throw error
        }

    }


}
