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
    private Label cityName;
    @FXML
    private Label lowTemp;
    @FXML
    private Label highTemp;
    @FXML
    private TextField cityToSearch;


    private City currCity;
    private Weather currWeather;
    private List<City> citiesFounded;

    @FXML
    protected void initialize()  {
        //setTemperature();
        //search();
    }

    @FXML
    protected void onButtonClickSearch() {
        if (!cityToSearch.getText().isEmpty()){
            citiesFounded = new ArrayList<>(API.getCityData(cityToSearch.getText()));
            currWeather = API.getWeatherData(citiesFounded.getFirst());
            currCity = new City(citiesFounded.getFirst().getId(),
                                citiesFounded.getFirst().getName(),
                                citiesFounded.getFirst().getLatitude(),
                                citiesFounded.getFirst().getLongitude(),
                                citiesFounded.getFirst().getCountry(),
                                citiesFounded.getFirst().getCountry_code());
            setCity();
            setTemperature();
            setLowTemp();
            setHighTemp();
        } else {
            System.out.println("No city name in the text field");
        }
    }


    protected void setTemperature() {
        temperatureField.setText(currWeather.getCurrentInfo().getCurrTemperature() + "°");
    }

    protected void setCity() {
        cityName.setText(currCity.getName());
    }

    protected void setLowTemp() {
        lowTemp.setText("L:" + currWeather.getInformationHourly().getMinTemp(currWeather.getInformationHourly().getCurrentDateTime()).toString() + "°");
    }

    protected void setHighTemp() {
        highTemp.setText("H:" + currWeather.getInformationHourly().getHighTemp(currWeather.getInformationHourly().getCurrentDateTime()).toString() + "°");
    }
    protected void search() {
        //cityToSearch.textProperty().addListener(((observableValue, oldText, newText) -> {API.getCityData(observableValue.getValue());}));
    }


}