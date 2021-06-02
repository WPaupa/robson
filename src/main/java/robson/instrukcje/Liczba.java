package robson.instrukcje;

import com.google.gson.JsonObject;
import robson.Robson;

public class Liczba implements Instrukcja {
    private double wartosc;
    
    // nie potrzebujemy robsona
    @Override
    public void robson(Robson robson) {
    }

    @Override
    public String typ() {
        return "\"Liczba\"";
    }

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").toString().equals(this.typ()));
        wartosc = json.get("wartosc").getAsDouble();
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        return wartosc;
    }
}
