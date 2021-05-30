package com.cab302qut.java.Controller;

import com.cab302qut.java.Trades.Trade;
import com.cab302qut.java.Trades.TradeType;
import com.cab302qut.java.Users.User;
import com.cab302qut.java.util.AssetPriceHistoryObj;
import com.cab302qut.java.util.DatabaseConnection;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class OrganisationTradeHistory implements Initializable{
    @FXML
    private TableView<AssetPriceHistoryObj> table_assetPriceHistory;
    @FXML
    private TableColumn<AssetPriceHistoryObj,Date> col_date;
    @FXML
    private TableColumn<AssetPriceHistoryObj,Double> col_price;

    ObservableList<AssetPriceHistoryObj> listTrade;

    int index = -1;

    @FXML
    NumberAxis xAxis;
    @FXML
    NumberAxis yAxis;
    @FXML
    LineChart<Number,Number> priceChart;
    XYChart.Series<Number,Number> series1;

    @FXML
    Button buttonToMain;

    //private Stage stage;
    //private Scene scene;
    //private Parent root;

//    public void switchToMain(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("main.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//
//    }

    public void switchToMain(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
        Stage window = (Stage) buttonToMain.getScene().getWindow();
        Scene scene = new Scene(root);
        window.setScene(scene);
    }

    @FXML
    public void showPastMonth(ActionEvent event){
        try {
            //event.consume();
            pastMonth();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void  pastMonthh() throws SQLException {
        listTrade = FXCollections.observableArrayList();
        try {
            DatabaseConnection.establishConnection();
            ResultSet rs = DatabaseConnection.executeStatement("SELECT * FROM tradeHistory WHERE `assetID`=1;");

            int i = 0;
            while (rs.next()){

                listTrade.add(new AssetPriceHistoryObj(rs.getDate("date"),rs.getDouble("price")));
                //System.out.println(listTrade.get(i).price);
                i++;
            }

        } catch (Exception e){

        }
        finally {
            DatabaseConnection.CloseConnection();
        }
    }

    public void pastMonth() throws SQLException {
        priceChart.getData().clear();
        Calendar calendar = Calendar.getInstance();
        DatabaseConnection.establishConnection();
        ResultSet rs = DatabaseConnection.executeStatement("SELECT * FROM `tradeHistory`;");
        series1 = new XYChart.Series<>();
        while (rs.next()){
            Number price = Integer.parseInt(rs.getString("price"));
            calendar.setTime(rs.getDate("date"));
            Number date = calendar.get(Calendar.DAY_OF_MONTH);
            series1.getData().add(new XYChart.Data<>(date,price));
        }
        DatabaseConnection.CloseConnection();

        this.series1.setName("Past Month");
        this.priceChart.getData().add(series1);
    }


    @FXML
    private Text organisationLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            col_date.setCellValueFactory(new PropertyValueFactory<AssetPriceHistoryObj,Date>("date"));
            col_price.setCellValueFactory(new PropertyValueFactory<AssetPriceHistoryObj,Double>("price"));
            pastMonthh();
            table_assetPriceHistory.setItems(listTrade);
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
