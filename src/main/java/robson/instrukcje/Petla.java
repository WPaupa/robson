package robson.instrukcje;

import com.google.gson.JsonObject;

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
}
