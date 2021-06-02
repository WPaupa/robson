package robson.instrukcje;

import com.google.gson.JsonObject;
import robson.Robson;

public class Zmienna implements Instrukcja {
    
    private String nazwa;
    public String nazwa() {
        return nazwa;
    }
    private Robson robson;
    @Override
    public void robson(Robson robson) {
        this.robson = robson;
    }
    
    @Override
    public String typ() {
        return "Zmienna";
    }

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").getAsString().equals(this.typ()));
        nazwa = json.get("nazwa").getAsString();
    }

    @Override
    public String toJava(String nazwaWyjscia) {
        return nazwaWyjscia + " = " + nazwa + ";";
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        return robson.wartoscZmiennej(nazwa);
    }
}
