package univr.ing.configuratore;

import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Optional {
    private int ID;
    private double price;
    private String name;

    public static void main(String[] args) {
        Optional optional = new Optional();
        System.out.println();
    }

    public Optional() { getOptionalInfo();}


    private void getOptionalInfo() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("Configuratore-master/database/auto.json")));
            JSONObject jsonObject = new JSONObject(content);
            JSONArray OptionalArray = jsonObject.getJSONArray("Optional");

            for (int i = 0; i < OptionalArray.length(); i++){
                JSONObject OptionalObject = OptionalArray.getJSONObject(i);
                this.ID = OptionalObject.getInt("ID");
                this.name = OptionalObject.getString("nome");
                this.price = OptionalObject.getDouble("prezzo");
                System.out.println(this.ID + " " + this.name + " " + this.price);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
