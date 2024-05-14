package univr.ing.configuratore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StartApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 200);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}
