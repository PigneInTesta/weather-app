package com.gtassotti.weatherapp.model;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class API {
    private final String urlString;

    public API(String urlString) {
        this.urlString = urlString;
    }

    public static void getCityData(String cityName) {
        //replace all the space in the city name to + to adhere to API's request format
        cityName = cityName.replace(" ", "+");

        String urlString =  "https://geocoding-api.open-meteo.com/v1/search?name=" +
                cityName + "&count=10&language=en&format=json";

        try {
            HttpURLConnection connection = fetchApiResponse(urlString);
            //check response
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

                System.out.println(cities);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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
