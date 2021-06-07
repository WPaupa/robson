package robson.instrukcje.operacje_arytmetyczne;

import robson.Robson;

public class Dzielenie extends OperacjaArytmetyczna {
    @Override
    public String typ() {
        return "Dzielenie";
    }

    @Override
    public String toJava(String nazwaWyjscia) {
        String wynik = "";
        wynik += "double " + nazwaWyjscia + nazwaArg1() +" = 0, " + nazwaWyjscia + nazwaArg2() + " = 0;\n";
        wynik += argument1.toJava(nazwaWyjscia + nazwaArg1()) + "\n";
        wynik += argument2.toJava(nazwaWyjscia + nazwaArg2()) + "\n";
        wynik += nazwaWyjscia + " = " + nazwaWyjscia + nazwaArg1() + " / " + nazwaWyjscia + nazwaArg2() + ";";
        return wynik;
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double arg1 = argument1.wykonaj();
        double arg2 = argument2.wykonaj();
        if (arg2 == 0)
            throw new Robson.BladWykonania();
        return arg1 / arg2;
    }
}
