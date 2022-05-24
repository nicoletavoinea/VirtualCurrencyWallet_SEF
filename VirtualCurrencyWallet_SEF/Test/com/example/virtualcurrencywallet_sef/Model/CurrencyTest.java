package com.example.virtualcurrencywallet_sef.Model;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {
    @Test
    public void JSONtoCurrencyTest(){
        JSONObject jsonCurrency=new JSONObject();
        jsonCurrency.put("name","doge");
        jsonCurrency.put("rate",420.0);
        Currency expected=new Currency("doge",420.0);
        assertEquals(Currency.JSONtoCurrency(jsonCurrency),expected);
    }

    @Test public void currencyJSONTest(){
        Currency currency=new Currency("doge",420.0);
        JSONObject expected= new JSONObject();
        expected.put("name","doge");
        expected.put("rate",420.0);
        assertEquals(currency.currencyJSON(),expected);
    }
}
