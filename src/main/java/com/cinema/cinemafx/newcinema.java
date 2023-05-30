package com.cinema.cinemafx;

import  com.cinema.cinemafx.JDBC.service.CinemaService;
import com.cinema.cinemafx.JDBC.entities.Cinema;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class newcinema {

    @FXML
    private TextField nameField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField capacityField;
    @FXML
    private CheckBox openCheckBox;
    @FXML
    private TextField screensField;
    @FXML
    private TextField revenueField;
    @FXML
    private TextField averagePriceField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private Button addButton;

    private CinemaService cinemaService = new CinemaService();

    private void redirectToCinemaList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listcinema.fxml"));
            Parent root = loader.load();

            // Create a new stage and set the scene
            //getpremarystage
            Stage stage = new Stage();
            stage.setTitle("Cinema List");
            stage.setScene(new Scene(root));
            stage.show();

            // Close the current window
            closeWindow();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeWindow() {
    }

    @FXML
    private void onAddCinemaButtonClicked() {
        String name = nameField.getText();
        String location = locationField.getText();
        int capacity = Integer.parseInt(capacityField.getText());
        boolean isOpen = openCheckBox.isSelected();
        int numberOfScreens = Integer.parseInt(screensField.getText());
        double revenue = Double.parseDouble(revenueField.getText());
        double averagePrice = Double.parseDouble(averagePriceField.getText());
        String phoneNumber = phoneNumberField.getText();

        Cinema cinema = new Cinema(name, location, capacity, isOpen, numberOfScreens, revenue, averagePrice, phoneNumber);
        cinemaService.save(cinema);
        redirectToCinemaList();


        // Clear the fields after adding the cinema
        nameField.clear();
        locationField.clear();
        capacityField.clear();
        openCheckBox.setSelected(false);
        screensField.clear();
        revenueField.clear();
        averagePriceField.clear();
        phoneNumberField.clear();
    }

}
