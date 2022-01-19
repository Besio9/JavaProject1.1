module com.example.javafxgame0_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javafxgame0_2 to javafx.fxml;
    exports com.example.javafxgame0_2;
}