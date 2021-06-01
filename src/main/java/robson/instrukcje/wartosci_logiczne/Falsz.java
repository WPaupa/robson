package robson.instrukcje.wartosci_logiczne;

import com.google.gson.JsonObject;
import robson.Robson;

public class Falsz extends WartoscLogiczna {
    @Override
    public String typ() {
        return "\"False\"";
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        return 0;
    }
}
