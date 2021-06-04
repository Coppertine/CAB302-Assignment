package com.cab302qut.java.Controller;

import com.cab302qut.java.CAB302Assignment;
import com.cab302qut.java.Trades.Trade;
import com.cab302qut.java.Trades.TradeType;
import com.cab302qut.java.Users.User;
import com.cab302qut.java.util.AssetPriceHistoryObj;
import com.cab302qut.java.util.AssetTableObj;
import com.cab302qut.java.util.DatabaseConnection;
import com.cab302qut.java.util.Message;
import com.sun.source.tree.NewArrayTree;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.w3c.dom.Text;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.sql.Date;

public class AssetPriceHistoryController implements Initializable{
    @FXML
    private TableView<AssetPriceHistoryObj> table_assetPriceHistory;
    @FXML
    private TableColumn<AssetPriceHistoryObj,Date> col_date;
    @FXML
    private TableColumn<AssetPriceHistoryObj,Double> col_price;
    @FXML
    private Text organisationLabel;

    ObservableList<AssetPriceHistoryObj> listTrade;

    @FXML
    NumberAxis xAxis;
    @FXML
    NumberAxis yAxis;
    @FXML
    LineChart<Number,Double> priceChart;
    XYChart.Series<Number,Double> series1;

    @FXML
    Button btn_logout;

    @FXML
    Button btn_viewGraph;

    @FXML
    ChoiceBox<String> choiceBox;

    public void Logout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        Stage window = (Stage) btn_logout.getScene().getWindow();
        Scene scene = new Scene(root);
        window.setScene(scene);
    }

    /**
     * Gets an asset's price history from server.
     */
    public void GetPriceData(){

        try {
            ArrayList<Integer> blank = new ArrayList<>();
            Message msg = new Message("GetTrades",blank);
            CAB302Assignment.tradeClient.sendMessage(msg);
            Refresh();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Updates price data
     */
    @SuppressWarnings("unchecked")
    public void Refresh() {
        try {
            Message obj = CAB302Assignment.assetData;
            listTrade = FXCollections.observableArrayList();
            ArrayList<AssetTableObj> data = (ArrayList<AssetTableObj>) obj.getMessageObject();
            data.forEach((row) -> listTrade.add(new AssetPriceHistoryObj(row.getDate(),row.getPrice())));
            table_assetPriceHistory.setItems(listTrade);
            SetGraph();

        } catch (Exception e)   {
            System.out.println(e.getMessage());
        }
    }

    public void SwitchView() throws SQLException {
        if (priceChart.isVisible()) {
            priceChart.setVisible(false);
            table_assetPriceHistory.setVisible(true);
            btn_viewGraph.setText("Graph view");
        }
        else {
            priceChart.setVisible(true);
            table_assetPriceHistory.setVisible(false);
            btn_viewGraph.setText("Table view");
        }
    }

    public void SetGraph() {
        Message obj = CAB302Assignment.assetData;
        Calendar calendar = Calendar.getInstance();
        this.series1 = new XYChart.Series<>();
        ArrayList<AssetTableObj> data = (ArrayList<AssetTableObj>) obj.getMessageObject();

        for (int i = 0; i < data.size(); i++) {
            AssetTableObj row = data.get(i);
            calendar.setTime(row.getDate());
            Number date = calendar.get(Calendar.DAY_OF_MONTH);
            this.series1.getData().add(new XYChart.Data<>(date, row.getPrice()));
        }

        this.series1.setName("Past Year");
        this.priceChart.getData().add(series1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.xAxis.setLabel("Past year");
            this.yAxis.setLabel("Price per unit");

            this.priceChart.setAnimated(false);
            this.priceChart.getXAxis().setAutoRanging(true);
            this.priceChart.getYAxis().setAutoRanging(true);
            this.priceChart.getData().clear();

            this.col_date.setCellValueFactory(new PropertyValueFactory<AssetPriceHistoryObj,Date>("date"));
            this.col_price.setCellValueFactory(new PropertyValueFactory<AssetPriceHistoryObj,Double>("price"));

            table_assetPriceHistory.setItems(listTrade);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
