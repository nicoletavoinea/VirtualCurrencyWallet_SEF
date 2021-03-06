package com.example.virtualcurrencywallet_sef.Model;

import com.example.virtualcurrencywallet_sef.Model.FileHandler;
import javafx.util.Duration;
import javafx.util.converter.LocalDateStringConverter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;
public class User {
    private String fullname;
    private String ID;
    private String phonenumber;
    private String adress;
    private String cardnumber;
    private String PIN;
    private String username;
    private String password;
    private ArrayList <Double> sums;

    public User(){
        this.fullname = null;
        this.ID = null;
        this.phonenumber = null;
        this.adress = null;
        this.cardnumber = null;
        this.PIN = null;
        this.username = null;
        this.password = null;
        this.sums=null;
    }


    public User(String fullname, String ID, String phonenumber, String adress, String cardnumber, String PIN, String username, String password) throws IOException, ParseException {
        this.fullname = fullname;
        this.ID = ID;
        this.phonenumber = phonenumber;
        this.adress = adress;
        this.cardnumber = cardnumber;
        this.PIN = Encryptor.encrypt(PIN,cardnumber);
        this.username = username;
        this.password = Encryptor.encrypt(password,username);
        this.sums=new ArrayList<>();
        FileHandler readcurrencies= new FileHandler("Currencies.json");
        JSONArray currencies= readcurrencies.read();
        for(int i=0;i<currencies.size();i++){
            this.sums.add(0.0);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "fullname='" + fullname + '\'' +
                ", ID='" + ID + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", adress='" + adress + '\'' +
                ", cardnumber='" + cardnumber + '\'' +
                ", PIN=" + PIN +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return PIN.equals(user.PIN) && fullname.equals(user.fullname) && ID.equals(user.ID) && phonenumber.equals(user.phonenumber) && adress.equals(user.adress) && cardnumber.equals(user.cardnumber) && username.equals(user.username) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullname, ID, phonenumber, adress, cardnumber, PIN, username, password);
    }

    public JSONObject userJSON(){
        JSONObject obj=new JSONObject();
        obj.put("fullname",this.fullname);
        obj.put("ID",this.ID);
        obj.put("phonenumber",this.phonenumber);
        obj.put("adress",this.adress);
        obj.put("cardnumber",this.cardnumber);
        obj.put("PIN",this.PIN);
        obj.put("username",this.username);
        obj.put("password",this.password);
        obj.put("sums",this.sums);

        return obj;
    }

    public static User JSONtoSimpleUser(JSONObject jsonObject){
        User user=new User();
        user.setFullname((String) jsonObject.get("fullname"));
        user.setID((String) jsonObject.get("ID"));
        user.setPhonenumber((String) jsonObject.get("phonenumber"));
        user.setAdress((String) jsonObject.get("adress"));
        return user;
    }

    public int usernameExists(String username) throws IOException, ParseException {
        FileHandler fileHandler=new FileHandler("Users.json");
        JSONArray jsonArray=fileHandler.read();
        for(int i=0;i<jsonArray.size();i++) {
            JSONObject object = (JSONObject) jsonArray.get(i);
            if(username.equals(object.get("username"))) return i;
        }
        return -1;
    }

    public boolean isUnderage(String ID){

        int year=((ID.charAt(1)-'0')*10) + (ID.charAt(2)-'0');
        int month=((ID.charAt(3)-'0')*10) + (ID.charAt(4)-'0');
        int day=((ID.charAt(5)-'0')*10) + (ID.charAt(6)-'0');
        if(year<22) year=year+2000;
        else year=year+1900;
        LocalDate birthdate= LocalDate.of(year,month,day);
        int age=Period.between(birthdate,LocalDate.now()).getYears();

        if(age<18) return true;
        return false;
    }

    public void setFullname(String fullname) {this.fullname = fullname;}
    public String getFullname() {return this.fullname;}
    public void setID(String ID) {this.ID = ID;}
    public String getID() {return this.ID;}
    public void setPhonenumber(String phonenumber) {this.phonenumber = phonenumber;}
    public String getPhonenumber() {return this.phonenumber;}
    public void setAdress(String adress) {this.adress = adress;}
    public String getAdress() {return this.adress;}
    public void setCardnumber(String cardnumber) {this.cardnumber = cardnumber;}
    public String getCardnumber() {return this.cardnumber;}
    public void setPIN(String PIN) {this.PIN = Encryptor.encrypt(PIN,cardnumber);}
    public String getPIN() {return this.PIN;}
    public void setUsername(String username) {this.username = username;}
    public String getUsername() {return this.username;}
    public void setPassword(String password) {this.password = Encryptor.encrypt(password,username);}
    public String getPassword() {return password;}
    public void setSums(ArrayList<Double> sums) {this.sums = sums;}
    public ArrayList<Double> getSums() {return sums;}
}
