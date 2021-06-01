package robson.instrukcje.porownania;

import com.google.gson.JsonObject;
import robson.Robson;

public class MniejszyLubRowny extends Porownanie {
    @Override
    public String typ() {
        return "\"<=\"";
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        if (argument1.wykonaj() <= argument2.wykonaj())
            return 1;
        return 0;
    }
}
