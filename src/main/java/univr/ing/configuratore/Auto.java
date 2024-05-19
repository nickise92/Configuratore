package univr.ing.configuratore;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;

public class Auto {

    private final String autoJsonPath = "database/auto_test.json";

    private String marca;
    private String nome;
    private String ID;
    private String descrizione;

    private double prezzo;

    private int altezza, larghezza, lunghezza, peso, volumeBagagliaio;
    private int[] motori;

    //private BufferedImage[] images;
    private int percentuale;
    private String mese;

    public Auto(String marca, String modello, String id, String descrizione, double prezzo, int height, int width,
                int length, int weight, int trunkVolume) {

        this.marca = marca;
        this.nome = modello;
        this.ID = id;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.altezza = height;
        this.larghezza = width;
        this.lunghezza = length;
        this.peso = weight;
        this.volumeBagagliaio = trunkVolume;

        addCar();

    }

    public Auto() {

    }


    private void addCar() {
        // First car
        JSONObject carJson = new JSONObject();
        carJson.put("marca", marca);
        carJson.put("modello", nome);
        carJson.put("ID", ID);
        carJson.put("descrizione", descrizione);
        carJson.put("prezzo", prezzo);
        carJson.put("altezza", altezza);
        carJson.put("larghezza", larghezza);
        carJson.put("lunghezza", lunghezza);
        carJson.put("peso", peso);
        carJson.put("volume", "volumeBagagliaio");

        // Adding the first car
        JSONObject carObject = new JSONObject();
        carObject.put("first", carJson);
        carObject.put("second", carJson);

        // Adding the objects to a list
        JSONArray cars = new JSONArray();
        cars.put(carObject);


        JSONObject carsJsonFile = new JSONObject();
        carsJsonFile.put("cars", cars);

        try (FileWriter file = new FileWriter(autoJsonPath )) {
            file.write(carsJsonFile.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }







    }




    public static void main(String[] args) {
        Auto test = new Auto("Audi", "A2", "123", "fancy ...", 134.55,
                10, 12, 14, 16,  110);
        try {
            File autofile = new File("database/auto_test.json");
            String jsonTest = new String(Files.readAllBytes(Paths.get(autofile.toURI())));

            JSONObject autoObj = new JSONObject(jsonTest);
            JSONArray carJsonArray = autoObj.getJSONArray("cars");
            for (int i = 0; i < carJsonArray.length(); i++) {

                JSONObject car = (JSONObject) carJsonArray.get(i);
                System.out.println(car.get("first"));
                System.out.println(car.get("second"));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }




    }

}


