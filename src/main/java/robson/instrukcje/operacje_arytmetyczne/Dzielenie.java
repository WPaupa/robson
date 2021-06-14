package robson.instrukcje.operacje_arytmetyczne;

import robson.Robson;

public class Dzielenie extends OperacjaArytmetyczna {
    // zapisane jako atrybut, żeby wypisało się do jsona
    // tak samo w każdej innej konkretnej podklasie Instrukcji (ale nie warto kopiować tego komentarza wszędzie)
    private static final String typ = "Dzielenie";

    @Override
    public String typ() {
        return typ;
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double arg1 = argument1.wykonaj();
        double arg2 = argument2.wykonaj();
        if (arg2 == 0)
            throw new Robson.BladWykonania();
        return arg1 / arg2;
    }

    @Override
    public String symbolOperacji() {
        return "/";
    }
}
