package com.gtassotti.weatherapp.model;

import com.google.gson.annotations.SerializedName;

public class WeatherCurrent {
    @SerializedName("temperature_2m")
    private Double currTemperature;
    @SerializedName("weather_code")
    private Integer weatherCode;

    public WeatherCurrent(Double currTemperature, Integer weatherCode) {
        this.currTemperature = currTemperature;
        this.weatherCode = weatherCode;
    }

    public Double getCurrTemperature() {
        return currTemperature;
    }

    public void setCurrTemperature(Double currTemperature) {
        this.currTemperature = currTemperature;
    }

    public Integer getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(Integer weatherCode) {
        this.weatherCode = weatherCode;
    }

    @Override
    public String toString() {
        return "WeatherCurrent{" +
                "currTemperature=" + currTemperature +
                ", weatherCode=" + weatherCode +
                '}';
    }
}
