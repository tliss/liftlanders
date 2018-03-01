package com.tayloraliss.liftlanders;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;

class Entity {
    public Integer width;
    public Integer height;
    public String name;
}

public abstract class Config {

    private static int height;
    private static int width;


    public static void write(){

        getHeightWidth();


        Entity lift = new Entity();
        lift.width = width;
        lift.height = height;
        lift.name = "lift";

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

    private static void getHeightWidth(){
        try {
            File f = new File("lift.png");
            BufferedImage image = ImageIO.read(f);
            height = image.getHeight();
            width = image.getWidth();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
