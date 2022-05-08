package com.example.virtualcurrencywallet_sef.AdministratorFunctions;

import com.example.virtualcurrencywallet_sef.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;


public class AddNewCurrency {
    @FXML
    private TextField field_CurrencyName;
    @FXML
    private TextField field_CurrencyRate;
    @FXML
    private Label label_AlreadyExists;
    @FXML
    private Button button_AddCurrency;
    @FXML
    private Button button_Back;

    public AddNewCurrency(){}

    @FXML
    public void addCurrency(ActionEvent event) throws IOException{
        label_AlreadyExists.setText("Currency added");
    }

    @FXML
    public void back(ActionEvent event) throws IOException{
        Main m=new Main();
        m.changeScene("Menu_Admin.fxml");
    }
}
