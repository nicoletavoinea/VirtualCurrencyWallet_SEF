package com.example.virtualcurrencywallet_sef.UserFunctions;

import com.example.virtualcurrencywallet_sef.Main;
import com.example.virtualcurrencywallet_sef.Model.FileHandler;
import com.example.virtualcurrencywallet_sef.Model.Holder;
import com.example.virtualcurrencywallet_sef.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        User user=new User();
        FileHandler fileHandler=new FileHandler("src/main/java/com/example/virtualcurrencywallet_sef/Database/Users.json");
        JSONArray jsonArray=fileHandler.read();

        Holder holder=Holder.getInstance();
        JSONObject objectH=holder.get();

        int userposition= user.usernameExists((String) objectH.get("username"));
        JSONObject objectToReplace= (JSONObject) jsonArray.get(userposition);

        if(objectH.get("cardnumber").equals(field_cardnumber.getText())){
            if(objectH.get("PIN").equals(field_pin.getText())) {
                ArrayList<Double> sumsarray= (ArrayList<Double>) objectH.get("sums");
                double initialsum=sumsarray.get(0);
                double finalsum=initialsum + Double.parseDouble(field_sum.getText());
                sumsarray.set(0,finalsum);
                objectToReplace.replace("sums",sumsarray);
                fileHandler.write(jsonArray);
                label_successful.setText("Money added successfully");
            }
            else{
                label_successful.setText("Wrong PIN");
            }
        }
        else
        {
            label_successful.setText("Wrong card number");
        }
    }
}
