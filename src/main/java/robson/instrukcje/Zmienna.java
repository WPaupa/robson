package robson.instrukcje;

import com.google.gson.JsonObject;
import robson.Robson;

public class Zmienna implements Instrukcja {

    private String nazwa;

    public String nazwa() {
        return nazwa;
    }

    private transient Robson robson;

    @Override
    public void robson(Robson robson) {
        this.robson = robson;
    }

    @Override
    public void dodajZmienne() {
        robson.dodanieZmiennej(nazwa);
    }

    private static final String typ = "Zmienna";

    @Override
    public String typ() {
        return typ;
    }

    @Override
    public void stworzOdJsona(JsonObject json) {
        assert (json.get("typ").getAsString().equals(this.typ()));
        nazwa = json.get("nazwa").getAsString();
    }

    @Override
    public String toJava(String nazwaWyjscia) {
        return nazwaWyjscia + " = " + nazwa + ";";
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        return robson.wartoscZmiennej(nazwa);
    }
}
