package com.gtassotti.weatherapp.controller;

import com.gtassotti.weatherapp.model.API;
import com.gtassotti.weatherapp.model.City;
import com.gtassotti.weatherapp.model.Weather;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

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
    @FXML
    private ComboBox<City> results;
    @FXML
    private Label weatherInformation;
    @FXML
    private ImageView mondayWeather;
    @FXML
    private ImageView thursdayWeather;
    @FXML
    private ImageView wednesdayWeather;
    @FXML
    private ImageView tuesdayWeather;
    @FXML
    private ImageView fridayWeather;
    @FXML
    private ImageView saturdayWeather;
    @FXML
    private ImageView sundayWeather;
    @FXML
    private Label minTempMonday;
    @FXML
    private Label minTempThursday;
    @FXML
    private Label minTempWednesday;
    @FXML
    private Label minTempTuesday;
    @FXML
    private Label minTempFriday;
    @FXML
    private Label minTempSaturday;
    @FXML
    private Label minTempSunday;

    @FXML
    private Label maxTempMonday;
    @FXML
    private Label maxTempThursday;
    @FXML
    private Label maxTempWednesday;
    @FXML
    private Label maxTempTuesday;
    @FXML
    private Label maxTempFriday;
    @FXML
    private Label maxTempSaturday;
    @FXML
    private Label maxTempSunday;

    private City currCity;
    private Weather currWeather;
    private List<City> citiesFounded;

    @FXML
    protected void initialize()  {
        //setTemperature();
        results.setEditable(true);
        results.setVisibleRowCount(10);
        search();
    }

    @FXML
    protected void onButtonClickSearch() throws IOException {
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
            setWeatherInformation();
            setMondayWeather();
        } else {
            System.out.println("Error: No city name in the text field");
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

    protected void setWeatherInformation() {
        weatherInformation.setText(currWeather.getCurrentInfo().getWeatherInterpretationScript());
    }

    protected void setMondayWeather() throws IOException {
        List<Double> tempListOfDay = currWeather.getInformationHourly().getDataOfDay(DayOfWeek.MONDAY, currWeather.getInformationHourly().getTemperatureList());
        maxTempMonday.setText(tempListOfDay.stream().max(Double::compareTo).orElseThrow(NoSuchElementException::new).toString());
        minTempMonday.setText(tempListOfDay.stream().min(Double::compareTo).orElseThrow(NoSuchElementException::new).toString());
        mondayWeather.setImage(new Image(Objects.requireNonNull(getClass().getResource(currWeather.getInformationHourly().getWeatherIcon(DayOfWeek.MONDAY))).toExternalForm()));
    }

    protected void search() {
        results.getEditor().textProperty().addListener(((obs, oldValue, newValue) -> {
            if (!results.getItems().isEmpty()) {
                results.getItems().clear();
            } else {
                results.getItems().setAll(FXCollections.observableArrayList(API.getCityData(obs.getValue())));
                results.show();
            }
        }));
    }

}