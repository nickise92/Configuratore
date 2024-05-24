package univr.ing.configuratore;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class AutoTest {

    private final String first = "Audi,A4,1,10000,22,33,44,90,1500,95";
    private final String second = "Mercedes,ClasseA,2,5000,12,13,14,60,2000,125";

    @Test
    void testConstructor() {

        Auto auto = new Auto(first);

        assertEquals(10, auto.getAuto().size());
        assertEquals("Audi", auto.getAuto().get(0));
        assertEquals("A4", auto.getAuto().get(1));
        assertEquals("1", auto.getAuto().get(2));
        assertEquals("10000", auto.getAuto().get(3));
        assertEquals("22", auto.getAuto().get(4));
        assertEquals("33", auto.getAuto().get(5));
        assertEquals("44", auto.getAuto().get(6));
        assertEquals("90", auto.getAuto().get(7));
        assertEquals("1500", auto.getAuto().get(8));
        assertEquals("95", auto.getAuto().get(9));

    }

    @Test
    void testToString() {
        Auto auto = new Auto(first);

        String expected = "Audi | A4 | 1 | 10000 | 22 | 33 | 44 | 90 | 1500 | 95 | \n";
        assertEquals(expected, auto.toString());
    }

    @Test
    void testMain() {

        List<Auto> catalogo = new ArrayList<>();

        catalogo.add(0, new Auto(first));
        catalogo.add(1, new Auto(second));

        assertEquals(2, catalogo.size());

        Auto audi = catalogo.get(0);
        assertEquals("Audi", audi.getAuto().get(0));
        assertEquals("A4", audi.getAuto().get(1));
        assertEquals("1", audi.getAuto().get(2));
        assertEquals("10000", audi.getAuto().get(3));
        assertEquals("22", audi.getAuto().get(4));
        assertEquals("33", audi.getAuto().get(5));
        assertEquals("44", audi.getAuto().get(6));
        assertEquals("90", audi.getAuto().get(7));
        assertEquals("1500", audi.getAuto().get(8));
        assertEquals("95", audi.getAuto().get(9));

        Auto mercedes = catalogo.get(1);
        assertEquals("Mercedes", mercedes.getAuto().get(0));
        assertEquals("ClasseA", mercedes.getAuto().get(1));
        assertEquals("2", mercedes.getAuto().get(2));
        assertEquals("5000", mercedes.getAuto().get(3));
        assertEquals("12", mercedes.getAuto().get(4));
        assertEquals("13", mercedes.getAuto().get(5));
        assertEquals("14", mercedes.getAuto().get(6));
        assertEquals("60", mercedes.getAuto().get(7));
        assertEquals("2000", mercedes.getAuto().get(8));
        assertEquals("125", mercedes.getAuto().get(9));
    }

    @Test
    void testEditCarInfo() {
        Auto car = new Auto(first);
        assertEquals("A4", car.getAuto().get(1));
        car.editCarInfo("A2", 1);
        assertEquals("A2", car.getAuto().get(1));
    }

    @Test
    void testGetImagePath() {
        Auto car = new Auto(first);
        String path = car.getImgPath(0).toLowerCase();
        final String realPath = "img/Audi/A4/0.jpg".toLowerCase();
        assertEquals(realPath, path);
    }
}