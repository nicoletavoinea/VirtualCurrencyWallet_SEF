package com.example.virtualcurrencywallet_sef.Authentication;

import com.example.virtualcurrencywallet_sef.Model.*;
import com.example.virtualcurrencywallet_sef.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

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
    @FXML
    private Button button_RegisterAdmin;

    public LogIn(){
    }

    @FXML
    private void login(ActionEvent event) throws IOException, ParseException {
        if(!existEmptyFields()){
            logInUser();
            logInAdmin();
        }


    }

    @FXML
    private void toRegisterPage(ActionEvent event) throws IOException {
        Main m=new Main();
        m.changeScene("Register_User.fxml");
    }

    @FXML
    private void register_admin(ActionEvent event)throws IOException{
        Main m=new Main();
        m.changeScene("Register_Admin.fxml");
    }

    public boolean existEmptyFields(){
        if(field_username.getText().isEmpty()){
            label_loginerror.setText("You must enter your username");
            return true;
        }
        if(field_password.getText().isEmpty()){
            label_loginerror.setText("You must enter your password");
            return true;
        }
        return false;
    }

    public void logInUser() throws IOException, ParseException {
        User user=new User();
        FileHandler fileHandler=new FileHandler("src/main/java/com/example/virtualcurrencywallet_sef/Database/Users.json");
        JSONArray jsonArray=fileHandler.read();

        int userPosition=user.usernameExists(field_username.getText());
        if(userPosition!=-1){
            JSONObject jsonObject= (JSONObject) jsonArray.get(userPosition);

            if(Encryptor.verify(field_password.getText(),(String)jsonObject.get("password"),(String)jsonObject.get("username"))){
                Holder holder=Holder.getInstance();
                holder.set(jsonObject);
                Main m=new Main();
                m.changeScene("Menu_User.fxml");
            }
            else{
                label_loginerror.setText("Incorrect username or password");
            }
        }

    }
    public void logInAdmin() throws IOException, ParseException {
        Admin admin=new Admin();
        FileHandler fileHandler=new FileHandler("src/main/java/com/example/virtualcurrencywallet_sef/Database/Admins.json");
        JSONArray jsonArray=fileHandler.read();


        int adminPosition=admin.adminExists(field_username.getText());
        if(adminPosition!=-1){
            JSONObject jsonObject= (JSONObject) jsonArray.get(adminPosition);
            if(Encryptor.verify(field_password.getText(),(String)jsonObject.get("password"),(String)jsonObject.get("username"))){
                Holder holder=Holder.getInstance();
                holder.set(jsonObject);
                Main m=new Main();
                m.changeScene("Menu_Admin.fxml");
            }
            else{
                label_loginerror.setText("Incorrect username or password");
            }
        }

    }

}