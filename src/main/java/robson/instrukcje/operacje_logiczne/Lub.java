package robson.instrukcje.operacje_logiczne;

import robson.Robson;

public class Lub extends OperacjaLogiczna {
    private static final String typ = "Or";

    @Override
    public String typ() {
        return typ;
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double arg1 = argument1.wykonaj();
        double arg2 = argument2.wykonaj();
        if (arg1 != 0 || arg2 != 0)
            return 1;
        return 0;
    }

    @Override
    String symbolOperacji() {
        return "||";
    }
}
