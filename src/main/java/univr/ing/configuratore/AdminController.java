package univr.ing.configuratore;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminController {

    @FXML private Button showEstimated;
    @FXML private Button insertEditOptional;
    @FXML private Button insertEditAuto;
    @FXML private Button exit;

    @FXML
    protected void onExitButton() {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
}
