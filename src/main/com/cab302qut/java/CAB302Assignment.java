package com.cab302qut.java;

import com.cab302qut.java.util.ServerConfiguration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import java.io.IOException;
import java.net.URL;

public class CAB302Assignment extends Application {
    private static ServerConfiguration config;
    private static String[] Args;
    private static final String configFile = "./config.ini";

    public static void main(String[] args) {
        Args = args;

        ServerConfiguration configTemp = new ServerConfiguration();
        try {
            configTemp.reloadConfiguration(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        config=configTemp;
        launch(args);
    }

    public static ServerConfiguration getConfig() {
        return config;
    }

    public static void setConfig(ServerConfiguration config) {
        CAB302Assignment.config = config;
        try {
            config.reloadConfiguration(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //System.out.println(getClass().getResource("main.fxml").getPath());
        URL fxmlURL = getClass().getClassLoader().getResource("main.fxml");
        if (Args.length > 0 && Args[0].equals("-server")) {
            fxmlURL = getClass().getClassLoader().getResource("server.fxml");
        }
        assert fxmlURL != null;
        Parent root = FXMLLoader.load(fxmlURL);

        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("styles/main.css").toExternalForm());

        primaryStage.setTitle("Assignment");
        primaryStage.setScene(scene);
        primaryStage.show();
//        TrayNotification tray = new TrayNotification("Hello World", "You got Mail!",
//                NotificationType.INFORMATION);
//        tray.setAnimationType(AnimationType.POPUP);
//        tray.showAndDismiss(Duration.seconds(2));

    }
}
