package univr.ing.configuratore;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class UtenteTest {

    @Test
    void testUtenteConstructor() {
        Utente test = new Utente("UT999C", "Nome", "Cognome", "passWord");

        assertEquals("UT999C", test.getUserID());
        assertEquals("Nome", test.getUserName());
        assertEquals("Cognome", test.getUserLastName());
        assertEquals("passWord", test.getUserPsw());
    }

    @Test
    void addUserToDB() {
        Utente test = new Utente("UT999C", "Utente", "Test", "testPsw");
        //test.addUserToDB();

        File file = new File("database/users.csv");
        try {
            Scanner sc = new Scanner(file);
            String tmp = sc.nextLine();
            while(sc.hasNextLine()) {
                tmp = sc.nextLine();
            }
            assertEquals("UT999C,Utente,Test,testPsw,", tmp);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void authenticator() {
        // PB002A,Pietro,Bianchedi,admin02,
        Utente test = new Utente("PB002A");

        assertTrue(test.authenticator(test.getUserID(), "admin02"));
        assertFalse(test.authenticator(test.getUserID(), "admin01"));
    }
}