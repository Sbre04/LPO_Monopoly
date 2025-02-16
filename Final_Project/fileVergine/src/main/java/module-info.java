module com.monopoly {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.monopoly.application to javafx.fxml;
    opens com.monopoly.controller to javafx.fxml;
    opens com.monopoly.model to javafx.fxml;

    exports com.monopoly.application;
    exports com.monopoly.controller;
    exports com.monopoly.model;
}
