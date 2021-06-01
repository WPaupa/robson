package robson.instrukcje.porownania;

import com.google.gson.JsonObject;

public class Rowny extends Porownanie {
    @Override
    public String typ() {
        return "\"==\"";
    }
}
