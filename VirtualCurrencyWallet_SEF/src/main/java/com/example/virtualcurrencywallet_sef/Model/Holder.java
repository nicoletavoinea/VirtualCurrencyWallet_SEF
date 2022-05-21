package com.example.virtualcurrencywallet_sef.Model;

import org.json.simple.JSONObject;

public final class Holder {
    private JSONObject object;
    private static final Holder INSTANCE =new Holder();

    private Holder(){}

    public static Holder getInstance(){
        return  INSTANCE;
    }

    public void set(JSONObject object){
        this.object=object;
    }

    public JSONObject get(){
        return this.object;
    }
}
