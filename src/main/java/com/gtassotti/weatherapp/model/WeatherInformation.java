package com.gtassotti.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class WeatherInformation {
    @SerializedName("time")
    private List<String> timeList;
    @SerializedName("temperature_2m")
    private List<Double> temperatureList;
    @SerializedName("relative_humidity_2m")
    private List<Double> relativeHumidityList;
    @SerializedName("precipitation_probability")
    private List<Integer> probabilityOfPrecipitation;
    @SerializedName("precipitation")
    private List<Double> precipitationSum;
    @SerializedName("weather_code")
    private List<Integer> weatherCode;

    private static final int DAYS_OF_WEEK = 7;

    public WeatherInformation() {
        this.timeList = new ArrayList<>();
        this.temperatureList = new ArrayList<>();
        this.relativeHumidityList = new ArrayList<>();
        this.probabilityOfPrecipitation = new ArrayList<>();
        this.precipitationSum = new ArrayList<>();
        this.weatherCode = new ArrayList<>();
    }

    public List<String> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<String> timeList) {
        this.timeList = timeList;
    }

    public List<Double> getTemperatureList() {
        return temperatureList;
    }

    public void setTemperatureList(List<Double> temperatureList) {
        this.temperatureList = temperatureList;
    }

    public List<Double> getRelativeHumidityList() {
        return relativeHumidityList;
    }

    public void setRelativeHumidityList(List<Double> relativeHumidityList) {
        this.relativeHumidityList = relativeHumidityList;
    }

    public List<Integer> getProbabilityOfPrecipitation() {
        return probabilityOfPrecipitation;
    }

    public void setProbabilityOfPrecipitation(List<Integer> probabilityOfPrecipitation) {
        this.probabilityOfPrecipitation = probabilityOfPrecipitation;
    }

    public Integer avgProbabilityOfPrecipitation(Integer indexOfTheDay) {
        List<Integer> probOfPrecipitationOfDay = getDataOfDay(indexOfTheDay, probabilityOfPrecipitation);
        return (int) probOfPrecipitationOfDay.stream().mapToInt(p -> p).average().orElse(0);
    }

    public List<Double> getPrecipitationSum() {
        return precipitationSum;
    }

    public void setPrecipitationSum(List<Double> precipitationSum) {
        this.precipitationSum = precipitationSum;
    }

    public List<Integer> getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(List<Integer> weatherCode) {
        this.weatherCode = weatherCode;
    }

    public <T> T getEquivalentValue(String dateTime, List<T> dst) {
        return dst.get(timeList.indexOf(dateTime));
    }

    public static String convertDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'");
        return dateTime.format(formatter);
    }

    public Integer getMinTemp(Integer day) {
        List<Double> tempListOfDay = getDataOfDay(day, temperatureList);
        return Math.round(tempListOfDay.stream().min(Double::compareTo).orElseThrow(NoSuchElementException::new).floatValue());
    }

    public Integer getMaxTemp(Integer day) {
        List<Double> tempListOfDay = getDataOfDay(day, temperatureList);
        return Math.round(tempListOfDay.stream().max(Double::compareTo).orElseThrow(NoSuchElementException::new).floatValue());
    }

    public DayOfWeek getCurrentDay() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.getDayOfWeek();
    }

    public static String getCurrentDate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return convertDateTime(currentDateTime).substring(0, 12);
    }

    public DayOfWeek getDaysAfter(Integer days) {
        DayOfWeek startDate = getCurrentDay();
        DayOfWeek endDate;
        if (startDate.getValue() + days > DAYS_OF_WEEK) {
            endDate = DayOfWeek.of(startDate.getValue() + days - DAYS_OF_WEEK);
        } else {
            endDate = DayOfWeek.of(startDate.getValue() + days);
        }
        return endDate;
    }

    public <T> List<T> getDataOfDay(Integer day, List<T> src) {
        return new ArrayList<>(src.subList((day - 1) * 24, 24 + ((day - 1) * 24)));
    }

    public String formatLabel(String day) {
        return day.charAt(0) + day.substring(1,3).toLowerCase();
    }

    public Integer getWeatherCodeOfTheDay (Integer indexOfTheDay) {
        List<Integer> weatherCodeList = getDataOfDay(indexOfTheDay, weatherCode);
        Map<Integer, Integer> weatherCodeAverage = new HashMap<>();
        for (Integer code : weatherCodeList) {
            if (weatherCodeAverage.containsKey(code)) {
                weatherCodeAverage.put(code, weatherCodeAverage.get(code) + 1);
            } else {
                weatherCodeAverage.put(code, 1);
            }
        }

        return Collections.max(weatherCodeAverage.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public String getWeatherIcon(Integer weatherCode) {
        HashMap<Integer, String> toWeatherIcon = new HashMap<>();

        toWeatherIcon.put(0, "/icons/ClearSky.png");
        toWeatherIcon.put(1, "/icons/ClearSky.png");
        toWeatherIcon.put(2, "/icons/PartlyCloudy.png");
        toWeatherIcon.put(3, "/icons/Cloudy.png");
        toWeatherIcon.put(45, "/icons/Fog.png");
        toWeatherIcon.put(48, "/icons/Fog.png");
        toWeatherIcon.put(51, "/icons/LightDrizzle.png");
        toWeatherIcon.put(53, "/icons/ModerateDenseDrizzle-Rain.png");
        toWeatherIcon.put(55, "/icons/ModerateDenseDrizzle-Rain.png");
        toWeatherIcon.put(61, "/icons/LightDrizzle.png");
        toWeatherIcon.put(63, "/icons/ModerateDenseDrizzle-Rain.png");
        toWeatherIcon.put(65, "/icons/HeavyRain.png");
        toWeatherIcon.put(66, "/icons/SnowShowers.png");
        toWeatherIcon.put(67, "/icons/SnowShowers.png");
        toWeatherIcon.put(71, "/icons/SlightSnowfall.png");
        toWeatherIcon.put(73, "/icons/SlightSnowfall.png");
        toWeatherIcon.put(75, "/icons/HeavySnowFall.png");
        toWeatherIcon.put(77, "/icons/HeavySnowFall.png");
        toWeatherIcon.put(80, "/icons/LightDrizzle.png");
        toWeatherIcon.put(81, "/icons/ModerateDenseDrizzle-Rain.png");
        toWeatherIcon.put(82, "/icons/HeavyRain.png");
        toWeatherIcon.put(85, "/icons/SnowShowers.png");
        toWeatherIcon.put(86, "/icons/SnowShowers.png");
        toWeatherIcon.put(95, "/icons/SlightThunderstorm.png");

        return toWeatherIcon.get(weatherCode);
    }

    public String getWeatherIconOfTheDay(Integer indexOfTheDay) {
        return getWeatherIcon(getWeatherCodeOfTheDay(indexOfTheDay));
    }

    @Override
    public String toString() {
        return "WeatherInformation{" +
                "timeList=" + timeList +
                ", temperatureList=" + temperatureList +
                ", relativeHumidityList=" + relativeHumidityList +
                ", probabilityOfPrecipitation=" + probabilityOfPrecipitation +
                ", precipitationSum=" + precipitationSum +
                ", weatherCode=" + weatherCode +
                '}';
    }
}
