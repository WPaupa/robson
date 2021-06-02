package robson.instrukcje;

import com.google.gson.JsonObject;
import robson.Robson;

public class Przypisanie implements Instrukcja {
    
    private String nazwa;
    private Instrukcja wartosc;
    private Robson robson;

    @Override
    public void robson(Robson robson) {
        this.robson = robson;
    }
    
    @Override
    public String typ() {
        return "\"Przypisanie\"";
    }

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").toString().equals(this.typ()));
        
        JsonObject wart = json.get("wyrazenie").getAsJsonObject();
        wartosc = Instrukcja.nowaInstrukcja(wart.get("typ").toString());
        assert wartosc != null;
        wartosc.robson(robson);
        wartosc.fromJson(wart);
        
        nazwa = json.get("nazwa").toString();
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double war = wartosc.wykonaj();
        robson.ustawianieZmiennej(nazwa, war);
        return war;
    }
}
