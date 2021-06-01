package robson.instrukcje;

import com.google.gson.JsonObject;

public class Przypisanie implements Instrukcja {
    
    private String nazwa;
    private Instrukcja wartosc;
    
    @Override
    public String typ() {
        return "\"Przypisanie\"";
    }

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").toString().equals(this.typ()));
        
        JsonObject wart = json.get("wartosc").getAsJsonObject();
        wartosc = Instrukcja.nowaInstrukcja(wart.get("typ").toString());
        assert wartosc != null;
        wartosc.fromJson(wart);
        
        nazwa = json.get("nazwa").toString();
    }
}
