package com.example.virtualcurrencywallet_sef.Model;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {
    @Test
    void adminJSONTest(){
        Admin admin=new Admin("a","b","c","d","e","f");
        JSONObject o=new JSONObject();
        o.put("fullname","a");
        o.put("ID","b");
        o.put("phonenumber","c");
        o.put("username","d");
        o.put("password",Encryptor.encrypt("e","d"));
        o.put("administratorPIN","f");

        assertEquals(o,admin.adminJSON());

    }

}