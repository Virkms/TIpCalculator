module virk.manpreet.tipcalculator {
    requires javafx.controls;
    requires javafx.fxml;

    opens virk.manpreet.tipcalculator to javafx.fxml;
    exports virk.manpreet.tipcalculator;
}
