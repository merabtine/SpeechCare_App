module com.example {
    requires javafx.controls;
    requires javafx.fxml;

    opens TP.IU to javafx.fxml;
    exports TP.IU;
    opens TP.Control to javafx.fxml;
    exports TP.Control;
}

