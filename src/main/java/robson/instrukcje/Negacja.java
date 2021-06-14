package robson.instrukcje;

import com.google.gson.JsonObject;
import robson.Robson;

public class Negacja implements Instrukcja {

    private Instrukcja argument;
    private transient Robson robson;

    @Override
    public void robson(Robson robson) {
        this.robson = robson;
    }

    @Override
    public void dodajZmienne() {
        argument.dodajZmienne();
    }

    private static final String typ = "Not";

    @Override
    public String typ() {
        return typ;
    }

    @Override
    public void stworzOdJsona(JsonObject json) {
        assert (json.get("typ").getAsString().equals(this.typ()));

        JsonObject arg = json.get("argument").getAsJsonObject();
        argument = Instrukcja.nowaInstrukcja(arg.get("typ").getAsString());
        assert argument != null;
        argument.robson(robson);
        argument.stworzOdJsona(arg);
    }

    @Override
    public String toJava(String nazwaWyjscia) {
        String wynik = "";
        wynik += argument.toJava(nazwaWyjscia) + "\n";
        wynik += "if (" + nazwaWyjscia + " == 0)\n " + nazwaWyjscia + " = 1;\n else\n " + nazwaWyjscia + " = 0;";
        return wynik;
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double arg = argument.wykonaj();
        if (arg != 0)
            return 0;
        return 1;
    }
}
