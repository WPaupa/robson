package robson.instrukcje.wartosci_logiczne;

import com.google.gson.JsonObject;
import robson.Robson;

public class Prawda extends WartoscLogiczna {
    @Override
    public String typ() {
        return "\"True\"";
    }

    @Override
    public String toJava(String nazwaWyjscia) {
        return nazwaWyjscia + " = 1;";
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        return 1;
    }
}
