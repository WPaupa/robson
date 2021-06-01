package robson.instrukcje.operacje_arytmetyczne;

import com.google.gson.JsonObject;
import robson.instrukcje.Instrukcja;

public class Dzielenie extends OperacjaArytmetyczna {
    @Override
    public String typ() {
        return "\"Dzielenie\"";
    }
}
