package robson.instrukcje.wartosci_logiczne;

import robson.Robson;

public class Falsz extends WartoscLogiczna {
    private static final String typ = "False";
    @Override
    public String typ() {
        return typ;
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
