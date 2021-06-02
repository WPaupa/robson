package robson.instrukcje.operacje_logiczne;

import robson.Robson;

public class Oraz extends OperacjaLogiczna {
    @Override
    public String typ() {
        return "And";
    }

    @Override
    public String toJava(String nazwaWyjscia) {
        String wynik = "";
        wynik += "double " + nazwaWyjscia + "1 = 0, " + nazwaWyjscia + "2 = 0;\n";
        wynik += argument1.toJava(nazwaWyjscia + "1") + "\n";
        wynik += argument2.toJava(nazwaWyjscia + "2") + "\n";
        wynik += "if (" + nazwaWyjscia + "1 == 1 && " + nazwaWyjscia + "2 == 1)\n " + nazwaWyjscia + " = 1;\n else\n " + nazwaWyjscia + " = 0;";
        return wynik;
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
