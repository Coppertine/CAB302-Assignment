package com.cab302qut.java;

import com.cab302qut.java.util.ServerConfiguration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CAB302Assignment extends Application {
    private static ServerConfiguration config;

    public static void main(String[] args) {
        launch(args);
    }

    public static ServerConfiguration getConfig() {
        return config;
    }

    public static void setConfig(ServerConfiguration config) {
        CAB302Assignment.config = config;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //System.out.println(getClass().getResource("main.fxml").getPath());
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("organisationTradeHistory.fxml"));

        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("styles/main.css").toExternalForm());

        primaryStage.setTitle("Assignment");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
