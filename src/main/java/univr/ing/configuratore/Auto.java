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
    }

    public Auto(String desc) {
        for (int i = 0; i < 10; i++) {
            auto.add(desc.split(",")[i]);
        }
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
