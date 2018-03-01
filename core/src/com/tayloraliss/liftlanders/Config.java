package com.tayloraliss.liftlanders;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Entity {
    public Integer width;
    public Integer height;
    public String name;
}

public abstract class Config {



    public static void write(){

        Entity lift = new Entity();
        lift.width = 184;
        lift.height = 20;
        lift.name = "lifter";

        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(new File("../../core/../core/src/com/tayloraliss/liftlanders/config.json"), lift );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void parse() {

        JSONParser parser = new JSONParser();
        try {
            FileReader fileReader = new FileReader("../../core/../core/src/com/tayloraliss/liftlanders/config.json");
            JSONObject json = (JSONObject) parser.parse(fileReader);
            System.out.println(json);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
