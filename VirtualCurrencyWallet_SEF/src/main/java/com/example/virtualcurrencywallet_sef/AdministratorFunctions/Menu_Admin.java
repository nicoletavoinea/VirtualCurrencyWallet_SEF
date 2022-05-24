package com.example.virtualcurrencywallet_sef.AdministratorFunctions;

import com.example.virtualcurrencywallet_sef.Main;
import com.example.virtualcurrencywallet_sef.Model.Holder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.json.simple.JSONObject;

import java.io.IOException;

public class Menu_Admin {
    @FXML
    private Button button_ModifyExchangeRate;
    @FXML
    private Button button_AddNewCurrency;
    @FXML
    private Button button_ModifyCommission;
    @FXML
    private Button button_DisplayUsers;
    @FXML
    private Button button_logout;
    @FXML
    private Label label_welcome;

    public Menu_Admin(){}

    public void initialize(){
        Holder holder=Holder.getInstance();
        JSONObject user=holder.get();
        String name= (String) user.get("fullname");
        label_welcome.setText("Welcome, "+name);
    }

    @FXML
    public void addNewCurrency(ActionEvent event) throws IOException {
        Main m= new Main();
        m.changeScene("AddNewCurrency.fxml");
    }

    @FXML
    public void modifyExchangeRate(ActionEvent event) throws IOException{
        Main m= new Main();
        m.changeScene("ModifyExchangeRate.fxml");
    }

    @FXML
    public void modifyCommission(ActionEvent event) throws IOException{
        Main m= new Main();
        m.changeScene("ModifyCommission.fxml");
    }

    @FXML
    public void displayUsers(ActionEvent event) throws IOException{
        Main m= new Main();
        m.changeScene("DisplayUsers.fxml");
    }

    @FXML
    public void logout(ActionEvent event) throws IOException{
        Main m= new Main();
        m.changeScene("LogIn.fxml");
    }
}
