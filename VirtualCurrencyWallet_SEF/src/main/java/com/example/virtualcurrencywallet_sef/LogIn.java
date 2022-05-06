package com.example.virtualcurrencywallet_sef;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogIn {
    @FXML
    private Label label_loginerror;
    @FXML
    private Button button_login;
    @FXML
    private Button button_register;
    @FXML
    private TextField field_username;
    @FXML
    private PasswordField field_password;

    private void login(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("menu.fxml");

    }
}