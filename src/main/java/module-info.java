module com.cinema.cinemafx {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.cinema.cinemafx to javafx.fxml;
    exports com.cinema.cinemafx;
}