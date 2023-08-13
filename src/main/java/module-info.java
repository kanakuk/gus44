module group1.gus01 {
    requires javafx.controls;
    requires javafx.fxml;
    opens group1.gus01 to javafx.fxml;
    //security file to allow
    opens other to javafx.base;
    exports group1.gus01;
}