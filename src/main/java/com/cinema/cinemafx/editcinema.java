package com.cinema.cinemafx;

import com.cinema.cinemafx.JDBC.entities.Cinema;
import com.cinema.cinemafx.JDBC.service.CinemaService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class editcinema {
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

    private Cinema cinema;
    private Stage stage;

    private CinemaService cinemaService;

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
        populateFields();
    }

    private void populateFields() {
        if (cinema != null) {
            nameField.setText(cinema.getNom());
            locationField.setText(cinema.getEmplacement());
            capacityField.setText(String.valueOf(cinema.getCapaciteMaximale()));
            openCheckBox.setSelected(cinema.isEstOuvert());
            screensField.setText(String.valueOf(cinema.getNombreDeSalles()));
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void redirectToCinemaList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("listcinema.fxml"));
            Parent root = loader.load();

            // Create a new stage and set the scene
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
    @FXML
    private void onSaveButtonClicked() {
        if (cinema != null) {
            cinema.setNom(nameField.getText());
            cinema.setEmplacement(locationField.getText());
            cinema.setCapaciteMaximale(Integer.parseInt(capacityField.getText()));
            cinema.setEstOuvert(openCheckBox.isSelected());
            cinema.setNombreDeSalles(Integer.parseInt(screensField.getText()));

            CinemaService.update(cinema);
            redirectToCinemaList();

            closeWindow();
        }
    }

    @FXML
    private void onCancelButtonClicked() {
        closeWindow();
        redirectToCinemaList();
    }

    private void closeWindow() {
        if (stage != null) {
            stage.close();
        }
    }

    public static class HelloController {
        @FXML
        private Label welcomeText;

        @FXML
        protected void onHelloButtonClick() {
            welcomeText.setText("Welcome to JavaFX Application!");
        }
    }
}