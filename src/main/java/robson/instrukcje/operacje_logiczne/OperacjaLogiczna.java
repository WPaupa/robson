package robson.instrukcje.operacje_logiczne;

import com.google.gson.JsonObject;
import robson.Robson;
import robson.instrukcje.Instrukcja;

public abstract class OperacjaLogiczna implements Instrukcja {
    // pakietowe, żeby były widoczne tylko w innych operacjach
    Instrukcja argument1, argument2;

    private String nazwaArg1() {
        if (robson.verbose())
            return "_argument1";
        return "1";
    }

    private String nazwaArg2() {
        if (robson.verbose())
            return "_argument2";
        return "2";
    }

    private transient Robson robson;

    @Override
    public void robson(Robson robson) {
        this.robson = robson;
    }

    @Override
    public void dodajZmienne() {
        argument1.dodajZmienne();
        argument2.dodajZmienne();
    }

    @Override
    public void fromJson(JsonObject json) {
        assert (json.get("typ").getAsString().equals(this.typ()));

        JsonObject arg1 = json.get("argument1").getAsJsonObject();
        argument1 = Instrukcja.nowaInstrukcja(arg1.get("typ").getAsString());
        assert argument1 != null;
        argument1.robson(robson);
        argument1.fromJson(arg1);

        JsonObject arg2 = json.get("argument2").getAsJsonObject();
        argument2 = Instrukcja.nowaInstrukcja(arg2.get("typ").getAsString());
        assert argument2 != null;
        argument2.robson(robson);
        argument2.fromJson(arg2);
    }

    @Override
    public String toJava(String nazwaWyjscia) {
        String wynik = "";
        wynik += "double " + nazwaWyjscia + nazwaArg1() + " = 0, " + nazwaWyjscia + nazwaArg2() + " = 0;\n";
        wynik += argument1.toJava(nazwaWyjscia + nazwaArg1()) + "\n";
        wynik += argument2.toJava(nazwaWyjscia + nazwaArg2()) + "\n";
        wynik += "if (" + nazwaWyjscia + nazwaArg1() + "== 1 " + symbolOperacji() + " " + nazwaWyjscia + nazwaArg2() + " == 1)\n "
                + nazwaWyjscia + " = 1;\n else\n " + nazwaWyjscia + " = 0;";
        return wynik;
    }

    // pakietowe, żeby było tylko w klasach operacji
    abstract String symbolOperacji();
}
