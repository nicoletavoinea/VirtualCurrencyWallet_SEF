package com.example.virtualcurrencywallet_sef;

import com.example.virtualcurrencywallet_sef.Database.FileHandler;
import com.example.virtualcurrencywallet_sef.Model.Admin;
import com.example.virtualcurrencywallet_sef.Model.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main extends Application {
    private static Stage stg;

    @Override
    public void start(Stage primarystage) throws Exception {
        /*User user=new User("fn","id","pn","adr","cn","pin","un","pw");
        User user2=new User("fn2","id2","pn2","adr2","cn2","pin2","un2","pw2");
        FileWriter fw=new FileWriter("src/main/java/com/example/virtualcurrencywallet_sef/Database/Users.json",true);
        fw.write(user.userJSON().toJSONString()+"\n");
        fw.write(user2.userJSON().toJSONString()+"\n");
        fw.flush();
        fw.close();*/

       /* Admin admin=new Admin("fullname","ID","075","admin","psw","PIN");
        Admin admin2=new Admin("fullname22","ID22","07522","admin222","psw222","PIN222");
        FileWriter fileWriter=new FileWriter("src/main/java/com/example/virtualcurrencywallet_sef/Database/Admins.json");
        fileWriter.write(admin.adminJSON().toJSONString());
        fileWriter.write(admin2.adminJSON().toJSONString());
        fileWriter.flush();
        fileWriter.close();*/


        /*JSONParser jp=new JSONParser();
        FileReader fr=new FileReader("src/main/java/com/example/virtualcurrencywallet_sef/Database/Users.json");
        Scanner sc=new Scanner(fr);
        Object obj=jp.parse(fr);
        JSONArray jobj=(JSONArray) obj;
        JSONObject user1=(JSONObject) jobj.get(0);
        System.out.println( (String) user1.get("username"));*/

      /*  JSONArray users= FileHandler.readusers();
        JSONObject user1= (JSONObject) users.get(1);
        user1.put("username","schimbat");
        FileHandler.writeusers(users);*/


        stg=primarystage;
        primarystage.setResizable(false);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LogIn.fxml")));
        primarystage.setTitle("Virtual Currency Wallet");
        primarystage.setScene(new Scene(root, 900,700));
        primarystage.show();
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane= FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}