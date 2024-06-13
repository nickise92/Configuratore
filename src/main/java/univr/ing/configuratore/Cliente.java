package univr.ing.configuratore;

public class Cliente extends Utente {

    public Cliente(String userID, String userName, String userLastName, String userPsw) {
        super(userID, userName, userLastName, userPsw);
    }

    public Cliente(String user) {
        super(user);
    }
}
