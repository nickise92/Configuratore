module univr.ing.configuratore {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires com.google.gson;


    opens univr.ing.configuratore to javafx.fxml;
    exports univr.ing.configuratore;
}