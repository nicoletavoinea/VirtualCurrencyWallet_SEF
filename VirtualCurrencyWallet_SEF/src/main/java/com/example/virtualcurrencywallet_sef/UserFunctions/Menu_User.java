package com.example.virtualcurrencywallet_sef.UserFunctions;

import com.example.virtualcurrencywallet_sef.Main;
import com.example.virtualcurrencywallet_sef.Model.Holder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.json.simple.JSONObject;

import java.io.IOException;

public class Menu_User {

    @FXML
    private Button button_logout;
    @FXML
    private Button button_addmoney;
    @FXML
    private Button button_exchange;
    @FXML
    private Button button_transfer;
    @FXML
    private Label label_welcome;
    @FXML
    private Button button_view;

    public Menu_User(){
    }

    public void initialize(){
        Holder holder=Holder.getInstance();
        JSONObject user=holder.get();
        String name= (String) user.get("fullname");
        label_welcome.setText("Welcome, "+name);
    }

    @FXML
    private void addMoney(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("AddMoney.fxml");
    }

    @FXML
    private void viewWallet(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("ViewWallet.fxml");
    }

    @FXML
    private void exchangeCurrency(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("ExchangeCurrency.fxml");
    }

    @FXML
    private void transfer(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("Transfer.fxml");
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("LogIn.fxml");
    }


}
