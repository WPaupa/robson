package robson.instrukcje.wartosci_logiczne;

import com.google.gson.JsonObject;
import robson.Robson;

public class Prawda extends WartoscLogiczna {
    @Override
    public String typ() {
        return "\"True\"";
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        return 1;
    }
}
