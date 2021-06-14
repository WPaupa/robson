package robson.instrukcje;

import com.google.gson.JsonObject;
import robson.Robson;

import java.util.Objects;

public class Warunek implements Instrukcja {

    private Instrukcja warunek;
    private Instrukcja blok_prawda;
    private Instrukcja blok_falsz;
    private transient Robson robson;

    @Override
    public void robson(Robson robson) {
        this.robson = robson;
    }

    @Override
    public void dodajZmienne() {
        warunek.dodajZmienne();
        blok_prawda.dodajZmienne();
        if (blok_falsz != null)
            blok_falsz.dodajZmienne();
    }

    private static final String typ = "If";

    @Override
    public String typ() {
        return typ;
    }

    @Override
    public void stworzOdJsona(JsonObject json) {
        assert (json.get("typ").getAsString().equals(this.typ()));

        JsonObject war = json.get("warunek").getAsJsonObject();
        warunek = Instrukcja.nowaInstrukcja(war.get("typ").getAsString());
        assert warunek != null;
        warunek.robson(robson);
        warunek.stworzOdJsona(war);

        JsonObject prawda = json.get("blok_prawda").getAsJsonObject();
        blok_prawda = Instrukcja.nowaInstrukcja(prawda.get("typ").getAsString());
        assert blok_prawda != null;
        blok_prawda.robson(robson);
        blok_prawda.stworzOdJsona(prawda);

        if (json.has("blok_falsz")) {
            JsonObject falsz = json.get("blok_falsz").getAsJsonObject();
            blok_falsz = Instrukcja.nowaInstrukcja(falsz.get("typ").getAsString());
            assert blok_falsz != null;
            blok_falsz.robson(robson);
            blok_falsz.stworzOdJsona(falsz);
        }
    }

    private String nazwaWarunku() {
        if (robson.gadatliwy())
            return "_warunek";
        return "w";
    }

    private String nazwaPrawdy() {
        if (robson.gadatliwy())
            return "_wartoscBlokuPrawda";
        return "p";
    }

    private String nazwaFalszu() {
        if (robson.gadatliwy())
            return "_wartoscBlokuFalsz";
        return "f";
    }

    @Override
    public String toJava(String nazwaWyjscia) {
        String wynik = "";
        wynik += "double " + nazwaWyjscia + nazwaWarunku() + " = 0, " +
                nazwaWyjscia + nazwaPrawdy() + " = 0, " + nazwaWyjscia + nazwaFalszu() + " = 0;\n";
        wynik += warunek.toJava(nazwaWyjscia + nazwaWarunku());
        wynik += "if (" + nazwaWyjscia + nazwaWarunku() + " != 0)\n";
        wynik += "{\n" + blok_prawda.toJava(nazwaWyjscia + nazwaPrawdy()) + "\n";
        wynik += nazwaWyjscia + " = " + nazwaWyjscia + nazwaPrawdy() + ";\n}\n";
        if (!Objects.isNull(blok_falsz)) {
            wynik += "else{\n" + blok_falsz.toJava(nazwaWyjscia + nazwaFalszu()) + "\n";
            wynik += nazwaWyjscia + " = " + nazwaWyjscia + nazwaFalszu() + ";\n}";
        } else
            wynik += "else\n" + nazwaWyjscia + " = 0;";
        return wynik;
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double war = warunek.wykonaj();
        if (war != 0)
            return blok_prawda.wykonaj();
        if (!Objects.isNull(blok_falsz))
            return blok_falsz.wykonaj();
        return 0;
    }
}
