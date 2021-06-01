package robson.instrukcje;

import com.google.gson.JsonObject;

public class Liczba implements Instrukcja {
    private double wartosc;

    @Override
    public String typ() {
        return "\"Liczba\"";
    }

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").toString().equals(this.typ()));
        wartosc = json.get("wartosc").getAsDouble();
    }
}
