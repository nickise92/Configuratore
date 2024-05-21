package univr.ing.configuratore;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AutoCsvTest {

    @Test
    void testConstructor() {
        String desc = "Audi,A4,1,10000,22,33,44,90,150,95,120,175";
        AutoCsv autoCsv = new AutoCsv(desc);

        assertEquals(12, autoCsv.getAuto().size());
        assertEquals("Audi", autoCsv.getAuto().get(0));
        assertEquals("A4", autoCsv.getAuto().get(1));
        assertEquals("1", autoCsv.getAuto().get(2));
        assertEquals("10000", autoCsv.getAuto().get(3));
        assertEquals("22", autoCsv.getAuto().get(4));
        assertEquals("33", autoCsv.getAuto().get(5));
        assertEquals("44", autoCsv.getAuto().get(6));
        assertEquals("90", autoCsv.getAuto().get(7));
        assertEquals("150", autoCsv.getAuto().get(8));
        assertEquals("95", autoCsv.getAuto().get(9));
        assertEquals("120", autoCsv.getAuto().get(10));
        assertEquals("175", autoCsv.getAuto().get(11));
    }

    @Test
    void testToString() {
        String desc = "Audi,A4,1,10000,22,33,44,90,150,95,120,175";
        AutoCsv autoCsv = new AutoCsv(desc);

        String expected = "Audi | A4 | 1 | 10000 | 22 | 33 | 44 | 90 | 150 | 95 | 120 | 175 | \n";
        assertEquals(expected, autoCsv.toString());
    }

    @Test
    void testMain() throws FileNotFoundException {
        String path = "database/car.csv";
        List<AutoCsv> catalogo = new ArrayList<>();

        File macchine = new File(path);
        Scanner reader = new Scanner(macchine);

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            AutoCsv auto = new AutoCsv(line);
            catalogo.add(auto);
        }

        assertEquals(2, catalogo.size());

        AutoCsv audi = catalogo.get(0);
        assertEquals("Audi", audi.getAuto().get(0));
        assertEquals("A4", audi.getAuto().get(1));
        assertEquals("1", audi.getAuto().get(2));
        assertEquals("10000", audi.getAuto().get(3));
        assertEquals("22", audi.getAuto().get(4));
        assertEquals("33", audi.getAuto().get(5));
        assertEquals("44", audi.getAuto().get(6));
        assertEquals("90", audi.getAuto().get(7));
        assertEquals("150", audi.getAuto().get(8));
        assertEquals("95", audi.getAuto().get(9));
        assertEquals("120", audi.getAuto().get(10));
        assertEquals("175", audi.getAuto().get(11));

        AutoCsv mercedes = catalogo.get(1);
        assertEquals("Mercedes", mercedes.getAuto().get(0));
        assertEquals("ClasseA", mercedes.getAuto().get(1));
        assertEquals("2", mercedes.getAuto().get(2));
        assertEquals("5000", mercedes.getAuto().get(3));
        assertEquals("12", mercedes.getAuto().get(4));
        assertEquals("13", mercedes.getAuto().get(5));
        assertEquals("14", mercedes.getAuto().get(6));
        assertEquals("60", mercedes.getAuto().get(7));
        assertEquals("200", mercedes.getAuto().get(8));
        assertEquals("65", mercedes.getAuto().get(9));
        assertEquals("125", mercedes.getAuto().get(10));
        assertEquals("300", mercedes.getAuto().get(11));
    }
}