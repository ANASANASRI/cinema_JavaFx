module com.cinema.cinemafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires poi;
    requires poi.ooxml;
    requires com.google.gson;
    requires json.simple;
    requires com.fasterxml.jackson.databind;


    opens com.cinema.cinemafx to javafx.fxml;
    exports com.cinema.cinemafx;
    opens com.cinema.cinemafx.JDBC.entities;
    exports com.cinema.cinemafx.tp1;
    opens com.cinema.cinemafx.tp1 to javafx.fxml;

    // Export the package containing Cinema class
    exports com.cinema.cinemafx.JDBC.entities;

}