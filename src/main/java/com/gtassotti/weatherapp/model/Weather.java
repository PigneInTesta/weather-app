package com.gtassotti.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;
    @SerializedName("hourly")
    private WeatherInformation informationHourly;

    public Weather(Double latitude, Double longitude, List<String> timeList, List<Double> temperatureList, List<Double> relativeHumidityList, List<Integer> probabilityOfPrecipitation, List<Double> precipitationSum, List<Integer> weatherCode) {
        this.latitude = latitude;
        this.longitude = longitude;
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
                ", informationHourly=" + informationHourly +
                '}';
    }
}
