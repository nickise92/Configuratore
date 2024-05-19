package univr.ing.configuratore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ConfiguratorApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 200);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public void alertDialog(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ConfiguratorApplication.class.getResource("dialog-view.fxml"));
        Scene dialogScene = new Scene(fxmlLoader.load(), 600, 150);
        stage.setTitle("Attenzione!");
        stage.setScene(dialogScene);
        stage.show();
    }
}
