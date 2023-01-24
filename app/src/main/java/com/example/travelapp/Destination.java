package com.example.travelapp;

import android.widget.ImageView;

public class Destination {
    private String city;
    private String country;
    private String continent;
    private double longitude;
    private double latitude;
    private double cost;
    private String imgLink;
    private String description;

    public Destination() {
    }

    public Destination(String city, String country, String continent, double longitude, double latitude, double cost, String imgLink, String description) {
        this.city = city;
        this.country = country;
        this.continent = continent;
        this.longitude = longitude;
        this.latitude = latitude;
        this.cost = cost;
        this.imgLink = imgLink;
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Destination{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", continent='" + continent + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", cost=" + cost +
                ", imgLink='" + imgLink + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
