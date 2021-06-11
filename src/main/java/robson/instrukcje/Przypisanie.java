package robson.instrukcje;

import com.google.gson.JsonObject;
import robson.Robson;

public class Przypisanie implements Instrukcja {
    
    private String nazwa;
    private Instrukcja wartosc;
    private transient Robson robson;

    @Override
    public void robson(Robson robson) {
        this.robson = robson;
    }

    @Override
    public void dodajZmienne() {
        robson.dodanieZmiennej(nazwa);
        wartosc.dodajZmienne();
    }

    private static final String typ = "Przypisanie";
    @Override
    public String typ() {
        return typ;
    }

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").getAsString().equals(this.typ()));
        
        JsonObject wart = json.get("wartosc").getAsJsonObject();
        wartosc = Instrukcja.nowaInstrukcja(wart.get("typ").getAsString());
        assert wartosc != null;
        wartosc.robson(robson);
        wartosc.fromJson(wart);
        
        nazwa = json.get("nazwa").getAsString();
    }
    
    private String nazwaWartosci() {
        if (robson.verbose())
            return "_wartoscDoPrzypisania";
        return "w";
    }

    @Override
    public String toJava(String nazwaWyjscia) {
        String wynik = "";
        wynik += "double " + nazwaWyjscia + nazwaWartosci() + " = 0;\n";
        wynik += wartosc.toJava(nazwaWyjscia + nazwaWartosci()) + "\n";
        wynik += nazwaWyjscia + " = " + nazwa + " = " + nazwaWyjscia + nazwaWartosci() + ";";
        return wynik;
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double war = wartosc.wykonaj();
        robson.ustawianieZmiennej(nazwa, war);
        return war;
    }
}
