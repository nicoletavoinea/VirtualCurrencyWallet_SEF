package com.example.virtualcurrencywallet_sef;

import com.example.virtualcurrencywallet_sef.Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main extends Application {
    private static Stage stg;

    @Override
    public void start(Stage primarystage) throws Exception {
        stg=primarystage;
        primarystage.setResizable(false);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LogIn.fxml")));
        primarystage.setTitle("Virtual Currency Wallet");
        primarystage.setScene(new Scene(root, 900,700));
        primarystage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane= FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args){
        launch(args);
    }
}