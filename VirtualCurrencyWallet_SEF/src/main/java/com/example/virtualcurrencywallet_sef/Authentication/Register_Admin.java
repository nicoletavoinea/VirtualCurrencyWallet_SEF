package com.example.virtualcurrencywallet_sef.Authentication;

import com.example.virtualcurrencywallet_sef.Main;
import com.example.virtualcurrencywallet_sef.Model.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.parser.ParseException;

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
        checkEmptyFields();
        checkValidInfo();
        //Main m= new Main();
      //  m.changeScene("Menu_Admin.fxml");
    }

    @FXML
    public void back(ActionEvent event) throws Exception{
        Main m= new Main();
        m.changeScene("LogIn.fxml");
    }

    public void checkEmptyFields(){
        if(field_FullName.getText().isEmpty()){
            label_Error.setText("You must enter your full name");
        }
        else if (field_ID.getText().isEmpty()){
            label_Error.setText("You must enter your ID");
        }
        else if (field_PhoneNumber.getText().isEmpty()){
            label_Error.setText("You must enter your phone number");
        }
        else if(field_Username.getText().isEmpty()){
            label_Error.setText("You must choose an username");
        }
        else if(field_Password.getText().isEmpty()){
            label_Error.setText("Password can't be empty");
        }
        else if(field_AdminPIN.getText().isEmpty()){
            label_Error.setText("Admin PIN cannot be empty");
        }
    }

    public void checkValidInfo() throws ParseException, IOException {
        Admin admin=new Admin();
        System.out.println(admin.adminExists(field_Username.getText()));

    }
}
