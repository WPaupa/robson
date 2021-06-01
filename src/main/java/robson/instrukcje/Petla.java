package robson.instrukcje;

import com.google.gson.JsonObject;
import robson.Robson;

public class Petla implements Instrukcja {
    
    private Instrukcja warunek;
    private Instrukcja blok;
    
    @Override
    public String typ() {
        return "\"Petla\"";
    }

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").toString().equals(this.typ()));
        
        JsonObject war = json.get("warunek").getAsJsonObject();
        JsonObject blo = json.get("blok").getAsJsonObject();
        
        warunek = Instrukcja.nowaInstrukcja(war.get("typ").toString());
        blok = Instrukcja.nowaInstrukcja(blo.get("typ").toString());
        
        warunek.fromJson(war);
        blok.fromJson(blo);
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
