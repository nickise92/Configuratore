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
        Utente utente = Utente.checkID(userName.getText());
        // Se l'utente esiste, e le credenziali sono corrette il sistema verifica a quale
        // sotto categoria di utente appartiene, aprendo di conseguenza la corretta interfaccia
        if (utente != null && utente.authenticator(userName.getText(), userPsw.getText())) {
            System.out.println("Access granted!");
            Stage stage = (Stage) userName.getScene().getWindow();
            if (utente instanceof Impiegato) { // È amministratore
                Stage adminForm = new Stage();
                AdminApplication adminApp = new AdminApplication();
                try {
                    adminApp.start(adminForm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (utente instanceof Venditore) { // È negoziante
                Stage vendorForm = new Stage();
                VendorApplication vendorApp = new VendorApplication();
                try {
                    vendorApp.start(vendorForm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (utente instanceof Cliente) { // Apre il configuratore
                Stage configForm = new Stage();
                ConfiguratorApplication configApp = new ConfiguratorApplication();
                try {
                    configApp.start(configForm);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            stage.close();
        } else {
            // Se l'utente è nullo o le credenziali non sono corrette
            // il sistema mostra un messaggio di errore.
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
