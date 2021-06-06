package robson.instrukcje;

import com.google.gson.JsonObject;
import robson.Robson;

public class Petla implements Instrukcja {
    
    private Instrukcja warunek;
    private Instrukcja blok;
    private Robson robson;

    @Override
    public void robson(Robson robson) {
        this.robson = robson;
    }
    
    @Override
    public String typ() {
        return "While";
    }

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").getAsString().equals(this.typ()));
        
        JsonObject war = json.get("warunek").getAsJsonObject();
        JsonObject blo = json.get("blok").getAsJsonObject();
        
        warunek = Instrukcja.nowaInstrukcja(war.get("typ").getAsString());
        blok = Instrukcja.nowaInstrukcja(blo.get("typ").getAsString());
        
        assert warunek != null;
        assert blok != null;

        warunek.robson(robson);
        blok.robson(robson);
        
        warunek.fromJson(war);
        blok.fromJson(blo);
    }

    @Override
    public String toJava(String nazwaWyjscia) {
        String wynik = "";
        wynik += "double " + nazwaWyjscia + "w = 0, " + nazwaWyjscia + "d = 0;\n";
        wynik += warunek.toJava(nazwaWyjscia + "w");
        wynik += "while (" + nazwaWyjscia + "w != 0)\n{\n";
        wynik += "double " + nazwaWyjscia + "b = 0;\n";
        wynik += blok.toJava(nazwaWyjscia + "b") + "\n";
        wynik += warunek.toJava(nazwaWyjscia + "d") + "\n";
        wynik += nazwaWyjscia + "w = " + nazwaWyjscia + "d;\n";
        wynik += "}\n " + nazwaWyjscia + " = 0;";
        return wynik;
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double war = warunek.wykonaj();
        if (war != 0 && war != 1)
            throw new Robson.BladWykonania();
        while (war == 1) {
            blok.wykonaj();
            war = warunek.wykonaj();
            if (war != 0 && war != 1)
                throw new Robson.BladWykonania();
        }
        
        return 0;
    }
}
