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
        FileHandler fileHandler=new FileHandler("src/main/java/com/example/virtualcurrencywallet_sef/Database/Currencies.json");
        JSONArray currencies=fileHandler.read();
        Currency newcurrency=new Currency(field_CurrencyName.getText(),Double.parseDouble(field_CurrencyRate.getText()));
        if(newcurrency.alreadyExists(field_CurrencyName.getText())!=-1)
            label_AlreadyExists.setText("Currency already exists!");
        else {
            currencies.add(newcurrency.currencyJSON());
            fileHandler.write(currencies);
            label_AlreadyExists.setText("Successful");
            fileHandler= new FileHandler("src/main/java/com/example/virtualcurrencywallet_sef/Database/Users.json");
            JSONArray users=fileHandler.read();
            for(int i=0;i<users.size();i++){
                JSONObject user= (JSONObject) users.get(i);
                ArrayList<Double> sums=new ArrayList<>();
                sums= (ArrayList<Double>) user.get("sums");
                sums.add(0.0);
                user.replace("sums",sums);
            }
            fileHandler.write(users);
        }
    }

    @FXML
    public void back(ActionEvent event) throws IOException{
        Main m=new Main();
        m.changeScene("Menu_Admin.fxml");
    }
}
