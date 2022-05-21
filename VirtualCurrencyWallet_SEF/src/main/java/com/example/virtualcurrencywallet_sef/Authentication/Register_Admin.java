package com.example.virtualcurrencywallet_sef.Authentication;

import com.example.virtualcurrencywallet_sef.Database.FileHandler;
import com.example.virtualcurrencywallet_sef.Main;
import com.example.virtualcurrencywallet_sef.Model.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;

public class Register_Admin {
    @FXML
    Label label_Error;
    @FXML
    TextField field_FullName;
    @FXML
    TextField field_ID;
    @FXML
    TextField field_PhoneNumber;
    @FXML
    TextField field_Username;
    @FXML
    PasswordField field_Password;
    @FXML
    PasswordField field_AdminPIN;
    @FXML
    Button button_Register;
    @FXML
    Button button_Back;

    public Register_Admin(){}

    @FXML
    public void register(ActionEvent event) throws Exception{
        if(checkEmptyFields()==0 && checkValidInfo()==1){
            FileHandler fileHandler=new FileHandler("src/main/java/com/example/virtualcurrencywallet_sef/Database/Admins.json");
            JSONArray admins= fileHandler.readusers();
            Admin newadmin= new Admin(field_FullName.getText(),field_ID.getText(),field_PhoneNumber.getText(),field_Username.getText(),field_Password.getText(),field_AdminPIN.getText());
            admins.add(newadmin.adminJSON());
            fileHandler.writeusers(admins);
            // Main m= new Main();
            //  m.changeScene("Menu_Admin.fxml");
        }
    }

    @FXML
    public void back(ActionEvent event) throws Exception{
        Main m= new Main();
        m.changeScene("LogIn.fxml");
    }

    public int checkEmptyFields(){
        if(field_FullName.getText().isEmpty()){
            label_Error.setText("You must enter your full name");
            return 1;
        }
        else if (field_ID.getText().isEmpty()){
            label_Error.setText("You must enter your ID");
            return 1;
        }
        else if (field_PhoneNumber.getText().isEmpty()){
            label_Error.setText("You must enter your phone number");
            return 1;
        }
        else if(field_Username.getText().isEmpty()){
            label_Error.setText("You must choose an username");
            return 1;
        }
        else if(field_Password.getText().isEmpty()){
            label_Error.setText("Password can't be empty");
            return 1;
        }
        else if(field_AdminPIN.getText().isEmpty()){
            label_Error.setText("Admin PIN cannot be empty");
            return 1;
        }
        return 0;
    }

    public int checkValidInfo() throws ParseException, IOException {
        Admin admin=new Admin();

        if(admin.adminExists(field_Username.getText())!=-1)
        {
            label_Error.setText("Username already exists. Try a new one");
            return 0;
        }
        if(admin.isUnderage(field_ID.getText()))
        {
            label_Error.setText("Admin cannot be underage.");
            return 0;
        }
        return 1;
    }



}
