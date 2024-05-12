package com.gtassotti.weatherapp.controller;

import com.gtassotti.weatherapp.model.API;
import com.gtassotti.weatherapp.model.City;
import com.gtassotti.weatherapp.model.Weather;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;


public class WeatherController {

    @FXML
    private Label temperatureField;
    @FXML
    private TextField cityToSearch;

    private City currCity;
    private Weather currWeather;

    @FXML
    protected void initialize()  {
        //setTemperature();
    }

    @FXML
    protected void onButtonClickSearch() {
        if (!cityToSearch.getText().isEmpty()){
            List<City> citiesFounded = new ArrayList<>(API.getCityData(cityToSearch.getText()));
            currWeather = API.getWeatherData(citiesFounded.getFirst());
            /*currCity = new City(citiesFounded.getFirst().getId(),
                                citiesFounded.getFirst().getName(),
                                citiesFounded.getFirst().getLatitude(),
                                citiesFounded.getFirst().getLongitude(),
                                citiesFounded.getFirst().getCountry(),
                                citiesFounded.getFirst().getCountry_code());*/
            setTemperature();
        } else {
            System.out.println("No city name in the text field");
        }
    }

    protected void setTemperature() {
        temperatureField.setText("Temperature: " + currWeather.getInformationHourly().getCurrentTemp() + " °C");
    }

}