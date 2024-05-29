package univr.ing.configuratore;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
    private final String enginePath = "database/engine.csv";
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
    @FXML private Label carWeight;
    @FXML private Label partialPrice;

    //TODO: Aggiungere colori e motorizzazioni ai menu' a tendina
    @FXML private ChoiceBox<String> carEngineChoice;
    @FXML private ChoiceBox<String> carColorChoice;

    @FXML private TextArea riepilogo;

    @FXML
    public void initialize() {
        carBrandChoice.setItems(brandList);
        carColorChoice.setItems(colorsList);
    }

    @FXML
    protected void onExitButtonClick() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onBrandSelection() {
        String brand = (String) carBrandChoice.getValue();
        /// DEBUG
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

        ObservableList<String> engineList = FXCollections.observableArrayList();
        try {
            Scanner sc = new Scanner(new File(enginePath));
            String tmp = "";
            while (sc.hasNextLine()) {
                String motore = sc.nextLine();
                tmp += motore.split(",")[2] + " ";
                tmp += motore.split(",")[4];
                engineList.add(tmp);
                tmp = "";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        carEngineChoice.setItems(engineList);
    }

    @FXML
    protected void onModelSelection() {
        Auto tmpCar = new Auto(carBrandChoice.getValue(), carModelChoice.getValue());
        double carPrice = Double.valueOf(tmpCar.getPrice());
        double enginePrice = 47000.00;
        double price = carPrice + enginePrice;

        System.out.println(tmpCar.toString());
        setCarImg(tmpCar.getImgPath(0));

        carHeight.setText(tmpCar.getHeight());
        carWidth.setText(tmpCar.getWidth());
        carLength.setText(tmpCar.getLength());
        trunkVol.setText(tmpCar.getTrunkVol());
        partialPrice.setText(String.valueOf(price));
        carWeight.setText(tmpCar.getWeight());
        carColorChoice.setValue("Bianco");
        carEngineChoice.setValue("Benzina 150CV");
    }

    @FXML
    protected void onEngineSelection() {
        try {
            Scanner sc = new Scanner(new File(enginePath));
            String conf = "";
            double enginePrice;
            while (sc.hasNextLine()) {
                String eng = sc.nextLine();
                conf += eng.split(",")[2] + " ";
                conf += eng.split(",")[4];
                enginePrice = Double.valueOf(eng.split(",")[5]);

                if (conf.equals(carEngineChoice.getValue()) && !conf.equals("Benzina 150CV")) {

                    partialPrice.setText(String.valueOf(enginePrice));
                }

                conf = "";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        updateRiepilogo();
    }

    @FXML
    protected void onColorSelection() {
        String color = carColorChoice.getValue();
        String oldColor = "";
        double price;

        if (!color.equals("Bianco")) {
            oldColor = color;

            price = Double.valueOf(partialPrice.getText());
            price += 250.00;
            partialPrice.setText(String.valueOf(price));
        } else if (!oldColor.equals("Bianco")) {
            price = Double.valueOf(partialPrice.getText());
            price -= 250.00;
            partialPrice.setText(String.valueOf(price));
        }

        updateRiepilogo();
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

    private void updateRiepilogo() {
        String riep = "";
        if (carColorChoice.getValue() != null && !carColorChoice.getValue().equals("Bianco")) {
            riep += "Colore: " + carColorChoice.getValue() + " +250.00€\n";
        }

        if (carEngineChoice.getValue() != null && carEngineChoice.getValue().equals("Diesel 136CV")) {
            riep += "Motore: " + carEngineChoice.getValue() + " +1750.00€\n";
        }

        riepilogo.setText(riep);
    }
}