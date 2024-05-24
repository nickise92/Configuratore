package univr.ing.configuratore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Utente {
    private final String usersPath = "database/users.csv";

    private String userName;
    private String userLastName;
    private String userPsw;
    private String userID;

    // Costruttore di un nuovo utente
    public Utente(String userID, String userName, String userLastName, String userPsw) {
        this.userID = userID;
        this.userName = userName;
        this.userLastName = userLastName;
        this.userPsw = userPsw;
    }

    public Utente(String userID, String userPsw) {

    }

    // Aggiungi un utente al database
    public void addUserToDB() {
        try {
            File dbFile = new File(usersPath);
            FileWriter writer = new FileWriter(dbFile, true);
            String tmp = userID + "," + userName + ","
                    + userLastName + "," + userPsw + ",\n";
            System.out.println(tmp);
            writer.write(tmp);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Utente getUserInfo(String userID) {
        // Apertura del 'database' degli impiegati
        try {

            File usersData = new File(usersPath);
            Scanner sc = new Scanner(usersData);

            // Verifichiamo se l'utente che si sta autenticando e' un amministratore
            boolean find = false;
            while (sc.hasNextLine() && !find) {
                String[] user = sc.nextLine().split("");

                if (userID.equals(user[0])) {
                    find = true;
                    return new Utente(user[0], user[1], user[2], user[3]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }

    public boolean authenticator() {
        // TODO sistemare il metodo
        if (userID.equals(this.userID) && password.equals(this.userPsw)) {
            System.out.println("L'utente " + userName + " " + userLastName +
                    " si è autenticato con successo.");
            return true;
        }
        return false;
    }
}
