package robson.instrukcje.wartosci_logiczne;

import robson.Robson;

public class Falsz extends WartoscLogiczna {
    @Override
    public String typ() {
        return "False";
    }

    @Override
    public String toJava(String nazwaWyjscia) {
        return nazwaWyjscia + " = 0;";
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        return 0;
    }
}
