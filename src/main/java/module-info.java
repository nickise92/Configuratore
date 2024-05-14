module univr.ing.configuratore {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens univr.ing.configuratore to javafx.fxml;
    exports univr.ing.configuratore;
}