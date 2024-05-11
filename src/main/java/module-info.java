module com.gtassotti.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gtassotti.weatherapp to javafx.fxml;
    exports com.gtassotti.weatherapp;
    exports com.gtassotti.weatherapp.controller;
    opens com.gtassotti.weatherapp.controller to javafx.fxml;
}