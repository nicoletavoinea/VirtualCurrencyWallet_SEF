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
        if(!InvalidFields()) {
            FileHandler fileHandler = new FileHandler("Currencies.json");
            JSONArray currencies = fileHandler.read();
            for (int i = 0; i < currencies.size(); i++) {
                JSONObject object = (JSONObject) currencies.get(i);
                if (field_CurrencyName.getText().equals(object.get("name"))) {
                    object.replace("rate", Double.parseDouble(field_CurrencyRate.getText()));
                    fileHandler.write(currencies);
                    //currencies.set(i,object);
                    label_AlreadyExists.setTextFill(Paint.valueOf("#1eba27"));
                    label_AlreadyExists.setText("Rate changed successfully");
                    return;
                }
            }
        }

    }

    public boolean InvalidFields() throws IOException, ParseException {
        double rate;
        if(field_CurrencyName.getText().isEmpty()){
            label_AlreadyExists.setTextFill(Paint.valueOf("#bc1d1d"));
            label_AlreadyExists.setText("Please insert the name of the currency.");
            return true;
        }
        if(field_CurrencyRate.getText().isEmpty()){
            label_AlreadyExists.setTextFill(Paint.valueOf("#bc1d1d"));//red
            label_AlreadyExists.setText("Please insert the new rate.");
            return true;
        }
        else try{
            rate=Double.parseDouble(field_CurrencyRate.getText());
            if(rate<0){
                label_AlreadyExists.setTextFill(Paint.valueOf("#bc1d1d"));//red
                label_AlreadyExists.setText("Rate can't be a negative number");
                return true;
            }
        }catch (NumberFormatException e){
            label_AlreadyExists.setTextFill(Paint.valueOf("#bc1d1d"));//red
            label_AlreadyExists.setText("Rate can contain only digits");
            return true;
        }
        Currency newcurrency=new Currency(field_CurrencyName.getText(),Double.parseDouble(field_CurrencyRate.getText()));
        if(newcurrency.alreadyExists(field_CurrencyName.getText())==-1) {
            label_AlreadyExists.setTextFill(Paint.valueOf("#bc1d1d"));
            label_AlreadyExists.setText("Currency does not exist");
            return true;
        }
        return false;
    }

    @FXML
    public void back(ActionEvent event) throws IOException{
        Main m=new Main();
        m.changeScene("Menu_Admin.fxml");
    }
}
