package univr.ing.configuratore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Auto {

    private List<String> auto = new ArrayList<String>();
    private final String imgPath = "img/";
    // private Engine engine;
    // private Engine defaultEngine;

    public List<String> getAuto() {
        return auto;
    }

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
        for (int i = 0; i < 10; i++) {
            auto.add(desc.split(",")[i]);
        }
    }

    private void getAutoInfo(String brand, String model) {
        File fd = new File("database/car.csv");
        try {
            Scanner sc = new Scanner(fd);
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
    }

    public String getHeight() {
        return auto.get(4);
    }
    public String getWidth() {
        return auto.get(5);
    }
    public String getLength() {
        return auto.get(6);
    }
    public String getWeight() {
        return auto.get(7);
    }
    public String getTrunkVol() {
        return auto.get(8);
    }
    public String getPrice() {
        return auto.get(2);
    }

    // Modifica del contenuto dell'auto
    public void editCarInfo(String modification, int index) {
        // rimozione dell'informazione obsoleta e aggiornamento
        // con la nuova informazione
        auto.set(index, modification);
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < 10; i++) {
            result += auto.get(i) + " | ";
        }
        result+="\n";
        return result;
    }
}
