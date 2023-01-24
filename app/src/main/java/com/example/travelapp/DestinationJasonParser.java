package com.example.travelapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DestinationJasonParser {
    public static List<Destination> getObjectFromJason(String jason) {
        List<Destination> destinations;
        try {
            JSONArray jsonArray = new JSONArray(jason);
            destinations = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject = (JSONObject) jsonArray.get(i);
                Destination destination = new Destination();
                destination.setCity(jsonObject.getString("city"));
                destination.setContinent(jsonObject.getString("continent"));
                destination.setCountry(jsonObject.getString("country"));
                destination.setCost(jsonObject.getDouble("cost"));
                destination.setImg(jsonObject.getString("img"));
                destination.setDescription(jsonObject.getString("description"));
                destination.setLatitude(jsonObject.getDouble("latitude"));
                destination.setLongitude(jsonObject.getDouble("longitude"));

                destinations.add(destination);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return destinations;
    }

}
