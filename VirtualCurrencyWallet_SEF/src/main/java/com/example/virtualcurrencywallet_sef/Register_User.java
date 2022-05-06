package com.example.virtualcurrencywallet_sef;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Register_User {
    @FXML
    Label label_InvalidAge;
    @FXML
    TextField field_FullName;
    @FXML
    TextField field_ID;
    @FXML
    TextField field_PhoneNumber;
    @FXML
    TextField field_HomeAdress;
    @FXML
    TextField field_CardNumber;
    @FXML
    PasswordField field_PIN;
    @FXML
    TextField field_Username;
    @FXML
    PasswordField field_Password;
    @FXML
    Button button_Register;
    @FXML
    Button button_Back;

    public Register_User(){}

    @FXML
    public void register(ActionEvent event) throws Exception{
        Main m= new Main();
        m.changeScene("Menu_User.fxml");
    }

    @FXML
    public void back(ActionEvent event) throws Exception{
        Main m= new Main();
        m.changeScene("LogIn.fxml");
    }
}
