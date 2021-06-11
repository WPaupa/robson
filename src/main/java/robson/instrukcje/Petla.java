package robson.instrukcje;

import com.google.gson.JsonObject;
import robson.Robson;

public class Petla implements Instrukcja {
    
    private Instrukcja warunek;
    private Instrukcja blok;
    private transient Robson robson;

    @Override
    public void robson(Robson robson) {
        this.robson = robson;
    }

    @Override
    public void dodajZmienne() {
        warunek.dodajZmienne();
        blok.dodajZmienne();
    }

    private static final String typ = "While";
    @Override
    public String typ() {
        return typ;
    }

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").getAsString().equals(this.typ()));
        
        JsonObject war = json.get("warunek").getAsJsonObject();
        JsonObject blo = json.get("blok").getAsJsonObject();
        
        warunek = Instrukcja.nowaInstrukcja(war.get("typ").getAsString());
        blok = Instrukcja.nowaInstrukcja(blo.get("typ").getAsString());
        
        assert warunek != null;
        assert blok != null;

        warunek.robson(robson);
        blok.robson(robson);
        
        warunek.fromJson(war);
        blok.fromJson(blo);
    }
    
    private String nazwaWarunkuZewn() {
        if (robson.verbose())
            return "_warunekPetli";
        return "w";
    }
    
    private String nazwaWarunkuWewn() {
        if (robson.verbose())
            return "_tymczasowyWarunekPetli";
        return "d";
    }
    
    private String nazwaBloku() {
        if (robson.verbose())
            return "_blokPetli";
        return "b";
    }

    @Override
    public String toJava(String nazwaWyjscia) {
        String wynik = "";
        wynik += "double " + nazwaWyjscia + nazwaWarunkuZewn() + " = 0, " + nazwaWyjscia + nazwaWarunkuWewn() +" = 0;\n";
        wynik += warunek.toJava(nazwaWyjscia + nazwaWarunkuZewn());
        wynik += "while (" + nazwaWyjscia + nazwaWarunkuZewn() + " != 0)\n{\n";
        wynik += "double " + nazwaWyjscia + nazwaBloku() + " = 0;\n";
        wynik += blok.toJava(nazwaWyjscia + nazwaBloku()) + "\n";
        wynik += warunek.toJava(nazwaWyjscia + nazwaWarunkuWewn()) + "\n";
        wynik += nazwaWyjscia + nazwaWarunkuZewn() + " = " + nazwaWyjscia + nazwaWarunkuWewn() + ";\n";
        wynik += "}\n " + nazwaWyjscia + " = 0;";
        return wynik;
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double war = warunek.wykonaj();
        if (war != 0 && war != 1)
            throw new Robson.BladWykonania();
        while (war == 1) {
            blok.wykonaj();
            war = warunek.wykonaj();
            if (war != 0 && war != 1)
                throw new Robson.BladWykonania();
        }
        
        return 0;
    }
}
