package robson.instrukcje;

import com.google.gson.JsonObject;
import robson.Robson;

public class Zmienna implements Instrukcja {
    
    private String nazwa;
    public String nazwa() {
        return nazwa;
    }
    
    @Override
    public String typ() {
        return "\"Zmienna\"";
    }

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").toString().equals(this.typ()));
        nazwa = json.get("nazwa").toString();
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        return Robson.wartoscZmiennej(nazwa);
    }
}
