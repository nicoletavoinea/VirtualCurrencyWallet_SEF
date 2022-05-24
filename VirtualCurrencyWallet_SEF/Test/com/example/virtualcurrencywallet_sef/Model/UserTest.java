package com.example.virtualcurrencywallet_sef.Model;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void userIsUnderage() {
        User user=new User();
        assertTrue(user.isUnderage("6051217115678"));
    }

    @Test
    void userIsNotUnderage() {
        User user=new User();
        assertFalse(user.isUnderage("6011217115678"));
    }

    @Test
    void userJSONTest() throws IOException, ParseException {
        User user=new User("a","1","2","b","3","4","c","d");
        JSONObject obj=new JSONObject();
        ArrayList <Double> arrayList=new ArrayList<>();
        arrayList.add(0.0);
        obj.put("fullname","a");
        obj.put("ID","1");
        obj.put("phonenumber","2");
        obj.put("adress","b");
        obj.put("cardnumber","3");
        obj.put("PIN",Encryptor.encrypt("4","3"));
        obj.put("username","c");
        obj.put("password",Encryptor.encrypt("d","c"));
        obj.put("sums",arrayList);
        assertEquals(obj,user.userJSON());
    }
}