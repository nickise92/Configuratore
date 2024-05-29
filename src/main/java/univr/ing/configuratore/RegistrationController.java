package univr.ing.configuratore;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegistrationController {

    @FXML private Button registrationButton;
    @FXML private TextField userID;
    @FXML private TextField userName;
    @FXML private TextField userLastName;
    @FXML private PasswordField userPsw1;
    @FXML private PasswordField userPsw2;




    @FXML
    protected void onConfirmButton() {

        if (userPsw1.getText().equals(userPsw2.getText())) {
            Utente user = new Utente(userID.getText(), userName.getText(), userLastName.getText(), userPsw1.getText());
            System.out.println("Utente generato con successo!");
        } else {
            //TODO: Error dialog, password does not match
        }
    }

    @FXML
    protected void onCancelButton() {
        Stage stage = (Stage) registrationButton.getScene().getWindow();
        stage.close();
    }

}
