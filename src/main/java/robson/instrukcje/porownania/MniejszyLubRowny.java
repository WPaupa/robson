package robson.instrukcje.porownania;

import robson.Robson;

public class MniejszyLubRowny extends Porownanie {
    @Override
    public String typ() {
        return "<=";
    }

    @Override
    public String toJava(String nazwaWyjscia) {
        String wynik = "";
        wynik += "double " + nazwaWyjscia + nazwaArg1() + " = 0, " + nazwaWyjscia + nazwaArg2() +" = 0;\n";
        wynik += argument1.toJava(nazwaWyjscia + nazwaArg1()) + "\n";
        wynik += argument2.toJava(nazwaWyjscia + nazwaArg2()) + "\n";
        wynik += "if (" + nazwaWyjscia + nazwaArg1() + " <= " + nazwaWyjscia + nazwaArg2() + ")\n "
                + nazwaWyjscia + " = 1;\n else\n " + nazwaWyjscia + " = 0;";
        return wynik;
    }
    
    @Override
    public double wykonaj() throws Robson.BladWykonania {
        if (argument1.wykonaj() <= argument2.wykonaj())
            return 1;
        return 0;
    }
}
