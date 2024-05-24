package univr.ing.configuratore;

import java.io.*;
import java.util.Scanner;

public class Utente {
    private final String usersPath = "database/users.csv";

    private String userName;
    private String userLastName;
    private String userPsw;
    private String userID;

    // Costruttore di un nuovo utente usato per la registrazione
    public Utente(String userID, String userName, String userLastName, String userPsw) {
        this.userID = userID;
        this.userName = userName;
        this.userLastName = userLastName;
        this.userPsw = userPsw;
        if (!alreadyRegistered(userID)) {
            /* Aggiunge il nuovo utente solo nel caso in cui non
            sia gia' registrato */
            this.addUserToDB();
        } else {
            System.out.println("Non e' stato possibile aggiungere l'utente");
            //TODO: aggiungere gestione fallimento registrazione
        }
    }

    // Costruttore che permette di recuperare le informazioni di un utente esistente
    public Utente(String user) {
        getUserInfo(user);
    }

    public String getUserID() {
        return userID;
    }
    public String getUserName() {
        return userName;
    }
    public String getUserLastName() {
        return userLastName;
    }
    public String getUserPsw() {
        return userPsw;
    }


    // Verifica se l'utente che si sta registrando e' gia' registrato
    private boolean alreadyRegistered(String id) {
        try {
            Scanner sc = new Scanner(new File(usersPath));
            while (sc.hasNextLine()) {
                if (id.equals(sc.nextLine().split(",")[0])) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }


    // Aggiungi un utente al database
    private void addUserToDB() {
        try {
            FileWriter fwr = new FileWriter(usersPath, true);
            String tmp = userID + "," + userName + ","
                    + userLastName + "," + userPsw + ",\n";
            fwr.append(tmp);
            fwr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getUserInfo(String id) {
        System.out.println("Recupero informazioni utente " + id + "...");

        try {
            // Apertura del 'database' degli impiegati
            File usersData = new File(usersPath);
            Scanner sc = new Scanner(usersData);

            // Verifico se lo user ID inserito e' presente nel sistema
            boolean find = false;
            while (sc.hasNextLine() && !find) {
                String line = sc.nextLine();
                String[] user = line.split(",");
                if (id.equals(user[0])) {
                    // se trovo l'utente recupero le informazioni
                    find = true;
                    this.userID = user[0];          // user ID
                    this.userName = user[1];        // user name
                    this.userLastName = user[2];    // user last name
                    this.userPsw = user[3];         // user password
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean authenticator(String user, String password) {
        if (user.equals(this.userID) && password.equals(this.userPsw)) {
            System.out.println("L'utente " + userName + " " + userLastName +
                    " si è autenticato con successo.");
            return true;
        }
        return false;
    }

}
