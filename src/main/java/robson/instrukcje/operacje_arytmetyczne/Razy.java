package robson.instrukcje.operacje_arytmetyczne;

import robson.Robson;

public class Razy extends OperacjaArytmetyczna {
    @Override
    public String typ() {
        return "Razy";
    }
    
    @Override
    public String symbolOperacji() {
        return "*";
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double arg1 = argument1.wykonaj();
        double arg2 = argument2.wykonaj();
        return arg1 * arg2;
    }
}
