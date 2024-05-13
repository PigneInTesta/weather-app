package com.gtassotti.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public class Weather {
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;
    @SerializedName("current")
    private WeatherCurrent currentInfo;
    @SerializedName("hourly")
    private WeatherInformation informationHourly;

    public Weather(Double latitude, Double longitude, Double currTemperature, Integer weatherCodeCurrent) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.currentInfo = new WeatherCurrent(currTemperature, weatherCodeCurrent);
        this.informationHourly = new WeatherInformation();
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public WeatherCurrent getCurrentInfo() {
        return currentInfo;
    }

    public void setCurrentInfo(Double currTemperature, Integer weatherCodeCurrent) {
        this.currentInfo = new WeatherCurrent(currTemperature, weatherCodeCurrent);
    }

    public WeatherInformation getInformationHourly() {
        return informationHourly;
    }

    public void setInformationHourly() {
        this.informationHourly = new WeatherInformation();

    }

    @Override
    public String toString() {
        return "Weather{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", currTemperature=" + currentInfo +
                ", informationHourly=" + informationHourly +
                '}';
    }
}
