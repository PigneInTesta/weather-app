package com.gtassotti.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

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

    public String getWeatherInterpretation() {
        HashMap<Integer, String> toWeatherInterpretation = new HashMap<>();
        toWeatherInterpretation.put(0, "Clear Sky");
        toWeatherInterpretation.put(1, "Mainly Clear");
        toWeatherInterpretation.put(2, "Partly Cloudy");
        toWeatherInterpretation.put(3, "Cloudy");
        toWeatherInterpretation.put(45, "Fog");
        toWeatherInterpretation.put(48, "Fog");
        toWeatherInterpretation.put(51, "Light Drizzle");
        toWeatherInterpretation.put(53, "Moderate Drizzle");
        toWeatherInterpretation.put(55, "Dense Drizzle");
        toWeatherInterpretation.put(61, "Slight Rain");
        toWeatherInterpretation.put(63, "Moderate Rain");
        toWeatherInterpretation.put(65, "Heavy Rain");
        toWeatherInterpretation.put(66, "Light Freezing Rain");
        toWeatherInterpretation.put(67, "Heavy Freezing Rain");
        toWeatherInterpretation.put(71, "Slight Snow Fall");
        toWeatherInterpretation.put(73, "Moderate Snow Fall");
        toWeatherInterpretation.put(75, "Heavy Snow Fall");
        toWeatherInterpretation.put(77, "Snow Grains");
        toWeatherInterpretation.put(80, "Slight Rain");
        toWeatherInterpretation.put(81, "Moderate Rain");
        toWeatherInterpretation.put(82, "Heavy Rain");
        toWeatherInterpretation.put(85, "Slight Snow Showers");
        toWeatherInterpretation.put(86, "Slight Snow Showers");
        toWeatherInterpretation.put(95, "Slight Thunderstorm");

        return toWeatherInterpretation.get(weatherCode);
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
