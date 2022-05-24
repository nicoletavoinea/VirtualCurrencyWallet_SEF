package com.example.virtualcurrencywallet_sef.UserFunctions;

import com.example.virtualcurrencywallet_sef.Main;
import com.example.virtualcurrencywallet_sef.Model.Encryptor;
import com.example.virtualcurrencywallet_sef.Model.FileHandler;
import com.example.virtualcurrencywallet_sef.Model.Holder;
import com.example.virtualcurrencywallet_sef.Model.User;
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

public class AddMoney {
    @FXML
    private Label label_invalidcredentials;
    @FXML
    private Label label_successful;
    @FXML
    private TextField field_sum;
    @FXML
    private TextField field_cardnumber;
    @FXML
    private TextField field_pin;
    @FXML
    private Button button_back;
    @FXML
    private Button button_add;

    @FXML
    private void back(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("Menu_User.fxml");
    }

    @FXML
    private void add(ActionEvent event) throws IOException, ParseException {
        if(!InvalidFields()) {
            User user = new User();
            FileHandler fileHandler = new FileHandler("Users.json");
            JSONArray jsonArray = fileHandler.read();

            Holder holder = Holder.getInstance();
            JSONObject objectH = holder.get();

            int userposition = user.usernameExists((String) objectH.get("username"));
            JSONObject objectToReplace = (JSONObject) jsonArray.get(userposition);
            if (objectH.get("cardnumber").equals(field_cardnumber.getText())) {
                if (Encryptor.verify(field_pin.getText(), (String) objectH.get("PIN"), (String) objectH.get("cardnumber"))) {
                    ArrayList<Double> sumsarray = (ArrayList<Double>) objectH.get("sums");
                    double initialsum = sumsarray.get(0);
                    double finalsum = initialsum + Double.parseDouble(field_sum.getText());
                    sumsarray.set(0, finalsum);
                    objectToReplace.replace("sums", sumsarray);
                    fileHandler.write(jsonArray);
                    label_successful.setTextFill(Paint.valueOf("#1eba27"));
                    label_successful.setText("Money added successfully");
                    label_invalidcredentials.setText("");
                } else {
                    label_invalidcredentials.setText("Wrong card or PIN");
                    label_successful.setText("");
                }
            } else {
                label_invalidcredentials.setText("Wrong card or PIN");
                label_successful.setText("");
            }
        }
    }

    public boolean InvalidFields(){
        double sum;
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
        label_successful.setTextFill(Paint.valueOf("#1eba27"));//green
        label_successful.setText("");
        if(field_cardnumber.getText().isEmpty()){
            label_invalidcredentials.setTextFill(Paint.valueOf("#bc1d1d"));//red
            label_invalidcredentials.setText("Please insert the card number");
            return true;
        }
        if(field_pin.getText().isEmpty()){
            label_invalidcredentials.setTextFill(Paint.valueOf("#bc1d1d"));//red
            label_invalidcredentials.setText("Please insert the pin");
            return true;
        }
        return false;
    }
}
