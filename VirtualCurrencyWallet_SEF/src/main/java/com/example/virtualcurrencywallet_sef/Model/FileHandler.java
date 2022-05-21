package com.example.virtualcurrencywallet_sef.Database;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {
    public static JSONArray readusers(String path) throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();
        FileReader fileReader=new FileReader(path);
        Scanner scanner = new Scanner(fileReader);
        Object object = jsonParser.parse(fileReader);
        JSONArray jsonArray = (JSONArray) object;
        return jsonArray;

    }

    public static void writeusers(JSONArray jsonArray,String path) throws IOException {
        FileWriter fw=new FileWriter(path);
        fw.write("[");
        for(int i=0;i<jsonArray.size();i++) {
            JSONObject object = (JSONObject) jsonArray.get(i);
            fw.write(object.toJSONString());
            //System.out.println(jsonArray.get(i));
        }
        fw.write("]");
        fw.flush();
        fw.close();
    }

}