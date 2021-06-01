package robson.instrukcje.operacje_arytmetyczne;

import com.google.gson.JsonObject;
import robson.Robson;

public class Razy extends OperacjaArytmetyczna {
    @Override
    public String typ() {
        return "\"Razy\"";
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double arg1 = argument1.wykonaj();
        double arg2 = argument2.wykonaj();
        if (nazwaZmiennej != null)
            Robson.ustawianieZmiennej(nazwaZmiennej, arg1 * arg2);
        return arg1 * arg2;
    }
}