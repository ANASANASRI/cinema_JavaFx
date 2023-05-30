package com.cinema.cinemafx;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.cinema.cinemafx.JDBC.service.CinemaService;
import com.cinema.cinemafx.JDBC.entities.Cinema;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class listcinema {
    @FXML
    private TableView<Cinema> tableViewCinema;
    @FXML
    private TableColumn<Cinema, String> tableColumnNom;
    @FXML
    private TableColumn<Cinema, String> tableColumnEmplacement;
    @FXML
    private TableColumn<Cinema, Integer> tableColumnCapaciteMaximale;
    @FXML
    private TableColumn<Cinema, Boolean> tableColumnEstOuvert;
    @FXML
    private TableColumn<Cinema, Integer> tableColumnNombreDeSalles;
    @FXML
    private TableColumn<Cinema, Double> tableColumnChiffreAffaireAnnuel;
    @FXML
    private TableColumn<Cinema, Double> tableColumnPrixMoyen;
    @FXML
    private TableColumn<Cinema, String> tableColumnNumeroDeTelephone;
    @FXML
    private TableColumn<Cinema, Integer> tableColumnId;
    @FXML
    private TableColumn<Cinema, Void> tableColumnEDIT;
    @FXML
    private TableColumn<Cinema, Void> tableColumnREMOVE;
    @FXML
    private Button btNew;

    @FXML
    private Button btImportTxt;

    @FXML
    private Button btImportExcel;

    private CinemaService cinemaService;

    public listcinema() {
        cinemaService = new CinemaService();
    }

    @FXML
    private void initialize() {
        populateCinemasTable();
        setupButtonActions();
    }

    private void populateCinemasTable() {
        List<Cinema> cinemasData = cinemaService.findAll();

        if (cinemasData != null) {
            System.out.println("CCCCCCCCCCCCCCCCCinemas!");
            cinemasData.forEach(System.out::println);
        } else {
            System.out.println("No cinemas found.");
        }

        tableColumnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tableColumnEmplacement.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
        tableColumnCapaciteMaximale.setCellValueFactory(new PropertyValueFactory<>("capaciteMaximale"));
        tableColumnEstOuvert.setCellValueFactory(new PropertyValueFactory<>("estOuvert"));
        tableColumnNombreDeSalles.setCellValueFactory(new PropertyValueFactory<>("nombreDeSalles"));
        tableColumnChiffreAffaireAnnuel.setCellValueFactory(new PropertyValueFactory<>("chiffreAffaireAnnuel"));
        tableColumnPrixMoyen.setCellValueFactory(new PropertyValueFactory<>("prixMoyen"));
        tableColumnNumeroDeTelephone.setCellValueFactory(new PropertyValueFactory<>("numeroDeTelephone"));
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));

        setupEditColumn();
        setupRemoveColumn();

        tableViewCinema.getItems().addAll(cinemasData);
    }

    private void setupButtonActions() {
        btNew.setOnAction(event -> {
            try {
                onBtNewAction();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void onBtNewAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("newcinema.fxml"));
        Parent root = loader.load();
        newcinema newCinemaController = loader.getController();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Add New Cinema");
        stage.show();

    }

    ////////////////////////////////////////////////////////////

    private void setupEditColumn() {
        tableColumnEDIT.setCellFactory(column -> {
            return new TableCell<>() {
                final Button editButton = new Button("EDIT");

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(editButton);
                        editButton.setOnAction(event -> {
                            Cinema cinema = getTableView().getItems().get(getIndex());
                            openEditCinemaForm(cinema);
                        });
                    }
                }
            };
        });
    }

    private void openEditCinemaForm(Cinema cinema) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editcinema.fxml"));
            Parent root = loader.load();

            // Get the controller instance
            editcinema controller = loader.getController();

            // Pass the cinema object to the controller
            controller.setCinema(cinema);

            // Create a new stage and set the scene
            Stage stage = new Stage();
            stage.setTitle("Edit Cinema");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/////////////////////////////////////////////////////////////////

    private void setupRemoveColumn() {
        tableColumnREMOVE.setCellFactory(column -> {
            return new TableCell<>() {
                final Button removeButton = new Button("REMOVE");

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(removeButton);
                        removeButton.setOnAction(event -> {
                            Cinema cinema = getTableView().getItems().get(getIndex());
                            CinemaService.remove(cinema);
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("listcinema.fxml"));
                                Parent root = loader.load();
                                Stage stage = (Stage) removeButton.getScene().getWindow();
                                stage.setScene(new Scene(root));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Removing cinema: " + cinema.getId());
                        });
                    }
                }
            };
        });
    }

    /////////////////////////////////////////////////////////////////
    @FXML
    private void importFromTxt(ActionEvent event) {

            cinemaService.importFromTxt("src\\main\\resources\\IMPORTXT.txt");
            System.out.println("Données importées avec succès depuis le fichier TXT");

    }

    @FXML
    private void importFromExcel(ActionEvent event) {

            cinemaService.importFromExcel("src\\main\\resources\\IMPORTEXCEL.xlsx");
            System.out.println("Données importées avec succès depuis le fichier Excel");

    }

}