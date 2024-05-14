package univr.ing.configuratore;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.*;

public class LoginController {

    @FXML protected TextField userName;
    @FXML protected PasswordField userPsw;

    @FXML
    protected void onAuthentication() {

    }

    @FXML
    protected void onClose() {
        Stage stage = (Stage) userName.getScene().getWindow();
        stage.close();
    }
}
