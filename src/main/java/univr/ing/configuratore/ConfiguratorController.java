package univr.ing.configuratore;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class ConfiguratorController {

    private final String carsPath = "database/car.csv";
    // Adding the ranges to the dropdown menu
    private ObservableList<String> brandList = FXCollections.observableArrayList(
            "Audi", "Mercedes", "Opel", "Fiat");
    private ObservableList<String> colorsList = FXCollections.observableArrayList(
            "Bianco", "Rosso", "Blu", "Nero"
    );


    @FXML private Button exitButton;
    @FXML private Button loginButton;
    @FXML private Button registerButton;
    @FXML private Button goBackButton;
    @FXML private Button nextButton;
    @FXML private ImageView carImg = new ImageView();
    @FXML private ChoiceBox<String> carBrandChoice;
    @FXML private ChoiceBox<String> carModelChoice;

    // TODO: Popolare le info dell'auto selezionata
    @FXML private Label carHeight;
    @FXML private Label carWidth;
    @FXML private Label carLength;
    @FXML private Label trunkVol;

    //TODO: Aggiungere colori e motorizzazioni ai menu' a tendina
    @FXML private ChoiceBox<String> carEngineChoice;
    @FXML private ChoiceBox<String> carColorChoice;

    @FXML
    public void initialize() {
        carBrandChoice.setItems(brandList);
    }

    @FXML
    protected void onExitButtonClick() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onBrandSelection() {
        String brand = (String) carBrandChoice.getValue();
        System.out.println(brand);


        ObservableList<String> modelList = FXCollections.observableArrayList();
        File db = new File(carsPath);
        try {
            Scanner sc = new Scanner(db);
            while(sc.hasNextLine()) {
                String[] tmp = sc.nextLine().split(",");
                if (brand.equals(tmp[0])) {
                    modelList.add(tmp[1]);
                }
            }
            carModelChoice.setItems(modelList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onModelSelection() {
        Auto tmp = new Auto(carBrandChoice.getValue(), carModelChoice.getValue());
        setCarImg(tmp.getImgPath(0));

    }


    @FXML
    protected void setCarImg(String path) {
        Image img = new Image(new File(path).toURI().toString());
        System.out.println(new File(path).toURI().toString());
        carImg.setImage(img);
    }

    @FXML
    protected void onLoginButtonClick() throws IOException {
        Stage loginStage = new Stage();
        LoginApp login = new LoginApp();
        login.start(loginStage);
    }

    @FXML
    protected void onRegisterButtonClick() {
        // TODO creazione nuovo utente, users.json -> users.csv
    }

    @FXML
    protected void onGoBackButtonClick() {

    }

    @FXML
    protected void onNextButtonClick() {
        // TODO: Seconda pagina di modifiche e aggiunta optional
    }
}