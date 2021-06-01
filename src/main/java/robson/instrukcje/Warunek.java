package robson.instrukcje;

import com.google.gson.JsonObject;

public class Warunek implements Instrukcja {
    
    private Instrukcja warunek;
    private Instrukcja blok_prawda;
    private Instrukcja blok_falsz;
    
    @Override
    public String typ() {
        return "\"Warunek\"";
    }

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").toString().equals(this.typ()));

        JsonObject war = json.get("warunek").getAsJsonObject();
        warunek = Instrukcja.nowaInstrukcja(war.get("typ").toString());
        assert warunek != null;
        warunek.fromJson(war);

        JsonObject prawda = json.get("blok_prawda").getAsJsonObject();
        blok_prawda = Instrukcja.nowaInstrukcja(war.get("typ").toString());
        assert blok_prawda != null;
        blok_prawda.fromJson(prawda);
        
        if (json.has("blok_falsz")) {
            JsonObject falsz = json.get("blok_falsz").getAsJsonObject();
            blok_falsz = Instrukcja.nowaInstrukcja(war.get("typ").toString());
            assert blok_falsz != null;
            blok_falsz.fromJson(falsz);
        } 
    }
}
