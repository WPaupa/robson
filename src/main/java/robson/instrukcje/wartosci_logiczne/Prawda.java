package robson.instrukcje.wartosci_logiczne;

import robson.Robson;

public class Prawda extends WartoscLogiczna {
    private static final String typ = "True";

    @Override
    public String typ() {
        return typ;
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
