package robson.instrukcje.operacje_arytmetyczne;

import robson.Robson;

public class Dzielenie extends OperacjaArytmetyczna {
    @Override
    public String typ() {
        return "\"Dzielenie\"";
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double arg1 = argument1.wykonaj();
        double arg2 = argument2.wykonaj();
        if (arg2 == 0)
            throw new Robson.BladWykonania();
        if (nazwaZmiennej != null)
            Robson.ustawianieZmiennej(nazwaZmiennej, arg1 / arg2);
        return arg1 / arg2;
    }
}
