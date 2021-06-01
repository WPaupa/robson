package robson.instrukcje.porownania;

import com.google.gson.JsonObject;

public class MniejszyLubRowny extends Porownanie {
    @Override
    public String typ() {
        return "\"<=\"";
    }
}
