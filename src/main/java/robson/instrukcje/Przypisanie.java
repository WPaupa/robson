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
        return "Przypisanie";
    }

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").getAsString().equals(this.typ()));
        
        JsonObject wart = json.get("wyrazenie").getAsJsonObject();
        wartosc = Instrukcja.nowaInstrukcja(wart.get("typ").getAsString());
        assert wartosc != null;
        wartosc.robson(robson);
        wartosc.fromJson(wart);
        
        nazwa = json.get("nazwa").getAsString();
    }

    @Override
    public String toJava(String nazwaWyjscia) {
        String wynik = "{\n";
        wynik += "double " + nazwaWyjscia + "w = 0;\n";
        wynik += wartosc.toJava(nazwaWyjscia + "w") + "\n";
        wynik += nazwaWyjscia + " = " + nazwa + " = " + nazwaWyjscia + "w;\n";
        return wynik + "}";
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double war = wartosc.wykonaj();
        robson.ustawianieZmiennej(nazwa, war);
        return war;
    }
}
