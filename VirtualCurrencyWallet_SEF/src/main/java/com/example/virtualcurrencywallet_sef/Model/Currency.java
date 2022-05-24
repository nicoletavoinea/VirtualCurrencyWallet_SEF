package com.example.virtualcurrencywallet_sef.Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class Currency {
    private String name;
    private double rate;

    /*public  Currency(){
        this.name=null;
        this.rate=null;
    }*/
    public Currency(String name, double rate){
        this.name=name;
        this.rate=rate;
    }

    public  Currency(){
        this.name=null;
        this.rate=0.0;
    }
    public static Currency JSONtoCurrency(JSONObject jsonObject){
        Currency currency=new Currency();
        currency.name= (String) jsonObject.get("name");
        currency.rate= (double) jsonObject.get("rate");
        return currency;
    }

    public void setName(String name) {this.name = name;}
    public String getName(){return this.name;}
    public void setRate(double rate){this.rate=rate;}
    public double getRate(){return this.rate;}

    public JSONObject currencyJSON(){
        JSONObject object=new JSONObject();
        object.put("name",this.name);
        object.put("rate",this.rate);
        return object;
    }

   /* public static Currency JSONtoCurrency(JSONObject jsonObject){
        Currency currency=new Currency();
        currency.name=jsonObject.get("name");
        currency.rate=jsonObject.get("rate");
        return currency;
    }*/

    public int alreadyExists(String name) throws IOException, ParseException {
        FileHandler fileHandler=new FileHandler("Currencies.json");
        JSONArray jsonArray=fileHandler.read();
        for(int i=0;i<jsonArray.size();i++){
            JSONObject object=(JSONObject) jsonArray.get(i);
            if((name.equals(object.get("name"))))
                return i;
        }
        return -1;
    }

    public static int getPosition(String name) throws IOException, ParseException {
        FileHandler fileHandler=new FileHandler("Currencies.json");
        JSONArray jsonArray=fileHandler.read();
        JSONObject current;
        for(int i=0;i<jsonArray.size();i++) {
            current= (JSONObject) jsonArray.get(i);
            if(name.equals(current.get("name"))) return i;
        }
        return -1;
    }

}
