package univr.ing.configuratore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ConfiguratorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ConfiguratorApplication.class.getResource("controller-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1080, 920);
        stage.setTitle("Configuratore auto v1.0");
        stage.setScene(scene);
        stage.show();
    }

/*    public static void main(String[] args) {
        launch();
    }*/
}