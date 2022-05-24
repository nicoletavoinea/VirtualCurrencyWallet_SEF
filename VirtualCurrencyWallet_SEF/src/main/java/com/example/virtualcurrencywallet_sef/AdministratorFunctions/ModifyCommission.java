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

public class ModifyCommission {
    @FXML
    private Label label_CurrentCommission;
    @FXML
    private TextField field_NewCommission;
    @FXML
    private Button button_ModifyCommission;
    @FXML
    private Button button_Back;
    @FXML
    private Label label_CommissionModified;

    public ModifyCommission(){}

    public void initialize() throws IOException, ParseException {
        FileHandler fileHandler=new FileHandler("Commission.json");
        JSONArray jsonArray=fileHandler.read();
        JSONObject commission= (JSONObject) jsonArray.get(0);
        Double cms=(Double) commission.get("commission");
        label_CurrentCommission.setText("Current commission: " + cms*100 + "%");
    }
    @FXML
    public void modifyCommission(ActionEvent event)throws IOException, ParseException {
        if(!InvalidFields()) {
            FileHandler fileHandler = new FileHandler("Commission.json");
            JSONArray jsonArray = fileHandler.read();
            JSONObject commission = (JSONObject) jsonArray.get(0);
            commission.replace("commission", Double.parseDouble(field_NewCommission.getText()) / 100);
            fileHandler.write(jsonArray);
            label_CommissionModified.setTextFill(Paint.valueOf("#1eba27"));
            label_CommissionModified.setText("Commission modified");
            label_CurrentCommission.setText("Current commission: " + (Double) commission.get("commission") * 100 + "%");
        }
    }

    public boolean InvalidFields() throws IOException, ParseException {
        double commission;
        if(field_NewCommission.getText().isEmpty()){
            label_CommissionModified.setTextFill(Paint.valueOf("#bc1d1d"));
            label_CommissionModified.setText("Please insert the new commission.");
            return true;
        }
        else try{
            commission=Double.parseDouble(field_NewCommission.getText());
            if(commission<0){
                label_CommissionModified.setTextFill(Paint.valueOf("#bc1d1d"));//red
                label_CommissionModified.setText("Commission can't be a negative number");
                return true;
            }
            if(commission>=100){
                label_CommissionModified.setTextFill(Paint.valueOf("#bc1d1d"));//red
                label_CommissionModified.setText("Commission must be lower than 100%");
                return true;
            }
        }catch (NumberFormatException e){
            label_CommissionModified.setTextFill(Paint.valueOf("#bc1d1d"));//red
            label_CommissionModified.setText("Commission can contain only digits");
            return true;
        }

        return false;
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("Menu_Admin.fxml");
    }
}
