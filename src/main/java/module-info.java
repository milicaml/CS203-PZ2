module com.cs203pz2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cs203pz2 to javafx.fxml;
    exports com.cs203pz2;
}