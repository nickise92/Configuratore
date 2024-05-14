package univr.ing.configuratore;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    @FXML private Button exitButton;
    @FXML private Button loginButton;
    @FXML private Button registerButton;
    @FXML private Button goBackButton;
    @FXML private Button nextButton;

    @FXML
    protected void onExitButtonClick() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onLoginButtonClick() throws IOException {
        Stage loginStage = new Stage();
        LoginApp login = new LoginApp();
        login.start(loginStage);
    }

    @FXML
    protected void onRegisterButtonClick() {

    }

    @FXML
    protected void onGoBackButtonClick() {

    }

    @FXML
    protected void onNextButtonClick() {

    }
}