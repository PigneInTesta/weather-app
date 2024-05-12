package com.gtassotti.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;
    @SerializedName("current")
    private WeatherCurrent currentInfo;
    @SerializedName("hourly")
    private WeatherInformation informationHourly;

    public Weather(Double latitude, Double longitude, Double currTemperature, Integer weatherCodeCurrent, List<String> timeList, List<Double> temperatureList, List<Double> relativeHumidityList, List<Integer> probabilityOfPrecipitation, List<Double> precipitationSum, List<Integer> weatherCode) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.currentInfo = new WeatherCurrent(currTemperature, weatherCodeCurrent);
        this.informationHourly = new WeatherInformation(timeList, temperatureList, relativeHumidityList, probabilityOfPrecipitation, precipitationSum, weatherCode);
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

    public void setInformationHourly(List<String> timeList, List<Double> temperatureList, List<Double> relativeHumidityList, List<Integer> probabilityOfPrecipitation, List<Double> precipitationSum, List<Integer> weatherCode) {
        this.informationHourly = new WeatherInformation(timeList, temperatureList, relativeHumidityList, probabilityOfPrecipitation, precipitationSum, weatherCode);

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
