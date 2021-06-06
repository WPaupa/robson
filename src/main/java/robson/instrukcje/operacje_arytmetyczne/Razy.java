package robson.instrukcje.operacje_arytmetyczne;

import robson.Robson;

public class Razy extends OperacjaArytmetyczna {
    @Override
    public String typ() {
        return "Razy";
    }
    
    @Override
    public String toJava(String nazwaWyjscia) {
        String wynik = "";
        wynik += "double " + nazwaWyjscia + "1 = 0, " + nazwaWyjscia + "2 = 0;\n";
        wynik += argument1.toJava(nazwaWyjscia + "1") + "\n";
        wynik += argument2.toJava(nazwaWyjscia + "2") + "\n";
        wynik += nazwaWyjscia + " = " + nazwaWyjscia + "1 * " + nazwaWyjscia + "2;";
        return wynik;
    }
    
    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double arg1 = argument1.wykonaj();
        double arg2 = argument2.wykonaj();
        return arg1 * arg2;
    }
}
