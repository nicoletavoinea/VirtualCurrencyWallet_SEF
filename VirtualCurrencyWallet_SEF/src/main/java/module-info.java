module com.example.virtualcurrencywallet_sef {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens com.example.virtualcurrencywallet_sef.Model;
    opens com.example.virtualcurrencywallet_sef to javafx.fxml;
    exports com.example.virtualcurrencywallet_sef;
    exports com.example.virtualcurrencywallet_sef.UserFunctions;
    opens com.example.virtualcurrencywallet_sef.UserFunctions to javafx.fxml;
    exports com.example.virtualcurrencywallet_sef.AdministratorFunctions;
    opens com.example.virtualcurrencywallet_sef.AdministratorFunctions to javafx.fxml;
    exports com.example.virtualcurrencywallet_sef.Authentication;
    opens com.example.virtualcurrencywallet_sef.Authentication to javafx.fxml;
}