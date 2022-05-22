package com.example.virtualcurrencywallet_sef.AdministratorFunctions;

import com.example.virtualcurrencywallet_sef.Main;
import com.example.virtualcurrencywallet_sef.Model.FileHandler;
import com.example.virtualcurrencywallet_sef.Model.User;
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

public class DisplayUsers {
    @FXML
    private Button button_Back;
    @FXML
    private TableView<User> table_Users;
    @FXML
    private TableColumn<User,String> fullname;
    @FXML
    private TableColumn<User,String> ID;
    @FXML
    private TableColumn<User,String> phonenumber;
    @FXML
    private TableColumn<User,String> adress;

    ObservableList<User> list; //=FXCollections.observableArrayList(new User("nume1","id1","pn1","adress1","cn1","pin1","user","pass"),
            //new User("nume2","id2","pn2","adress2","cn2","pin2","user2","pass2"));

    private ArrayList<User> users=new ArrayList<>();

    public DisplayUsers() throws IOException, ParseException {
    }

    public void initialize() throws IOException, ParseException {
        fullname.setCellValueFactory(new PropertyValueFactory<User,String>("fullname"));
        ID.setCellValueFactory(new PropertyValueFactory<User,String>("ID"));
        phonenumber.setCellValueFactory(new PropertyValueFactory<User,String>("phonenumber"));
        adress.setCellValueFactory(new PropertyValueFactory<User,String>("adress"));
        FileHandler fileHandler=new FileHandler("src/main/java/com/example/virtualcurrencywallet_sef/Database/Users.json");
        JSONArray JSONusers=fileHandler.read();
        for (int i=0;i<JSONusers.size();i++) {
            JSONObject user= (JSONObject) JSONusers.get(i);
               users.add(User.JSONtoSimpleUser(user));
        }
        list=FXCollections.observableArrayList(users);
        table_Users.setItems(list);
    }



    @FXML
    public void back(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("Menu_Admin.fxml");
    }
}
