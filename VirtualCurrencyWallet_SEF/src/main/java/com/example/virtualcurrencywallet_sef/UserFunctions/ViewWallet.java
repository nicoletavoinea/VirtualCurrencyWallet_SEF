package com.example.virtualcurrencywallet_sef.UserFunctions;

import com.example.virtualcurrencywallet_sef.Main;
import com.example.virtualcurrencywallet_sef.Model.Currency;
import com.example.virtualcurrencywallet_sef.Model.FileHandler;
import com.example.virtualcurrencywallet_sef.Model.Holder;
import com.example.virtualcurrencywallet_sef.Model.User;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class ViewWallet {

    @FXML
    private TableView<Currency> table_wallet=new TableView<>();
    @FXML
    private TableView<Currency> table_amount;
    @FXML
    private TableColumn<Currency, String> column_currency;
    @FXML
    private TableColumn<Currency, Double> column_rate;
    @FXML
    private TableColumn<Currency, Double> column_amount;
    @FXML
    private Button button_Back;

    @FXML
    private void back(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("Menu_User.fxml");
    }

    public void initialize() throws IOException, ParseException {
        FileHandler fileHandler=new FileHandler("Currencies.json");
        JSONArray currenciesArray =fileHandler.read();
        ArrayList<Currency> currencies=new ArrayList<>();

        Holder holder=Holder.getInstance();
        JSONObject user=holder.get();
        ArrayList<Double> sumsarray= (ArrayList<Double>) user.get("sums");



        column_currency.setCellValueFactory(new PropertyValueFactory<Currency,String>("name"));
        column_rate.setCellValueFactory(new PropertyValueFactory<Currency,Double>("rate"));
        column_amount.setCellValueFactory(new PropertyValueFactory<Currency,Double>("rate"));
        Currency c;

        for (int i=0;i<currenciesArray.size();i++) {
            JSONObject co= (JSONObject) currenciesArray.get(i);
            table_wallet.getItems().add(new Currency((String) co.get("name"), (Double) co.get("rate")));
            table_amount.getItems().add(new Currency((String) co.get("name"),(sumsarray.get(i))));

        }



    }




}
