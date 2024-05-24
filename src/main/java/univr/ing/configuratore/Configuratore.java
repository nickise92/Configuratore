package univr.ing.configuratore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Configuratore {

    public static void main(String[] args) {

        File autoCsv = new File("database/car.csv");

        try {
            Scanner scanner = new Scanner(autoCsv);
            List<Auto> cars = new ArrayList<Auto>();

            while (scanner.hasNextLine()) {
                cars.add(new Auto(scanner.nextLine()));
            }

            for (Auto c : cars) {
                System.out.println(c);
            }

            cars.get(0).editCarInfo("A2", 1);

            System.out.println(cars.get(0));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
