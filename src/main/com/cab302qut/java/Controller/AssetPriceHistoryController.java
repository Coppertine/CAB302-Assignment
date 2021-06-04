package com.cab302qut.java.Controller;

import com.cab302qut.java.CAB302Assignment;
import com.cab302qut.java.Trades.Trade;
import com.cab302qut.java.Trades.TradeType;
import com.cab302qut.java.Users.User;
import com.cab302qut.java.util.AssetPriceHistoryObj;
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
    LineChart<Number,Number> priceChart;
    XYChart.Series<Number,Number> series1;

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

    public void GetMessage(){

        try {
            Message msg = new Message("GetTrades",null);
            CAB302Assignment.tradeClient.sendMessage(msg);
            //Object obj = CAB302Assignment.receivedMsg;
            //System.out.println(obj.getClass());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showPastMonth(ActionEvent event){
        try {
            event.consume();
            pastMonthTable();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void  pastMonthTable() throws SQLException {
        listTrade = FXCollections.observableArrayList();
        try {
            DatabaseConnection.establishConnection();
            ResultSet rs = DatabaseConnection.executeStatement("SELECT * FROM tradeHistory WHERE `assetID`=1;");

            while (rs.next()){

                listTrade.add(new AssetPriceHistoryObj(rs.getDate("date"),rs.getDouble("price")));
            }

        } catch (Exception e){
        }
        finally {
            DatabaseConnection.CloseConnection();
        }
    }

    public void SwitchGraph() throws SQLException {
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




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
//            xAxis.setLabel("Past year");
//            yAxis.setLabel("Price per unit");
//
//            priceChart.getXAxis().setAutoRanging(true);
//            priceChart.getYAxis().setAutoRanging(true);
//
//            priceChart.getData().clear();
//            Calendar calendar = Calendar.getInstance();
//            DatabaseConnection.establishConnection();
//            ResultSet rs = DatabaseConnection.executeStatement("SELECT * FROM `tradeHistory` WHERE YEAR(`date`) = 2020;");
//            series1 = new XYChart.Series<>();
//            while (rs.next()){
//                Number price = Integer.parseInt(rs.getString("price"));
//                calendar.setTime(rs.getDate("date"));
//                Number date = calendar.get(Calendar.DAY_OF_MONTH);
//                series1.getData().add(new XYChart.Data<>(date,price));
//            }
//            DatabaseConnection.CloseConnection();
//
//            this.series1.setName("Past Month");
//            this.priceChart.getData().add(series1);
//
//
//
//            col_date.setCellValueFactory(new PropertyValueFactory<AssetPriceHistoryObj,Date>("date"));
//            col_price.setCellValueFactory(new PropertyValueFactory<AssetPriceHistoryObj,Double>("price"));
//            pastMonthTable();
//            table_assetPriceHistory.setItems(listTrade);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        //xAxis.setLabel("Dates");
        //yAxis.setLabel("Price per unit");

        //series1 = new XYChart.Series<>();
//        series1.setName("Past 60 days");
//        for(int i = 0; i < 60;i++){
//            series1.getData().add(new XYChart.Data<>(i,i+1));
//
//        }
        //priceChart.getXAxis().setAutoRanging(true);
        //priceChart.getYAxis().setAutoRanging(true);
//
//        //priceChart.setAnimated(true);
//        priceChart.getData().add(series1);
    }
}
