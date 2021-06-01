package robson.instrukcje.wartosci_logiczne;

import com.google.gson.JsonObject;

public class Falsz extends WartoscLogiczna {
    @Override
    public String typ() {
        return "\"False\"";
    }
}
