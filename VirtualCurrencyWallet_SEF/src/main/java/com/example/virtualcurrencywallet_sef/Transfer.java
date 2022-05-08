package com.example.virtualcurrencywallet_sef;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.channels.AcceptPendingException;
import java.security.PublicKey;

public class Transfer {

    @FXML
    private TextField field_FullName;
    @FXML
    private TextField field_PID;
    @FXML
    private TextField field_Amount;
    @FXML
    private ChoiceBox choice_Currency;
    @FXML
    private Button button_Transfer;
    @FXML
    private Button button_Back;
    @FXML
    private Label label_InsufficientFunds;

    public Transfer(){}

    @FXML
    public void transfer(ActionEvent event) throws IOException{
        label_InsufficientFunds.setText("Transfer complete");
    }
    @FXML
    public void back(ActionEvent event) throws IOException{
        Main m=new Main();
        m.changeScene("Menu_User.fxml");
    }
}
