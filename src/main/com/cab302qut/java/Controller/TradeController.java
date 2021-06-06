package com.cab302qut.java.Controller;

import com.cab302qut.java.CAB302Assignment;
import com.cab302qut.java.Items.Asset;
import com.cab302qut.java.Trades.Order;
import com.cab302qut.java.Trades.OrderType;
import com.cab302qut.java.Trades.TradeException;
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
import java.sql.Date;
import java.util.ResourceBundle;

public class TradeController implements Initializable{

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
    private ObservableList<String> existingOrgAssets;

    Order order;
    boolean orderReady;
//need to add different assets to choice box
//then when clicked they are displayed to the user

    /**
     * @param actionEvent
     * @throws IOException
     */
    public void sendOrder(ActionEvent actionEvent) throws IOException {
        checkOrder();
    }


    public void back(ActionEvent actionEvent) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setCreditAmount(String creditAmountInput) {
        creditAmount.setText(creditAmountInput);
    }

    public void setChoiceBox() {

        existingOrgAssets = FXCollections.observableArrayList();
        for (ArrayList<String> row : StaticVariables.orgsAssets)
        {
            System.out.println(row.get(0));
            existingOrgAssets.add(row.get(0)); // get orgName
        }
        assetChoice.setItems(FXCollections.observableArrayList(existingOrgAssets));
    }

    /**
     * checks order and then submits order to server
     */
    private void checkOrder() {
        try {
            quantity = Integer.parseInt(assetQuantity.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        try {
            price = Double.parseDouble(assetPrice.getText());
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
        asset = new Asset(assetChoice.getSelectionModel().getSelectedItem().toString(), 0);

        Date date = new Date(System.currentTimeMillis());
        try
        {
            if (buyButton.isSelected())
            {
                order = new Order(asset, OrderType.BUY, quantity, price, StaticVariables.user, date);
                System.out.println(order.getPrice() + " " + order.getTradeAsset() + order.getQuantityToTrade() + OrderType.BUY);
                orderReady = true;
            } else if (sellButton.isSelected())
            {
                order = new Order(asset, OrderType.SELL, quantity, price, StaticVariables.user, date);
                System.out.println(order.getPrice() + " " + order.getTradeAsset() + order.getQuantityToTrade() + OrderType.SELL);
                orderReady = true;
            } else
            {
                throw new TradeException("Trade type not selected.");
            }
        }catch (ClassCastException | TradeException C){
            System.out.println(C);

        }
        if (orderReady){
            orderReady = false;

            Message msg = new Message("Order",order);
            CAB302Assignment.tradeClient.sendMessage(msg);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setChoiceBox();
        Message newMsg = new Message("GetOrgsList");
        CAB302Assignment.tradeClient.sendMessage(newMsg);
        while (StaticVariables.organisationList == null) {
            System.out.println("Waiting for orgs credits");
        }
        // get the credits of the org selected
        for (ArrayList<String> row: StaticVariables.organisationList) {
            if (row.get(0).equals(StaticVariables.userOrganisation.getName())) {
                StaticVariables.organisationCredits = Integer.parseInt(row.get(1));
                creditAmount.setText(row.get(1));
                break;
            }
        }
    }
}
