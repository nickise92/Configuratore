package univr.ing.configuratore;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class VendorController {

    private Venditore vendor;

    @FXML private Button manageOrder;

    public void onManageOrder() {
        Stage closing = (Stage) manageOrder.getScene().getWindow();
        closing.close();

        Stage orderManagementStage = new Stage();
        GestioneOrdiniApplication orderManagementForm = new GestioneOrdiniApplication();
        try {
            orderManagementForm.start(orderManagementStage);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
