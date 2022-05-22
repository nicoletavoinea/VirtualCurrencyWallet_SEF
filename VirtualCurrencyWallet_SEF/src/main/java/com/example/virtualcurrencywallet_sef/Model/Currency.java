package com.example.virtualcurrencywallet_sef.Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

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

    public int alreadyExists(String name) throws IOException, ParseException {
        FileHandler fileHandler=new FileHandler("src/main/java/com/example/virtualcurrencywallet_sef/Database/Currencies.json");
        JSONArray jsonArray=fileHandler.read();
        for(int i=0;i<jsonArray.size();i++){
            JSONObject object=(JSONObject) jsonArray.get(i);
            if((name.equals(object.get("name"))))
                return i;
        }
        return -1;
    }

    public static int getPosition(String name) throws IOException, ParseException {
        FileHandler fileHandler=new FileHandler("src/main/java/com/example/virtualcurrencywallet_sef/Database/Currencies.json");
        JSONArray jsonArray=fileHandler.read();
        JSONObject current;
        for(int i=0;i<jsonArray.size();i++) {
            current= (JSONObject) jsonArray.get(i);
            if(name.equals(current.get("name"))) return i;
        }
        return -1;
    }

}
