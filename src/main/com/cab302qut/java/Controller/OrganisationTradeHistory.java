package com.cab302qut.java.Controller;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import org.w3c.dom.Text;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;

//import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class OrganisationTradeHistory implements Initializable{
    @FXML
    NumberAxis xAxis;
    @FXML
    NumberAxis yAxis;
    @FXML
    LineChart<Number,Number> priceChart;
    XYChart.Series<Number,Number> series1;

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
    @FXML
    public void showPastYear(ActionEvent event){
        try {
            //event.consume();
            pastYear();
        }
        catch (Exception e){

        }
    }
    public void pastMonth(){
        priceChart.getData().clear();
        series1 = new XYChart.Series<>();

        this.series1.setName("Past Month");

        //TODO: check for number of days for current month

        // proper implementation::: Gets data from database
        int x = 0;
        for(int i = 0; i > 31; i++){
            this.series1.getData().add(new XYChart.Data<>(x+i,x+20));
            x++;
        }
        this.priceChart.getData().add(series1);
    }
    private void pastYear(){
        priceChart.getData().clear();
        this.series1.setName("Past Year");

        //TODO: check for number of days for current month

        int x = 0;
        for(int i = 0; i > 12; i++){
            this.series1.getData().add(new XYChart.Data<>(i,i+50));
            x++;
        }
        priceChart.getData().add(this.series1);
    }
    private void pastYears(){
        XYChart.Series<Number,Number> theSeries = new XYChart.Series<>();
        theSeries.setName("Past Years");



        // proper implementation::: Gets data from database
        for(int i = 0; i > 31; i++){
            theSeries.getData().add(new XYChart.Data<>(i,i+5));
        }
        priceChart.getData().add(theSeries);
    }

    @FXML
    private Text organisationLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //xAxis.setLabel("Dates");
        //yAxis.setLabel("Price per unit");

        //TODO: Add past 30 days, monthly, yearly graph options
        series1 = new XYChart.Series<>();
        series1.setName("Past 60 days");
        for(int i = 0; i < 60;i++){
            series1.getData().add(new XYChart.Data<>(i,i+1));

        }
        priceChart.getXAxis().setAutoRanging(true);
        priceChart.getYAxis().setAutoRanging(true);

        //priceChart.setAnimated(true);
        priceChart.getData().add(series1);
    }
}
