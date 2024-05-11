package com.gtassotti.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityWrapper {
    @SerializedName("results")
    private List<City> results;

    @Override
    public String toString() {
        return "CityWrapper{" +
                "results=" + results +
                '}';
    }
}