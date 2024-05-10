module univr.ing.configuratore {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens univr.ing.configuratore to javafx.fxml;
    exports univr.ing.configuratore;
}