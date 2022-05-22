package com.example.virtualcurrencywallet_sef.AdministratorFunctions;

import com.example.virtualcurrencywallet_sef.Main;
import com.example.virtualcurrencywallet_sef.Model.FileHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
        FileHandler fileHandler=new FileHandler("src/main/java/com/example/virtualcurrencywallet_sef/Database/Commission.json");
        JSONArray jsonArray=fileHandler.read();
        JSONObject commission= (JSONObject) jsonArray.get(0);
        Double cms=(Double) commission.get("commission");
        label_CurrentCommission.setText("Current commission: " + cms*100 + "%");
    }
    @FXML
    public void modifyCommission(ActionEvent event)throws IOException, ParseException {
        FileHandler fileHandler=new FileHandler("src/main/java/com/example/virtualcurrencywallet_sef/Database/Commission.json");
        JSONArray jsonArray=fileHandler.read();
        JSONObject commission= (JSONObject) jsonArray.get(0);
        commission.replace("commission",Double.parseDouble(field_NewCommission.getText())/100);
        fileHandler.write(jsonArray);
        label_CommissionModified.setText("Commission modified");
        label_CurrentCommission.setText("Current commission: " + (Double)commission.get("commission")*100 + "%");
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("Menu_Admin.fxml");
    }
}
