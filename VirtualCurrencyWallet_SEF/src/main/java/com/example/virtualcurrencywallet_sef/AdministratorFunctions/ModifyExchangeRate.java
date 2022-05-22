package com.example.virtualcurrencywallet_sef.AdministratorFunctions;

import com.example.virtualcurrencywallet_sef.Main;
import com.example.virtualcurrencywallet_sef.Model.Currency;
import com.example.virtualcurrencywallet_sef.Model.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class ModifyExchangeRate {
    @FXML
    private TextField field_CurrencyName;
    @FXML
    private TextField field_CurrencyRate;
    @FXML
    private Button button_ModifyRate;
    @FXML
    private Button button_Back;
    @FXML
    private Label label_AlreadyExists;

    public ModifyExchangeRate(){}

    @FXML
    public void modifyRate(ActionEvent event) throws IOException, ParseException {
        FileHandler fileHandler=new FileHandler("src/main/java/com/example/virtualcurrencywallet_sef/Database/Currencies.json");
        JSONArray currencies=fileHandler.read();
        for(int i=0; i<currencies.size();i++)
        {
            JSONObject object= (JSONObject) currencies.get(i);
            if(field_CurrencyName.getText().equals(object.get("name")))
            {
                object.replace("rate",Double.parseDouble(field_CurrencyRate.getText()));
                fileHandler.write(currencies);
                //currencies.set(i,object);
                label_AlreadyExists.setTextFill(Paint.valueOf("#1eba27"));
                label_AlreadyExists.setText("Rate changed successfully");
                return;
            }
        }
        label_AlreadyExists.setTextFill(Paint.valueOf("#bc1d1d"));
        label_AlreadyExists.setText("Currency not found");
        return;

    }

    @FXML
    public void back(ActionEvent event) throws IOException{
        Main m=new Main();
        m.changeScene("Menu_Admin.fxml");
    }
}
