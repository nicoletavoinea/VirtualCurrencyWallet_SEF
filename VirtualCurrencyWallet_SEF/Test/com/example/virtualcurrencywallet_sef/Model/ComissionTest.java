package com.example.virtualcurrencywallet_sef.Model;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComissionTest {
    @Test
    public void commissionJSONTest(){
        Comission cms=new Comission(0.05);
        JSONObject object=new JSONObject();
        object.put("commission", 0.05);
        assertEquals(object,cms.commissionJSON());
    }
}