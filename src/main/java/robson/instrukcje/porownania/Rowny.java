package robson.instrukcje.porownania;

import robson.Robson;

public class Rowny extends Porownanie {
    @Override
    public String typ() {
        return "==";
    }

    @Override
    String symbolOperacji() {
        return "==";
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        if (argument1.wykonaj() == argument2.wykonaj())
            return 1;
        return 0;
    }
}
