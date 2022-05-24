package com.example.virtualcurrencywallet_sef.Authentication;

import com.example.virtualcurrencywallet_sef.Model.FileHandler;
import com.example.virtualcurrencywallet_sef.Main;
import com.example.virtualcurrencywallet_sef.Model.Admin;
import com.example.virtualcurrencywallet_sef.Model.Holder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.math.BigInteger;

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

    private static final String ADMIN_PIN="100911";

    public Register_Admin(){}

    @FXML
    public void register(ActionEvent event) throws Exception{
        if(checkInvalidFields()==0){
            FileHandler fileHandler=new FileHandler("src/main/java/com/example/virtualcurrencywallet_sef/Database/Admins.json");
            JSONArray admins= fileHandler.read();
            Admin newadmin= new Admin(field_FullName.getText(),field_ID.getText(),field_PhoneNumber.getText(),field_Username.getText(),field_Password.getText(),field_AdminPIN.getText());
            admins.add(newadmin.adminJSON());
            fileHandler.write(admins);
            Holder holder=Holder.getInstance();
            holder.set(newadmin.adminJSON());
            Main m= new Main();
            m.changeScene("Menu_Admin.fxml");
        }
    }

    @FXML
    public void back(ActionEvent event) throws Exception{
        Main m= new Main();
        m.changeScene("LogIn.fxml");
    }

    public int checkInvalidFields() throws ParseException, IOException {
        Admin admin=new Admin();
        BigInteger bigInteger;

        if(field_FullName.getText().isEmpty()){
            label_Error.setText("You must enter your full name");
            return 1;
        }
        if (field_ID.getText().isEmpty()){
            label_Error.setText("You must enter your ID");
            return 1;
        }
        else try{
                bigInteger=new BigInteger(field_ID.getText());
                System.out.println(bigInteger);
            }catch (NumberFormatException e){
                label_Error.setText("ID can not contain letters or special characters");
                return 1;
            }
        if(field_ID.getText().length()!=13){
            label_Error.setText("ID must be 13 digits long");
            return 1;
        }
        if(admin.isUnderage(field_ID.getText()))
        {
            label_Error.setText("Admin cannot be underage.");
            return 1;
        }
        if (field_PhoneNumber.getText().isEmpty()){
            label_Error.setText("You must enter your phone number");
            return 1;
        }
        else try{
            bigInteger=new BigInteger(field_PhoneNumber.getText());
            System.out.println(bigInteger);
        }catch (NumberFormatException e){
            label_Error.setText("Phone number can not contain letters or special characters");
            return 1;
        }
        if(field_PhoneNumber.getText().length()!=10){
            label_Error.setText("Phone number must be 10 digits long");
            return 1;
        }
        if(field_Username.getText().isEmpty()){
            label_Error.setText("You must choose an username");
            return 1;
        }
        if(admin.adminExists(field_Username.getText())!=-1)
        {
            label_Error.setText("Username already exists. Try a new one");
            return 1;
        }
        if(field_Password.getText().isEmpty()){
            label_Error.setText("Password can't be empty");
            return 1;
        }
        if(field_AdminPIN.getText().isEmpty()){
            label_Error.setText("Admin PIN cannot be empty");
            return 1;
        }
        if(!field_AdminPIN.getText().equals(ADMIN_PIN)){
            label_Error.setText("Wrong AdminPIN");
            return 1;
        }
        return 0;
    }




}
