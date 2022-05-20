package com.example.virtualcurrencywallet_sef.Model;

import org.json.simple.JSONObject;

import java.util.Objects;

public class Admin {
    private String fullname;
    private String ID;
    private String phonenumber;
    private String username;
    private String password;
    private String administratorPIN;

    public Admin(String fullname, String ID, String phonenumber, String username, String password, String administratorPIN) {
        this.fullname = fullname;
        this.ID = ID;
        this.phonenumber = phonenumber;
        this.username = username;
        this.password = password;
        this.administratorPIN = administratorPIN;
    }



    @Override
    public String toString() {
        return "Admin{" +
                "fullname='" + fullname + '\'' +
                ", ID='" + ID + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", administratorPIN='" + administratorPIN + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return fullname.equals(admin.fullname) && ID.equals(admin.ID) && phonenumber.equals(admin.phonenumber) && username.equals(admin.username) && password.equals(admin.password) && administratorPIN.equals(admin.administratorPIN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullname, ID, phonenumber, username, password, administratorPIN);
    }

    public JSONObject adminJSON(){
        JSONObject obj=new JSONObject();
        obj.put("fullname",this.fullname);
        obj.put("ID",this.ID);
        obj.put("phonenumber",this.phonenumber);
        obj.put("username",this.username);
        obj.put("password",this.password);
        obj.put("administratorPIN",this.administratorPIN);
        return obj;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdministratorPIN() {
        return administratorPIN;
    }

    public void setAdministratorPIN(String administratorPIN) {
        this.administratorPIN = administratorPIN;
    }
}
