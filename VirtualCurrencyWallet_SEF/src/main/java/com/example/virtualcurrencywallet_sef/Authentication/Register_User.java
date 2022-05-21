package com.example.virtualcurrencywallet_sef.Authentication;

import com.example.virtualcurrencywallet_sef.Model.FileHandler;
import com.example.virtualcurrencywallet_sef.Main;
import com.example.virtualcurrencywallet_sef.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Register_User {
    @FXML
    Label label_Error;
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
        if(checkEmptyFields()==0 && checkValidInfo()){
            FileHandler fileHandler=new FileHandler("src/main/java/com/example/virtualcurrencywallet_sef/Database/Users.json");
            JSONArray jsonArray=fileHandler.read();
            User user=new User(field_FullName.getText(),field_ID.getText(),field_PhoneNumber.getText(),field_HomeAdress.getText(),field_CardNumber.getText(),field_PIN.getText(),field_Username.getText(),field_Password.getText());
            jsonArray.add(user.userJSON());
            fileHandler.write(jsonArray);
            Main m= new Main();
            m.changeScene("Menu_User.fxml");
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
        else if(field_HomeAdress.getText().isEmpty()){
            label_Error.setText("You must enter your home address");
            return 1;
        }
        else if(field_CardNumber.getText().isEmpty()){
            label_Error.setText("You must enter your card number");
            return 1;
        }
        else if(field_PIN.getText().isEmpty()){
            label_Error.setText("You must enter your card PIN");
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

        return 0;
    }

    public boolean checkValidInfo() throws IOException, ParseException {
        User user=new User();
        if(user.usernameExists(field_Username.getText())!=-1) {
            label_Error.setText("Username already exists :( Try a new one");
            return false;
        }
        if(user.isUnderage(field_ID.getText())){
            label_Error.setText("User can not be underage");
            return false;
        }
        return true;
    }
}
