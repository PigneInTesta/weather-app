package com.gtassotti.weatherapp.model;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class API {
    private final String urlString;

    public API(String urlString) {
        this.urlString = urlString;
    }

    public static List<City> getCityData(String cityName) {
        //replace all the space in the city name to + to adhere to API's request format
        cityName = cityName.replace(" ", "+");

        String urlString =  "https://geocoding-api.open-meteo.com/v1/search?name=" +
                cityName + "&count=10&language=en&format=json";

        List<City> cityList = new ArrayList<>();
        try {
            HttpURLConnection connection = fetchApiResponse(urlString);
            if (connection.getResponseCode() != 200){
                System.out.println("Error: Could not connect to API");
            } else {
                StringBuilder result = new StringBuilder();
                Scanner scanner = new Scanner(connection.getInputStream());

                while(scanner.hasNext()){
                    result.append(scanner.nextLine());
                }
                scanner.close();
                connection.disconnect();
                CityWrapper cities = new Gson().fromJson(result.toString(), CityWrapper.class);
                for (int i = 0; i < cities.size(); i++) {
                    cityList.add(cities.get(i));
                }
                //System.out.println(cities);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityList;
    }

    public static Weather getWeatherData(City city) {

        String urlString = "https://api.open-meteo.com/v1/forecast?" +
                "latitude=" + city.getLatitude() +
                "&longitude=" + city.getLongitude() +
                "&current=temperature_2m,weather_code&hourly=temperature_2m,relative_humidity_2m,precipitation_probability,precipitation,weather_code";

        Weather weather = new Weather(0.0, 0.0, 0.0, null);
        try {
            HttpURLConnection connection = fetchApiResponse(urlString);
            if (connection.getResponseCode() != 200){
                System.out.println("Error: Could not connect to API");
            } else {
                StringBuilder result = new StringBuilder();
                Scanner scanner = new Scanner(connection.getInputStream());

                while(scanner.hasNext()){
                    result.append(scanner.nextLine());
                }
                scanner.close();
                connection.disconnect();
                weather = new Gson().fromJson(result.toString(), Weather.class);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return weather;
    }

    private static HttpURLConnection fetchApiResponse(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            return connection;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
