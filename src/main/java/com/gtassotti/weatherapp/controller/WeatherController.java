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
import java.util.ArrayList;
import java.util.Arrays;
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

    @FXML
    private Label firstDayProbPrec;
    @FXML
    private Label secondDayProbPrec;
    @FXML
    private Label thirdDayProbPrec;
    @FXML
    private Label fourthDayProbPrec;
    @FXML
    private Label fifthDayProbPrec;
    @FXML
    private Label sixthDayProbPrec;
    @FXML
    private Label seventhDayProbPrec;

    @FXML
    private Label firstTimeSlotTemp;
    @FXML
    private Label secondTimeSlotTemp;
    @FXML
    private Label thirdTimeSlotTemp;
    @FXML
    private Label fourthTimeSlotTemp;
    @FXML
    private Label fifthTimeSlotTemp;
    @FXML
    private Label sixthTimeSlotTemp;
    @FXML
    private Label seventhTimeSlotTemp;

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
            setWeekForecast();

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

    protected void setWeekForecast() {
        List<Label> daysList = Arrays.asList(firstDayLabel, secondDayLabel, thirdDayLabel, fourthDayLabel, fifthDayLabel, sixthDayLabel, seventhDayLabel);
        List<Label> maxTempList = Arrays.asList(firstDayMaxTemp, secondDayMaxTemp, thirdDayMaxTemp, fourthDayMaxTemp, fifthDayMaxTemp, sixthDayMaxTemp, seventhDayMaxTemp);
        List<Label> minTempList = Arrays.asList(firstDayMinTemp, secondDayMinTemp, thirdDayMinTemp, fourthDayMinTemp, fifthDayMinTemp, sixthDayMinTemp, seventhDayMinTemp);
        List<ImageView> iconForecastList = Arrays.asList(firstDayWeather, secondDayWeather, thirdDayWeather, fourthDayWeather, fifthDayWeather, sixthDayWeather, seventhDayWeather);
        List<Label> probPrecList = Arrays.asList(firstDayProbPrec, secondDayProbPrec, thirdDayProbPrec, fourthDayProbPrec, fifthDayProbPrec, sixthDayProbPrec, seventhDayProbPrec);

        StringBuilder day = new StringBuilder();
        for (int i = 1; i <= daysList.size(); i++) {
            if (i == 1) {
                daysList.getFirst().setText("Today");
            } else {
                day.append(currWeather.getInformationHourly().getDaysAfter(i - 1).toString());
                daysList.get(i - 1).setText(currWeather.getInformationHourly().formatLabel(day.toString()));
            }
            maxTempList.get(i - 1).setText(currWeather.getInformationHourly().getMaxTemp(i).toString()+ "°");
            minTempList.get(i - 1).setText(currWeather.getInformationHourly().getMinTemp(i).toString()+ "°");
            iconForecastList.get(i - 1).setImage(new Image(Objects.requireNonNull(Objects.requireNonNull(getClass().getResource(currWeather.getInformationHourly().getWeatherIcon(i))).toExternalForm())));
            probPrecList.get(i - 1).setText("☂ " + currWeather.getInformationHourly().avgProbabilityOfPrecipitation(i).toString() + "%");
            day.delete(0, day.length());
        }
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