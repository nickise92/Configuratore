package univr.ing.configuratore;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Configuratore {

    private final BooleanProperty isClient = new SimpleBooleanProperty();
    private final BooleanProperty isLogged = new SimpleBooleanProperty();
    private final StringProperty userNameToPass = new SimpleStringProperty();

    public StringProperty userNAmeToPassProperty() {
        return userNameToPass;
    }

    public final String getText() {
        return userNAmeToPassProperty().get();
    }

    public final void setText(String username) {
        userNAmeToPassProperty().set(username);
    }



    public static void main(String[] args) {
    }
}
