package univr.ing.configuratore;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.json.*;

import java.io.IOException;

public class LoginController {

    @FXML protected TextField userName;
    @FXML protected PasswordField userPsw;
    @FXML protected Button okButton;

    @FXML
    protected void onAuthentication() {
        Utente utente = new Utente(userName.getText());
        System.out.println(utente.getUserID());
        if (utente.authenticator(userName.getText(), userPsw.getText())) {
            System.out.println("Access granted!");
            Stage stage = (Stage) userName.getScene().getWindow();
            stage.close();
        } else {
            System.out.println("Access denied!");
            Stage alertStage = new Stage();
            LoginApp alertMsg = new LoginApp();
            try {
                alertMsg.alertDialog(alertStage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    protected void onOKClick() {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onClose() {
        Stage stage = (Stage) userName.getScene().getWindow();
        stage.close();
    }
}
