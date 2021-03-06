package com.example.virtualcurrencywallet_sef.AdministratorFunctions;

import com.example.virtualcurrencywallet_sef.Model.FileHandler;
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

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


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
    public void addCurrency(ActionEvent event) throws IOException, ParseException {
        if (!InvalidFields()) {
            FileHandler fileHandler = new FileHandler("Currencies.json");
            JSONArray currencies = fileHandler.read();
            Currency newcurrency = new Currency(field_CurrencyName.getText(), Double.parseDouble(field_CurrencyRate.getText()));

            currencies.add(newcurrency.currencyJSON());
            fileHandler.write(currencies);
            fileHandler = new FileHandler("Users.json");
            JSONArray users = fileHandler.read();
            for (int i = 0; i < users.size(); i++) {
                JSONObject user = (JSONObject) users.get(i);
                ArrayList<Double> sums = new ArrayList<>();
                sums = (ArrayList<Double>) user.get("sums");
                sums.add(0.0);
                user.replace("sums", sums);
            }
            fileHandler.write(users);
            label_AlreadyExists.setTextFill(Paint.valueOf("#1eba27"));
            label_AlreadyExists.setText("Successfuly added.");
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
            label_AlreadyExists.setText("Please insert the rate.");
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
        if(newcurrency.alreadyExists(field_CurrencyName.getText())!=-1) {
            label_AlreadyExists.setTextFill(Paint.valueOf("#bc1d1d"));
            label_AlreadyExists.setText("Currency already exists!");
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
