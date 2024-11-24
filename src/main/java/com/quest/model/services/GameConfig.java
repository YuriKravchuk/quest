package com.quest.model.services;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GameConfig {
    private int numberOfTrees;
    private int numberOfCaves;
    private int numberOfPeople;
    private static int width;
    private static int height;


    public GameConfig() {
        try (InputStream inputStream = getClass().getResourceAsStream("/config.json")) {
            if (inputStream == null) {
                throw new IOException("Resource not found: config.json");
            }
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(inputStream));
            numberOfTrees = Math.toIntExact((Long) jsonObject.get("numberOfTrees"));
            numberOfCaves = Math.toIntExact((Long) jsonObject.get("numberOfCaves"));
            numberOfPeople = Math.toIntExact((Long) jsonObject.get("numberOfPeople"));
            width = Math.toIntExact((Long) jsonObject.get("width"));
            height = Math.toIntExact((Long) jsonObject.get("height"));
            numberOfTrees = width*height*numberOfTrees / 100;
            numberOfCaves = width*height*numberOfCaves / 100;
            numberOfPeople = width*height*numberOfPeople / 100;

            if (width <= 0 || height <= 0) {
                throw new IllegalArgumentException("Width and height must be greater than zero.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Можна також встановити значення за замовчуванням
        }
    }

    // Геттери і сеттери...

    public static int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public static int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getNumberOfTrees() {
        return numberOfTrees;
    }

    public void setNumberOfTrees(int numberOfTrees) {
        this.numberOfTrees = numberOfTrees;
    }

    public int getNumberOfCaves() {
        return numberOfCaves;
    }

    public void setNumberOfCaves(int numberOfCaves) {
        this.numberOfCaves = numberOfCaves;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
}
