module P212.INFOMAN.SALCEDO {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.SALCEDO.controller to javafx.fxml, javafx.base;
    exports com.SALCEDO.controller;

    opens com.SALCEDO to javafx.fxml;
    exports com.SALCEDO;
}