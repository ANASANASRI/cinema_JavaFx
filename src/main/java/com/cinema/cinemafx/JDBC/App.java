package com.cinema.cinemafx.JDBC;

import com.cinema.cinemafx.JDBC.entities.Cinema;
import com.cinema.cinemafx.JDBC.service.CinemaService;

import java.util.List;

public class App {

    public static void main(String[] args) {
        CinemaService cinemaService = new CinemaService();

        System.out.println("Welcome to our cinema app!");

        //launchApp(args);

        // Create a new cinema
        //cinemaService.save(new Cinema("Megarama", "Casablanca", 200, true, 5, 212-1234567, 10000000.0, "50.0"));

        // Find all cinemas
        List<Cinema> cinemasData = cinemaService.findAll();
        if (cinemasData != null) {
            System.out.println("These are our cinemas:");

            cinemasData.forEach(System.out::println);

            // Export cinema data to Excel
            //cinemaService.exporterVersExcel();

            // Export cinema data to TXT
            //cinemaService.exportToTxt();

            // EXport json
            cinemaService.exportToJson();

        } else {
            System.out.println("No cinemas found.");
        }

    }
}