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

    public String getWeatherInterpretationScript() {
        HashMap<Integer, String> toWeatherInterpretationScript = new HashMap<>();
        toWeatherInterpretationScript.put(0, "Clear Sky");
        toWeatherInterpretationScript.put(1, "Mainly Clear");
        toWeatherInterpretationScript.put(2, "Partly Cloudy");
        toWeatherInterpretationScript.put(3, "Cloudy");
        toWeatherInterpretationScript.put(45, "Fog");
        toWeatherInterpretationScript.put(48, "Fog");
        toWeatherInterpretationScript.put(51, "Light Drizzle");
        toWeatherInterpretationScript.put(53, "Moderate Drizzle");
        toWeatherInterpretationScript.put(55, "Dense Drizzle");
        toWeatherInterpretationScript.put(61, "Slight Rain");
        toWeatherInterpretationScript.put(63, "Moderate Rain");
        toWeatherInterpretationScript.put(65, "Heavy Rain");
        toWeatherInterpretationScript.put(66, "Light Freezing Rain");
        toWeatherInterpretationScript.put(67, "Heavy Freezing Rain");
        toWeatherInterpretationScript.put(71, "Slight Snow Fall");
        toWeatherInterpretationScript.put(73, "Moderate Snow Fall");
        toWeatherInterpretationScript.put(75, "Heavy Snow Fall");
        toWeatherInterpretationScript.put(77, "Snow Grains");
        toWeatherInterpretationScript.put(80, "Slight Rain");
        toWeatherInterpretationScript.put(81, "Moderate Rain");
        toWeatherInterpretationScript.put(82, "Heavy Rain");
        toWeatherInterpretationScript.put(85, "Slight Snow Showers");
        toWeatherInterpretationScript.put(86, "Slight Snow Showers");
        toWeatherInterpretationScript.put(95, "Slight Thunderstorm");

        return toWeatherInterpretationScript.get(weatherCode);
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
