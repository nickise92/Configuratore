package univr.ing.configuratore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AutoCsv {

    private List<String> auto = new ArrayList<String>();


    public List<String> getAuto() {
        return auto;
    }

    public AutoCsv (String desc) {
        for (int i = 0; i < 12; i++) {
            auto.add(desc.split(",")[i]);
           //System.out.println(desc.split("i"));
        }
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < 12; i++) {
            result += auto.get(i) + " | ";
        }
        result+="\n";
        return result;
    }

/*    public static void main(String[] args) {

        String path = "database/car.csv";
        List<AutoCsv> catalogo = new ArrayList<AutoCsv>();

        File macchine = new File(path);
        try {
            Scanner reader = new Scanner(macchine);

            while(reader.hasNextLine()) {
                String line = reader.nextLine();
                AutoCsv auto = new AutoCsv(line);
                catalogo.add(auto);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for(AutoCsv auto : catalogo) {
            System.out.println(auto);
        }
    }*/


}
