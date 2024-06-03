package univr.ing.configuratore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Auto {

    private List<String> auto = new ArrayList<>();
    private final String imgPath = "img/";
    private String brand;
    private String model;
    private double price;
    private double weight;
    private double height;
    private double width;
    private double length;
    private String defaultColor;

    private double trunkVolume;
    private Engine engine;
    private Engine defaultEngine;

    public List<String> getAuto() {
        return auto;
    }

   /* public String getImgPath(int index) {
        return imgPath + this.brand + "/" + this.model + "/"
                + index +".jpg";
    }*/
    public String getImgPath(int index) {
        return imgPath + auto.get(0) + "/" + auto.get(1) + "/"
                + index +".jpg";
    }

    public Auto(String brand, String model) {
        this.auto.add(brand);
        this.auto.add(model);
        getAutoInfo(brand, model);
    }

    public Auto(String desc) {
        for (int i = 0; i < 9; i++) {
            auto.add(desc.split(",")[i]);
        }
    }

    /**
     * Con questo metodo si recuperano dal database tutti i dati dell'auto
     * richiesta con i parametri.
     * @param brand -> Il nome della marca di auto
     * @param model -> Il nome del modello di auto
     */
    private void getAutoInfo(String brand, String model) {
        try {
            Scanner sc = new Scanner(new File("database/car.csv"));
            while (sc.hasNextLine()) {
                String[] car = sc.nextLine().split(",");
                if (brand.equals(car[0]) && model.equals(car[1])) {
                    for (int i = 2; i < car.length; i++) {
                        this.auto.add(car[i]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Popolo i campi...");
        /* L'auto generata e' una lista di stringhe, con la chiamata al metodo
         * successivo si ottiene la popolazione dei campi tipizzati della classe
         * necessari per successive operazioni aritmetiche.
         */
        populateAutoFields();
    }

    /* Trasforma le stringhe nei vari valori di default dell'auto
     * generata.
     */
    private void populateAutoFields() {
        this.brand = auto.get(0);
        this.model = auto.get(1);
        this.price = Double.parseDouble(auto.get(2));
        this.weight = Double.parseDouble(auto.get(3));
        this.height = Double.parseDouble(auto.get(4));
        this.width = Double.parseDouble(auto.get(5));
        this.length = Double.parseDouble(auto.get(6));
        this.trunkVolume = Double.parseDouble(auto.get(7));
        this.engine = new Engine(auto.get(8));
        this.defaultEngine = new Engine(auto.get(8));
        this.defaultColor = "Bianco";

    }


    public String getBrand() {
        return this.brand;
    }
    public String getModel() {
        return this.model;
    }
    public double getHeight() {
        return this.height;
    }
    public double getWidth() {
        return this.width;
    }
    public double getLength() {
        return this.length;
    }
    public double getWeight() {
        return this.weight;
    }
    public double getTrunkVol() {
        return this.trunkVolume;
    }
    // Questo metodo ritorna il valore del prezzo dell'auto
    // senza considerare il motore!
    public double getPrice() {
        return this.price;
    }
    public Engine getEngine() {
        return this.engine;
    }
    public Engine getDefaultEngine() {
        return this.defaultEngine;
    }
    public String getDefaultColor() {
        return this.defaultColor;
    }

    // Modifica del contenuto dell'auto
    public void editCarInfo(String modification, int index) {
        // rimozione dell'informazione obsoleta e aggiornamento
        // con la nuova informazione
        auto.set(index, modification);
    }

    public String toString() {
        return "" + this.brand + " " + this.model + " " + this.price;
    }
}
