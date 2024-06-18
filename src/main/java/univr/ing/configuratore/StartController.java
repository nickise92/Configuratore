package univr.ing.configuratore;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {


    @FXML private Button loginBtn;
    @FXML private Button startConfiguratorBtn;
    @FXML private Button registerBtn;



    @FXML
    protected void onLoginButtonClick() throws IOException {
        Stage loginStage = new Stage();
        LoginApp login = new LoginApp();
        login.start(loginStage);
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onRegisterButtonClick() throws Exception {
        Stage registrationApp = new Stage();
        RegistrationApp registration = new RegistrationApp();
        registration.start(registrationApp);

    }

    @FXML
    protected void onStartConfiguratorClick() throws IOException {
        Stage configuratorApp = new Stage();
        ConfiguratorApplication configurator = new ConfiguratorApplication();
        configurator.start(configuratorApp);
        Stage tmp = (Stage) startConfiguratorBtn.getScene().getWindow();
        tmp.close();
    }
}
