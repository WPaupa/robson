package robson.instrukcje.porownania;

import robson.Robson;

public class MniejszyLubRowny extends Porownanie {
    @Override
    public String typ() {
        return "<=";
    }

    @Override
    public String toJava(String nazwaWyjscia) {
        String wynik = "{\n";
        wynik += "double " + nazwaWyjscia + "1 = 0, " + nazwaWyjscia + "2 = 0;\n";
        wynik += argument1.toJava(nazwaWyjscia + "1") + "\n";
        wynik += argument2.toJava(nazwaWyjscia + "2") + "\n";
        wynik += "if (" + nazwaWyjscia + "1 <= " + nazwaWyjscia + "2)\n " + nazwaWyjscia + " = 1;\n else\n " + nazwaWyjscia + " = 0;\n";
        return wynik + "}";
    }
    
    @Override
    public double wykonaj() throws Robson.BladWykonania {
        if (argument1.wykonaj() <= argument2.wykonaj())
            return 1;
        return 0;
    }
}
