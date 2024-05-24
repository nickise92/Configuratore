package univr.ing.configuratore;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Configuratore {

    public static void main(String[] args) {

        Utente user = new Utente("UT111C","Utonto","Test","TestPsw90");
        user.addUserToDB();

    }
}
