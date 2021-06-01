package robson.instrukcje.porownania;

import com.google.gson.JsonObject;

public class Wiekszy extends Porownanie {
    @Override
    public String typ() {
        return "\">\"";
    }
}
