package univr.ing.configuratore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utente {
    private final String customerPath = "database/customer.json";
    private final String employeePath = "database/employee.json";
    private final String usersPath = "database/users.json";

    private String userName;
    private String userBirthName;
    private String userPsw;
    private String userID;
    private boolean admin;

    // Costruttore
    public Utente (String userID) {
        getUserInfo(userID);
    }

    private void getUserInfo(String userID) {
        // Apertura del 'database' degli impiegati
        try {

            File usersData = new File(usersPath);

            // Ricerca dell'ID utente nel database degli utenti registrati

            String usersContent = new String(Files.readAllBytes(Paths.get(usersData.toURI())));
            JSONObject usersObj = new JSONObject(usersContent);
            JSONArray adminsArr = usersObj.getJSONArray("admins");
            JSONArray vendorsArr = usersObj.getJSONArray("vendors");
            JSONArray customersArr = usersObj.getJSONArray("customers");

            // Verifichiamo se l'utente che si sta autenticando e' un amministratore
            boolean find = false;
            for (int i = 0; i < adminsArr.length() && !find; i++) {
                JSONObject admin = (JSONObject) adminsArr.get(i);
                JSONObject vendor = (JSONObject) vendorsArr.get(i);
                JSONObject customer = (JSONObject) customersArr.get(i);

                if (userID.equals(admin.getString("username"))) {
                    System.out.println("Trovato in 'admins'!");
                    this.userName = admin.getString("nome");
                    this.userBirthName = admin.getString("cognome");
                    this.userPsw = admin.getString("password");
                    this.userID = userID;
                    find = true;
                } else if (userID.equals(vendor.getString("username"))) {
                    System.out.println("Trovato in 'vendors'!");
                    this.userName = vendor.getString("nome");
                    this.userBirthName = vendor.getString("cognome");
                    this.userPsw = vendor.getString("password");
                    this.userID = userID;
                    find = true;
                } else if(userID.equals(customer.getString("username"))) {
                    System.out.println("Trovato in 'customers'!");
                    this.userName = customer.getString("nome");
                    this.userBirthName = customer.getString("cognome");
                    this.userPsw = customer.getString("password");
                    this.userID = userID;
                    find = true;
                } else {
                    System.out.println("Non trovato");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean authenticator(String userID, String password) {
        if (userID.equals(this.userID) && password.equals(this.userPsw)) {
            System.out.println("L'utente " + userName + " " + userBirthName +
                    " si è autenticato con successo.");
            return true;
        }
        return false;
    }
}
