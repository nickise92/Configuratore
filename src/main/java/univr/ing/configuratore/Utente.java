package univr.ing.configuratore;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utente {
    private final String customerPath = "database/customer.json";
    private final String employeePath = "database/employee.json";

    private String userName;
    private String userPsw;
    private String userID;
    private boolean admin;

    // Costruttore
    public Utente (String userID) {
        getUserInfo(userID);
    }

    private void getUserInfo(String userID) {
        // Apertura del 'database' degli impiegati
        File employeeData = new File(employeePath);
        // Apertura del 'database' dei clienti
        File customerData = new File(customerPath);

        // Ricerca dell'ID utente nel database degli impiegati

        try {
            String employeeContent = new String(Files.readAllBytes(Paths.get(employeeData.toURI())));
            JSONObject employeeJson = new JSONObject(employeeContent);

            if (employeeJson.has(userID)) {
                this.userName = employeeJson.getString("username");
                this.userPsw = employeeJson.getString("password");
                this.userID = userID;
            } else {
                String customerContent = new String(Files.readAllBytes(Paths.get(customerData.toURI())));
                JSONObject customerJson = new JSONObject(customerContent);

                if (customerJson.has(userID)) {
                    this.userName = customerJson.getString("username");
                    this.userPsw = customerJson.getString("password");
                    this.userID = userID;
                } else {
                    /* TODO:
                     * mostrare un messaggio di avviso che la password o lo username
                     * sono incorretti o l'utente non e' registrato
                     */
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
