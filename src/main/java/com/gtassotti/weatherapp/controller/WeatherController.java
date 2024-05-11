package com.gtassotti.weatherapp.controller;

import com.gtassotti.weatherapp.model.API;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WeatherController {

    @FXML
    private Label temperatureField;
    @FXML
    private TextField cityToSearch;

    private final String geoAPI = "https://geocoding-api.open-meteo.com/v1/search";
    private final String weatherAPI = "https://api.open-meteo.com/v1/forecast?";

    @FXML
    protected void initialize()  {
        temperatureField.setText("Temperature: ");
    }

    @FXML
    protected void onButtonClickSearch() {
        if (!cityToSearch.getText().isEmpty()){
            API.getCityData(cityToSearch.getText());
        } else {
            System.out.println("No city name in the text field");
        }
    }

}