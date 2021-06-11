package robson.instrukcje.porownania;

import robson.Robson;

public class Mniejszy extends Porownanie {
    private static final String typ = "<";
    @Override
    public String typ() {
        return typ;
    }
    
    @Override
    public double wykonaj() throws Robson.BladWykonania {
        if (argument1.wykonaj() < argument2.wykonaj())
            return 1;
        return 0;
    }

    @Override
    String symbolOperacji() {
        return "<";
    }
}
