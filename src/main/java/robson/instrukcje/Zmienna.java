package robson.instrukcje;

import com.google.gson.JsonObject;

public class Zmienna implements Instrukcja {
    
    String nazwa;
    
    @Override
    public String typ() {
        return "\"Zmienna\"";
    }

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").toString().equals(this.typ()));
        nazwa = json.get("nazwa").toString();
    }
}
