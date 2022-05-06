package com.example.virtualcurrencywallet_sef;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

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
    private void back(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("Menu_User.fxml");
    }
}
