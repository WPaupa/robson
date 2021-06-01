package robson.instrukcje.wartosci_logiczne;

import com.google.gson.JsonObject;

public class Prawda extends WartoscLogiczna {
    @Override
    public String typ() {
        return "\"True\"";
    }
}
