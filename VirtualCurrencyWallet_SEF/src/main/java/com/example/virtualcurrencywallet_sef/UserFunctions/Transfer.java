package com.example.virtualcurrencywallet_sef.UserFunctions;

import com.example.virtualcurrencywallet_sef.Main;
import com.example.virtualcurrencywallet_sef.Model.FileHandler;
import com.example.virtualcurrencywallet_sef.Model.Holder;
import com.example.virtualcurrencywallet_sef.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.channels.AcceptPendingException;
import java.security.PublicKey;
import java.util.ArrayList;

public class Transfer {

    @FXML
    private TextField field_FullName;
    @FXML
    private TextField field_PID;
    @FXML
    private TextField field_Amount;
    @FXML
    private ChoiceBox choice_Currency;
    @FXML
    private Button button_Transfer;
    @FXML
    private Button button_Back;
    @FXML
    private Label label_InsufficientFunds;

    public Transfer(){}

    public void initialize() throws IOException, ParseException {
        FileHandler fileHandler=new FileHandler("Currencies.json");
        JSONArray currenciesArray=fileHandler.read();
        ArrayList<String> availableCurrencies=new ArrayList<>();
        Holder holder=Holder.getInstance();
        JSONObject objectH=holder.get();
        ArrayList<Double> sumsarray= (ArrayList<Double>) objectH.get("sums");

        for(int i=0;i<currenciesArray.size();i++){
            JSONObject currentCurrency= (JSONObject) currenciesArray.get(i);
            if((sumsarray.get(i))>(Double) 0.0) availableCurrencies.add((String) currentCurrency.get("name"));
        }

        choice_Currency.getItems().addAll(availableCurrencies);

    }


    @FXML
    public void transfer(ActionEvent event) throws IOException, ParseException {
        if(!InvalidFields()) {
            Holder holder = Holder.getInstance();
            JSONObject current = holder.get();
            FileHandler cms = new FileHandler("Commission.json");
            JSONArray commission = cms.read();
            double c = (double) ((JSONObject) commission.get(0)).get("commission");
            FileHandler fileHandler = new FileHandler("Users.json");
            JSONArray jsonArray = fileHandler.read();
            int i;      //returns the index of the user;
            User user = new User();
            int index_from = user.usernameExists((String) current.get("username"));
            System.out.println("Indexul meu:" + index_from);
            for (i = 0; i < jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                if (object.get("fullname").equals(field_FullName.getText()) && object.get("ID").equals(field_PID.getText()))
                    break;
            }
            if (i == jsonArray.size()) {
                label_InsufficientFunds.setTextFill(Paint.valueOf("#8a1919"));
                label_InsufficientFunds.setText("User not found");
                return;
            }
            FileHandler fileHandler2 = new FileHandler("Currencies.json");
            JSONArray currencies = fileHandler2.read();
            int j;  //retains the index of the currency;
            for (j = 0; j < currencies.size(); j++) {
                JSONObject currency = (JSONObject) currencies.get(j);
                if (currency.get("name").equals(choice_Currency.getValue()))
                    break;
            }
            double sum = Double.parseDouble(field_Amount.getText());
            ArrayList<Double> sumsarray = (ArrayList<Double>) current.get("sums");
            if (sumsarray.get(j) >= sum) {
                sumsarray.set(j, sumsarray.get(j) - sum);
                JSONObject from = (JSONObject) jsonArray.get(index_from);
                from.replace("sums", sumsarray);
                jsonArray.set(index_from, from);
                JSONObject object = (JSONObject) jsonArray.get(i);
                sumsarray = (ArrayList<Double>) object.get("sums");
                sumsarray.set(j, sumsarray.get(j) + (sum * (1.0 - c)));
                object.replace("sums", sumsarray);
                fileHandler.write(jsonArray);
                label_InsufficientFunds.setTextFill(Paint.valueOf("#1eba27"));
                label_InsufficientFunds.setText("Transfer complete");
            } else {
                label_InsufficientFunds.setTextFill(Paint.valueOf("#8a1919"));
                label_InsufficientFunds.setText("Insufficient funds");
            }
        }
    }

    public boolean InvalidFields(){
        double sum;
        if(field_FullName.getText().isEmpty()){
            label_InsufficientFunds.setTextFill(Paint.valueOf("#bc1d1d"));//red
            label_InsufficientFunds.setText("Please insert the full name of the user you want to trasfer to.");
            return true;
        }
        if(field_PID.getText().isEmpty()){
            label_InsufficientFunds.setTextFill(Paint.valueOf("#bc1d1d"));//red
            label_InsufficientFunds.setText("Please insert the ID of the user you want to trasfer to.");
            return true;
        }
        if(choice_Currency.getValue()==null){
            label_InsufficientFunds.setTextFill(Paint.valueOf("#bc1d1d"));//red
            label_InsufficientFunds.setText("Please choose a currency");
            return true;
        }
        if(field_Amount.getText().isEmpty()){
            label_InsufficientFunds.setTextFill(Paint.valueOf("#bc1d1d"));//red
            label_InsufficientFunds.setText("Please insert a transfer amount.");
            return true;
        }
        else try{
            sum=Double.parseDouble(field_Amount.getText());
            if(sum<0){
                label_InsufficientFunds.setTextFill(Paint.valueOf("#bc1d1d"));//red
                label_InsufficientFunds.setText("Amount can't be a negative number");
                return true;
            }
        }catch (NumberFormatException e){
            label_InsufficientFunds.setTextFill(Paint.valueOf("#bc1d1d"));//red
            label_InsufficientFunds.setText("Amount can contain only digits");
            return true;
        }
        return false;
    }
    @FXML
    public void back(ActionEvent event) throws IOException{
        Main m=new Main();
        m.changeScene("Menu_User.fxml");
    }
}
