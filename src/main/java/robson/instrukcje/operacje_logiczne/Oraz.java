package robson.instrukcje.operacje_logiczne;

import com.google.gson.JsonObject;

public class Oraz extends OperacjaLogiczna {
    @Override
    public String typ() {
        return "\"And\"";
    }
}
