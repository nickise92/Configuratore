package univr.ing.configuratore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VendorApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ConfiguratorApplication.class.getResource("vendor-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 280, 300);
        stage.setTitle("Gestione Vendite ");
        stage.setScene(scene);
        stage.show();
    }

    public void confermaOrdine(Stage stage) throws IOException {
        // TODO
    }

    public static void main(String[] args) {
        launch();
    }
}
