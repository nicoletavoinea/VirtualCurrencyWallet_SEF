package com.example.virtualcurrencywallet_sef.Authentication;

import com.example.virtualcurrencywallet_sef.Model.FileHandler;
import com.example.virtualcurrencywallet_sef.Main;
import com.example.virtualcurrencywallet_sef.Model.Holder;
import com.example.virtualcurrencywallet_sef.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

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
        if(checkInvalidFields()==0){
            FileHandler fileHandler=new FileHandler("src/main/java/com/example/virtualcurrencywallet_sef/Database/Users.json");

            JSONArray jsonArray = fileHandler.read();
            User user=new User(field_FullName.getText(),field_ID.getText(),field_PhoneNumber.getText(),field_HomeAdress.getText(),field_CardNumber.getText(),field_PIN.getText(),field_Username.getText(),field_Password.getText());
            jsonArray.add(user.userJSON());
            fileHandler.write(jsonArray);
            Holder holder=Holder.getInstance();
            holder.set(user.userJSON());
            Main m= new Main();
            m.changeScene("Menu_User.fxml");
        }

    }

    @FXML
    public void back(ActionEvent event) throws Exception{
        Main m= new Main();
        m.changeScene("LogIn.fxml");
    }

    public int checkInvalidFields() throws IOException, ParseException {
        int number;
        BigInteger bignumber;
        User user=new User();

        if(field_FullName.getText().isEmpty()){
            label_Error.setText("You must enter your full name");
            return 1;
        }
        if (field_ID.getText().isEmpty()){
                label_Error.setText("You must enter your ID");
                return 1;
        }
        else try{
            bignumber=new BigInteger(field_ID.getText());
            System.out.println(bignumber);
        }catch (NumberFormatException e){
            label_Error.setText("ID can not contain letters or special characters");
            return 1;
        }
        if(field_ID.getText().length()!=13){
            label_Error.setText("ID must be 13 digits long");
            return 1;
        }
        if(user.isUnderage(field_ID.getText())){
            label_Error.setText("User can not be underage");
            return 1;
        }
        if (field_PhoneNumber.getText().isEmpty()){
            label_Error.setText("You must enter your phone number");
            return 1;
        }
        else try{
            bignumber=new BigInteger(field_PhoneNumber.getText());
            System.out.println(bignumber);
        }catch (NumberFormatException e){
            label_Error.setText("Phone number can not contain letters or special characters");
            return 1;
        }
        if(field_PhoneNumber.getText().length()!=10){
            label_Error.setText("Phone number must be 10 digits long");
            return 1;
        }

        if(field_HomeAdress.getText().isEmpty()){
            label_Error.setText("You must enter your home address");
            return 1;
        }
        if(field_CardNumber.getText().isEmpty()){
            label_Error.setText("You must enter your card number");
            return 1;
        }
        else try{
            bignumber=new BigInteger(field_CardNumber.getText());
            System.out.println(bignumber);
        }catch (NumberFormatException e){
            label_Error.setText("Card number can not contain letters or special characters");
            return 1;
        }
        if(field_CardNumber.getText().length()!=16){
            label_Error.setText("Card number must be 16 digits long");
            return 1;
        }
        if(field_PIN.getText().isEmpty()){
            label_Error.setText("You must enter your card PIN");
            return 1;
        }
        else try{
            System.out.println(Integer.parseInt(field_PIN.getText()));
        }catch (NumberFormatException e){
            label_Error.setText("PIN can not contain letters or special characters");
            return 1;
        }
        if(field_PIN.getText().length()!=4){
            label_Error.setText("PIN number must be 4 digits long");
            return 1;
        }
        if(field_Username.getText().isEmpty()){
            label_Error.setText("You must choose an username");
            return 1;
        }
        if(user.usernameExists(field_Username.getText())!=-1) {
            label_Error.setText("Username already exists :( Try a new one");
            return 1;
        }
        if(field_Password.getText().isEmpty()){
            label_Error.setText("Password can not be null");
            return 1;
        }

        return 0;
    }

}
