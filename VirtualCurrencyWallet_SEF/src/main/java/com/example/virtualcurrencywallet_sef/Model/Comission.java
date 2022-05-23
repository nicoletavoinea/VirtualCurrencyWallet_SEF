package com.example.virtualcurrencywallet_sef.Model;

import org.json.simple.JSONObject;

public class Comission {
    private double commission;

    public Comission(double commission){this.commission=commission;}

    public JSONObject commissionJSON(){
        JSONObject object=new JSONObject();
        object.put("commission",commission);
        return object;
    }

}
