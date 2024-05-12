module com.gtassotti.weatherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;
    requires org.controlsfx.controls;


    opens com.gtassotti.weatherapp to javafx.fxml;
    exports com.gtassotti.weatherapp;
    exports com.gtassotti.weatherapp.controller;
    opens com.gtassotti.weatherapp.controller to javafx.fxml;
    opens com.gtassotti.weatherapp.model to com.google.gson;
}