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
    public void dodajZmienne() {
    }

    private static final String typ = "Liczba";
    @Override
    public String typ() {
        return typ;
    }

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").getAsString().equals(this.typ()));
        wartosc = json.get("wartosc").getAsDouble();
    }

    @Override
    public String toJava(String nazwaWyjscia) {
        return nazwaWyjscia + " = " + wartosc + ";";
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        return wartosc;
    }
}
