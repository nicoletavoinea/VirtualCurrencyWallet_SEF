module com.example.virtualcurrencywallet_sef {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.virtualcurrencywallet_sef to javafx.fxml;
    exports com.example.virtualcurrencywallet_sef;
}