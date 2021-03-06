package com.example.virtualcurrencywallet_sef.UserFunctions;

import com.example.virtualcurrencywallet_sef.Main;
import com.example.virtualcurrencywallet_sef.Model.Currency;
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
import java.math.BigInteger;
import java.util.ArrayList;

public class ExchangeCurrency {
    @FXML
    private ChoiceBox choice_from;
    @FXML
    private ChoiceBox choice_to;
    @FXML
    private Label label_successful;
    @FXML
    private Label label_preview;
    @FXML
    private Label label_currency;
    @FXML
    private Button button_back;
    @FXML
    private Button button_exchange;
    @FXML
    private TextField field_sum;

    public void initialize() throws IOException, ParseException {
        //setting items in choice boxes

        FileHandler fileHandler=new FileHandler("Currencies.json");
        JSONArray currenciesArray=fileHandler.read();
        ArrayList<String> availableCurrencies=new ArrayList<>();
        ArrayList<String> allCurrencies=new ArrayList<>();
        Holder holder=Holder.getInstance();
        JSONObject objectH=holder.get();
        ArrayList<Double> sumsarray= (ArrayList<Double>) objectH.get("sums");

        for(int i=0;i<currenciesArray.size();i++){
            JSONObject currentCurrency= (JSONObject) currenciesArray.get(i);
            allCurrencies.add((String) currentCurrency.get("name"));
            if((sumsarray.get(i))>(Double) 0.0) availableCurrencies.add((String) currentCurrency.get("name"));
        }

        choice_from.getItems().addAll(availableCurrencies);
        choice_to.getItems().addAll(allCurrencies);


        //set currency label
        choice_from.setOnAction(e-> {
            label_currency.setText((String) choice_from.getValue());
        });

    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("Menu_User.fxml");
    }
    @FXML
    private void exchange(ActionEvent event) throws IOException, ParseException {
        if(!InvalidFields()) {

            FileHandler fileHandler = new FileHandler("Currencies.json");
            JSONArray currenciesArray = fileHandler.read();
            Holder holder = Holder.getInstance();
            JSONObject objectH = holder.get();
            ArrayList<Double> sumsarray = (ArrayList<Double>) objectH.get("sums");


            //get owned sums in both currencies
            int indexfrom = Currency.getPosition(String.valueOf(choice_from.getValue()));
            int indexto = Currency.getPosition(String.valueOf(choice_to.getValue()));
            double availablesum = sumsarray.get(indexfrom);
            double sumOfSecondCurrency = sumsarray.get(indexto);

            //get rates of both currencies
            JSONObject fromcurrency = (JSONObject) currenciesArray.get(Currency.getPosition(String.valueOf(choice_from.getValue())));
            double fromrate = (double) fromcurrency.get("rate");
            JSONObject tocurrency = (JSONObject) currenciesArray.get(Currency.getPosition(String.valueOf(choice_to.getValue())));
            double torate = (double) tocurrency.get("rate");


            //get commission
            FileHandler cms = new FileHandler("Commission.json");
            JSONArray commissionObject = cms.read();
            double commission = (double) ((JSONObject) commissionObject.get(0)).get("commission");

            FileHandler fh = new FileHandler("Users.json");
            JSONArray usersArray = fh.read();
            User user = new User();
            int userposition = user.usernameExists((String) objectH.get("username"));
            JSONObject objectToReplace = (JSONObject) usersArray.get(userposition);

            //exchange algorithm
            double toadd;
            double sum = Double.parseDouble(field_sum.getText());
            if (sum < availablesum) {
                toadd = sum * fromrate * (1 - commission);
                toadd = toadd / torate;
                sumOfSecondCurrency = sumOfSecondCurrency + toadd;
                availablesum = availablesum - sum;
                sumsarray.set(indexfrom, availablesum);
                sumsarray.set(indexto, sumOfSecondCurrency);
                objectToReplace.replace("sums", sumsarray);
                fh.write(usersArray);
                label_successful.setTextFill(Paint.valueOf("#1eba27"));
                label_successful.setText("Exchange completed successfully");
            } else {
                label_successful.setTextFill(Paint.valueOf("#bc1d1d"));
                label_successful.setText("Insufficient funds in chosen currency");
            }
        }

    }

    public boolean InvalidFields(){
        double sum;
        if(choice_from.getValue()==null || choice_to.getValue()==null){
            label_successful.setTextFill(Paint.valueOf("#bc1d1d"));//red
            label_successful.setText("Please choose exchanging currencies");
            return true;
        }
        if(field_sum.getText().isEmpty()){
            label_successful.setTextFill(Paint.valueOf("#bc1d1d"));//red
            label_successful.setText("Please insert a sum");
            return true;
        }
        else try{
            sum=Double.parseDouble(field_sum.getText());
            if(sum<0){
                label_successful.setTextFill(Paint.valueOf("#bc1d1d"));//red
                label_successful.setText("Sum can't be a negative number");
                return true;
            }
        }catch (NumberFormatException e){
            label_successful.setTextFill(Paint.valueOf("#bc1d1d"));//red
            label_successful.setText("Sum can contain only digits");
            return true;
        }
        return false;
    }


}
