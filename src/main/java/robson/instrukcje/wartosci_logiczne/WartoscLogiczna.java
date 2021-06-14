package robson.instrukcje.wartosci_logiczne;

import com.google.gson.JsonObject;
import robson.Robson;
import robson.instrukcje.Instrukcja;

public abstract class WartoscLogiczna implements Instrukcja {
    // nie potrzebujemy robsona
    @Override
    public void robson(Robson robson) {
    }

    @Override
    public void stworzOdJsona(JsonObject json) {
    }

    @Override
    public void dodajZmienne() {
    }
}
