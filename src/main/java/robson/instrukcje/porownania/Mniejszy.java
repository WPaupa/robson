package robson.instrukcje.porownania;

import com.google.gson.JsonObject;

public class Mniejszy extends Porownanie {
    @Override
    public String typ() {
        return "\"<\"";
    }
}
