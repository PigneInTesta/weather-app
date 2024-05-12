package com.gtassotti.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CityWrapper {
    @SerializedName("results")
    private List<City> results;

    public City get(int index) {
        return results.get(index);
    }

    public int size() {
        return results.size();
    }

    @Override
    public String toString() {
        return "CityWrapper{" +
                "results=" + results +
                '}';

    }
}