package robson.instrukcje;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import robson.Robson;

public class Blok implements Instrukcja {
    private Instrukcja[] instrukcje;

    @Override
    public String typ() {
        return "\"Blok\"";
    }

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").toString().equals(this.typ()));
        
        JsonArray ins = json.get("instrukcje").getAsJsonArray();
        
        instrukcje = new Instrukcja[ins.size()];
        
        for (int i = 0; i < ins.size(); i++) {
            instrukcje[i] = Instrukcja.nowaInstrukcja(ins.get(i).getAsJsonObject().get("typ").toString());
            assert instrukcje[i] != null;
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
