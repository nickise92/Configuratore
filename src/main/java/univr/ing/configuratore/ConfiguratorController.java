package univr.ing.configuratore;

import javafx.beans.binding.Bindings;
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
import javafx.scene.layout.AnchorPane;
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
    private ObservableList<String> brandList = FXCollections.observableArrayList();
    private ObservableList<String> colorsList = FXCollections.observableArrayList(
            "Bianco", "Rosso", "Blu", "Nero", "Grigio"
    );

    private Auto configCar;
    private double carPrice;
    private double enginePrice;
    private double colorPrice;
    private double optionalPrice;

    @FXML private Label titleText;
    @FXML private AnchorPane anchorPane;
    @FXML private AnchorPane imageAnchor;
    @FXML private Button leftImgArrow;
    @FXML private Button rightImgArrow;
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
        // Binding di tutti i componenti dell'interfaccia alle dimensioni
        // della finestra principale.

        AnchorPane.setTopAnchor(titleText, 25.0);
        AnchorPane.setBottomAnchor(exitButton, 15.0);
        AnchorPane.setRightAnchor(exitButton, 15.0);
        AnchorPane.setBottomAnchor(loginButton, 15.0);
        AnchorPane.setBottomAnchor(registerButton, 15.0);
        AnchorPane.setBottomAnchor(goBackButton, 15.0);
        AnchorPane.setBottomAnchor(nextButton, 15.0);
        AnchorPane.setRightAnchor(imageAnchor, 20.0);
        AnchorPane.setTopAnchor(imageAnchor, 165.0);

        try {
            Scanner sc = new Scanner(new File("database/brand.csv"));
            for (String car : sc.nextLine().split(",")) {
                if (!car.equals("")) {
                    brandList.add(car);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Impossibile inizializzare le marche di auto.");
        }

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
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");
                if (line.length == 9) {
                    String tmpEng = line[1];
                        if (!tmpEng.equals("")) {
                            engineList.add(tmpEng);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        carEngineChoice.setItems(engineList);
    }

    @FXML
    protected void onModelSelection() {
        configCar = new Auto(carBrandChoice.getValue(), carModelChoice.getValue());
        System.out.println(configCar);

        carPrice = configCar.getPrice();
        enginePrice = configCar.getDefaultEngine().getPrice();
        colorPrice = 0;
        optionalPrice = 0;

        setCarImg(configCar.getImgPath(0));

        carHeight.setText(String.valueOf(configCar.getHeight()));
        carWidth.setText(String.valueOf(configCar.getWidth()));
        carLength.setText(String.valueOf(configCar.getLength()));
        trunkVol.setText(String.valueOf(configCar.getTrunkVol()));
        partialPrice.setText(String.valueOf(carPrice));
        carWeight.setText(String.valueOf(configCar.getWeight()));
        carColorChoice.setValue(configCar.getDefaultColor());
        carEngineChoice.setValue(configCar.getDefaultEngine().toString());
    }

    @FXML
    protected void onEngineSelection() {
        // Recuperiamo il nome del motore che vogliamo inserire nella vettura
        // e creiamo l'oggetto Engine corrispondente
        Engine engine = new Engine(carEngineChoice.getValue());
        enginePrice = engine.getPrice();
        partialPrice.setText(String.valueOf(carPrice));

        updateRiepilogo();
    }


    // Questo metodo gestisce la scelta del colore delle automobili
    @FXML
    protected void onColorSelection() {
        String color = carColorChoice.getValue();

        if (!color.equals(configCar.getDefaultColor()) && colorPrice == 0) {
            colorPrice = 800.00;
            partialPrice.setText(String.valueOf(carPrice));
        } else if (!color.equals(configCar.getDefaultColor()) && colorPrice > 0) {
            // Se cambio colore ma non ritorno a quello di default non cambia il prezzo
            // I colori costano tutti uguali
        } else if (color.equals(configCar.getDefaultColor()) && colorPrice > 0) {
            // Sono tortato al colore di default dopo averlo cambiato, quindi reimposto
            // il valore a 0
            colorPrice = 0;
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
        carPrice = colorPrice + enginePrice + optionalPrice;
        partialPrice.setText(String.valueOf(carPrice));
        String riep = "";
        if (carColorChoice.getValue() != null) {
            riep += "Colore: " + carColorChoice.getValue() + " " + colorPrice + ".00€\n";
        }

        if (carEngineChoice.getValue() != null) {
            riep += "Motore: " + carEngineChoice.getValue() + " " + enginePrice + ".00€\n";
        }

        riepilogo.setText(riep);
    }
}