package com.example.virtualcurrencywallet_sef.Model;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HolderTest {

    @Test
    void getHeldObject() {
        Holder holder=Holder.getInstance();
        JSONObject object1=new JSONObject();
        JSONObject object2=new JSONObject();
        holder.set(object1);
        object1.put("key",0);
        object2.put("key",0);
        
        assertEquals(object2,holder.get());


    }
}