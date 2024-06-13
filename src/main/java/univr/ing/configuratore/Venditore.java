package univr.ing.configuratore;

public class Venditore extends Utente {
    public Venditore(String userID, String userName, String userLastName, String userPsw) {
        super(userID, userName, userLastName, userPsw);
    }

    public Venditore(String userID) {
        super(userID);
    }
}
