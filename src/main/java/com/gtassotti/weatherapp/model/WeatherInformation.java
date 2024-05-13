package com.gtassotti.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    public <T> T returnEquivalentValue(String dateTime, List<T> dst) {
        return dst.get(timeList.indexOf(dateTime));
    }

    public LocalDateTime convertDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'");
        return LocalDateTime.parse(dateTime, formatter);
    }

    public String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':00'");
        return currentDateTime.format(formatter);
    }

    public Double getMinTemp(String dateTime) {
        List<Double> tempListOfDay = getDataOfDay(getDayOfWeek(dateTime), temperatureList);
        return tempListOfDay.stream().min((o1, o2) -> o1.compareTo(o2)).orElseThrow(NoSuchElementException::new);
    }

    public Double getHighTemp(String dateTime) {
        List<Double> tempListOfDay = getDataOfDay(getDayOfWeek(dateTime), temperatureList);
        return tempListOfDay.stream().max((o1, o2) -> o1.compareTo(o2)).orElseThrow(NoSuchElementException::new);
    }

    public DayOfWeek getDayOfWeek(String dateTime) {
        return convertDateTime(dateTime).getDayOfWeek();
    }

    public <T> List<T> getDataOfDay(DayOfWeek day, List<T> src) {
        return new ArrayList<>(src.subList((day.getValue() - 1) * 24, 23 + ((day.getValue() - 1) * 24)));
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
