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
import java.util.ArrayList;
import java.util.List;
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
    private Label firstDayLabel;
    @FXML
    private Label secondDayLabel;
    @FXML
    private Label thirdDayLabel;
    @FXML
    private Label fourthDayLabel;
    @FXML
    private Label fifthDayLabel;
    @FXML
    private Label sixthDayLabel;
    @FXML
    private Label seventhDayLabel;

    @FXML
    private ImageView firstDayWeather;
    @FXML
    private ImageView secondDayWeather;
    @FXML
    private ImageView thirdDayWeather;
    @FXML
    private ImageView fourthDayWeather;
    @FXML
    private ImageView fifthDayWeather;
    @FXML
    private ImageView sixthDayWeather;
    @FXML
    private ImageView seventhDayWeather;

    @FXML
    private Label firstDayMinTemp;
    @FXML
    private Label secondDayMinTemp;
    @FXML
    private Label thirdDayMinTemp;
    @FXML
    private Label fourthDayMinTemp;
    @FXML
    private Label fifthDayMinTemp;
    @FXML
    private Label sixthDayMinTemp;
    @FXML
    private Label seventhDayMinTemp;

    @FXML
    private Label firstDayMaxTemp;
    @FXML
    private Label secondDayMaxTemp;
    @FXML
    private Label thirdDayMaxTemp;
    @FXML
    private Label fourthDayMaxTemp;
    @FXML
    private Label fifthDayMaxTemp;
    @FXML
    private Label sixthDayMaxTemp;
    @FXML
    private Label seventhDayMaxTemp;

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
            setWeatherInformation();
            setFirstDayWeather();
            setSecondDayWeather();
            setThirdDayWeather();
            setFourthDayWeather();
            setFifthDayWeather();
            setSixthDayWeather();
            setSeventhDayWeather();
        } else {
            System.out.println("Error: No city name in the text field");
        }
    }

    protected void setTemperature() {
        temperatureField.setText(Math.round(currWeather.getCurrentInfo().getCurrTemperature()) + "°");
    }

    protected void setCity() {
        cityName.setText(currCity.getName());
    }

    protected void setLowTemp() {
        lowTemp.setText("L:" + currWeather.getInformationHourly().getMinTemp(1) + "°");
    }

    protected void setHighTemp() {
        highTemp.setText("H:" + currWeather.getInformationHourly().getMaxTemp(1) + "°");
    }

    protected void setWeatherInformation() {
        weatherInformation.setText(currWeather.getCurrentInfo().getWeatherInterpretationScript());
    }

    protected void setFirstDayWeather() {
        firstDayLabel.setText("Today");
        firstDayMaxTemp.setText(currWeather.getInformationHourly().getMaxTemp(1).toString()+ "°");
        firstDayMinTemp.setText(currWeather.getInformationHourly().getMinTemp(1).toString()+ "°");
        firstDayWeather.setImage(new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource(currWeather.getInformationHourly().getWeatherIcon(1))).toExternalForm())));
    }

    protected void setSecondDayWeather() {
        String secondDay = currWeather.getInformationHourly().getDaysAfter(1).toString();
        secondDayLabel.setText(currWeather.getInformationHourly().formatLabel(secondDay));
        secondDayMaxTemp.setText(currWeather.getInformationHourly().getMaxTemp(2).toString() + "°");
        secondDayMinTemp.setText(currWeather.getInformationHourly().getMinTemp(2).toString() + "°");
        secondDayWeather.setImage(new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource(currWeather.getInformationHourly().getWeatherIcon(2))).toExternalForm())));
    }

    protected void setThirdDayWeather() {
        String thirdDay = currWeather.getInformationHourly().getDaysAfter(2).toString();
        thirdDayLabel.setText(currWeather.getInformationHourly().formatLabel(thirdDay));
        thirdDayMaxTemp.setText(currWeather.getInformationHourly().getMaxTemp(3).toString() + "°");
        thirdDayMinTemp.setText(currWeather.getInformationHourly().getMinTemp(3).toString() + "°");
        thirdDayWeather.setImage(new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource(currWeather.getInformationHourly().getWeatherIcon(3))).toExternalForm())));
    }

    protected void setFourthDayWeather() {
        String fourthDay = currWeather.getInformationHourly().getDaysAfter(3).toString();
        fourthDayLabel.setText(currWeather.getInformationHourly().formatLabel(fourthDay));
        fourthDayMaxTemp.setText(currWeather.getInformationHourly().getMaxTemp(4).toString() + "°");
        fourthDayMinTemp.setText(currWeather.getInformationHourly().getMinTemp(4).toString() + "°");
        fourthDayWeather.setImage(new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource(currWeather.getInformationHourly().getWeatherIcon(4))).toExternalForm())));
    }

    protected void setFifthDayWeather() {
        String fifthDay = currWeather.getInformationHourly().getDaysAfter(4).toString();
        fifthDayLabel.setText(currWeather.getInformationHourly().formatLabel(fifthDay));
        fifthDayMaxTemp.setText(currWeather.getInformationHourly().getMaxTemp(5).toString() + "°");
        fifthDayMinTemp.setText(currWeather.getInformationHourly().getMinTemp(5).toString() + "°");
        fifthDayWeather.setImage(new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource(currWeather.getInformationHourly().getWeatherIcon(5))).toExternalForm())));
    }

    protected void setSixthDayWeather() {
        String sixthDay = currWeather.getInformationHourly().getDaysAfter(5).toString();
        sixthDayLabel.setText(currWeather.getInformationHourly().formatLabel(sixthDay));
        sixthDayMaxTemp.setText(currWeather.getInformationHourly().getMaxTemp(6).toString() + "°");
        sixthDayMinTemp.setText(currWeather.getInformationHourly().getMinTemp(6).toString() + "°");
        sixthDayWeather.setImage(new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource(currWeather.getInformationHourly().getWeatherIcon(6))).toExternalForm())));
    }

    protected void setSeventhDayWeather() {
        String seventhDay = currWeather.getInformationHourly().getDaysAfter(6).toString();
        seventhDayLabel.setText(currWeather.getInformationHourly().formatLabel(seventhDay));
        seventhDayMaxTemp.setText(currWeather.getInformationHourly().getMaxTemp(7).toString() + "°");
        seventhDayMinTemp.setText(currWeather.getInformationHourly().getMinTemp(7).toString() + "°");
        seventhDayWeather.setImage(new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource(currWeather.getInformationHourly().getWeatherIcon(7))).toExternalForm())));
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