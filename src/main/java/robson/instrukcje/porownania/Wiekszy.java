package robson.instrukcje.porownania;

import robson.Robson;

public class Wiekszy extends Porownanie {
    private static final String typ = ">";
    @Override
    public String typ() {
        return typ;
    }
    

    @Override
    String symbolOperacji() {
        return ">";
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        if (argument1.wykonaj() > argument2.wykonaj())
            return 1;
        return 0;
    }
}
