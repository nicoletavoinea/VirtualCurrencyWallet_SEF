package com.example.virtualcurrencywallet_sef.Model;

import java.util.Objects;

public class User {
    private String fullname;
    private String ID;
    private String phonenumber;
    private String adress;
    private String cardnumber;
    private int PIN;
    private String username;
    private String password;

    public User(String fullname, String ID, String phonenumber, String adress, String cardnumber, int PIN, String username, String password) {
        this.fullname = fullname;
        this.ID = ID;
        this.phonenumber = phonenumber;
        this.adress = adress;
        this.cardnumber = cardnumber;
        this.PIN = PIN;
        this.username = username;
        this.password = password;
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
        return PIN == user.PIN && fullname.equals(user.fullname) && ID.equals(user.ID) && phonenumber.equals(user.phonenumber) && adress.equals(user.adress) && cardnumber.equals(user.cardnumber) && username.equals(user.username) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullname, ID, phonenumber, adress, cardnumber, PIN, username, password);
    }

    public void setFullname(String fullname) {this.fullname = fullname;}
    public String getFullname() {return fullname;}
    public void setID(String ID) {this.ID = ID;}
    public String getID() {return ID;}
    public void setPhonenumber(String phonenumber) {this.phonenumber = phonenumber;}
    public String getPhonenumber() {return phonenumber;}
    public void setAdress(String adress) {this.adress = adress;}
    public String getAdress() {return adress;}
    public void setCardnumber(String cardnumber) {this.cardnumber = cardnumber;}
    public String getCardnumber() {return cardnumber;}
    public void setPIN(int PIN) {this.PIN = PIN;}
    public int getPIN() {return PIN;}
    public void setUsername(String username) {this.username = username;}
    public String getUsername() {return username;}
    public void setPassword(String password) {this.password = password;}
    public String getPassword() {return password;}
}