package com.example.virtualcurrencywallet_sef.Model;

import org.json.simple.JSONObject;

public class Currency {
    private final String name;
    private double rate;

    public Currency(String name, double rate){
        this.name=name;
        this.rate=rate;
    }


    public String getName(){return this.name;}
    public void setRate(double rate){this.rate=rate;}
    public double getRate(){return this.rate;}

    public JSONObject currencyJSON(){
        JSONObject object=new JSONObject();
        object.put("name",this.name);
        object.put("rate",this.rate);
        return object;
    }
}
