package com.example.virtualcurrencywallet_sef.Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {
    private String path;

    public FileHandler(String path)
    {
        this.path=path;
    }

    public JSONArray read() throws IOException, ParseException {
        JSONParser jsonParser=new JSONParser();
        FileReader fileReader=new FileReader(this.path);
        Scanner scanner = new Scanner(fileReader);
        Object object = jsonParser.parse(fileReader);
        return (JSONArray) object;

    }

    public void write(JSONArray jsonArray) throws IOException {
        FileWriter fw=new FileWriter(this.path);
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