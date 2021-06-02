package robson.instrukcje;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import robson.Robson;

import java.util.Objects;

public class Blok implements Instrukcja {
    private Instrukcja[] instrukcje;
    private Robson robson;

    @Override
    public void robson(Robson robson) {
        this.robson = robson;
    }

    @Override
    public String typ() {
        return "\"Blok\"";
    }

    @Override
    public String toJava(String nazwaWyjscia) {
        if (instrukcje.length == 0)
            return nazwaWyjscia + " = 0;";
        StringBuilder wynik = new StringBuilder("{\n");
        for (int i = 0; i < instrukcje.length; i++) {
            wynik.append("double ").append(nazwaWyjscia).append(i).append(" = 0;\n");
            wynik.append(instrukcje[i].toJava(nazwaWyjscia + i)).append("\n");
        }
        wynik.append(nazwaWyjscia).append(" = ").append(nazwaWyjscia).append(instrukcje.length - 1).append(";\n");
        return wynik.append("}").toString();
    }
    
    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").toString().equals(this.typ()));
        
        JsonArray ins = json.get("instrukcje").getAsJsonArray();
        
        instrukcje = new Instrukcja[ins.size()];
        
        for (int i = 0; i < ins.size(); i++) {
            instrukcje[i] = Instrukcja.nowaInstrukcja(ins.get(i).getAsJsonObject().get("typ").toString());
            assert instrukcje[i] != null;
            instrukcje[i].robson(robson);
            instrukcje[i].fromJson(ins.get(i).getAsJsonObject());
        }
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double wynik = 0;
        for (Instrukcja instrukcja : instrukcje) 
            wynik = instrukcja.wykonaj();
        return wynik;
    }
}
