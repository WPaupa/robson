package robson.instrukcje.operacje_logiczne;

import robson.Robson;

public class Oraz extends OperacjaLogiczna {
    private static final String typ = "And";
    @Override
    public String typ() {
        return typ;
    }

    @Override
    String symbolOperacji() {
        return "&&";
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double arg1 = argument1.wykonaj();
        double arg2 = argument2.wykonaj();
        if ((arg1 != 0 && arg1 != 1) || (arg2 != 0 && arg2 != 1))
            throw new Robson.BladWykonania();

        if (arg1 == 1 && arg2 == 1)
            return 1;
        return 0;
    }
}
