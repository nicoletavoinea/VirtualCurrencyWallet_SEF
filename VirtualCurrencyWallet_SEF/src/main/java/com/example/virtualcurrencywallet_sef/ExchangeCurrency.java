package com.example.virtualcurrencywallet_sef;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;

public class ExchangeCurrency {
    @FXML
    private ChoiceBox choice_from;
    @FXML
    private ChoiceBox choice_to;
    @FXML
    private Label label_successful;
    @FXML
    private Label label_preview;
    @FXML
    private Button button_back;
    @FXML
    private Button button_exchange;

    @FXML
    private void back(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("Menu.fxml");
    }
    @FXML
    private void exchange(ActionEvent event){
        //code
        label_successful.setText("Exchange completed successfully");
    }


}
