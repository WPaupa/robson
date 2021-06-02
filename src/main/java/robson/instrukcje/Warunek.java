package robson.instrukcje;

import com.google.gson.JsonObject;
import robson.Robson;

import java.util.Objects;

public class Warunek implements Instrukcja {
    
    private Instrukcja warunek;
    private Instrukcja blok_prawda;
    private Instrukcja blok_falsz;
    private Robson robson;

    @Override
    public void robson(Robson robson) {
        this.robson = robson;
    }
    
    @Override
    public String typ() {
        return "Warunek";
    }

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").getAsString().equals(this.typ()));

        JsonObject war = json.get("wyrazenie").getAsJsonObject();
        warunek = Instrukcja.nowaInstrukcja(war.get("typ").getAsString());
        assert warunek != null;
        warunek.robson(robson);
        warunek.fromJson(war);

        JsonObject prawda = json.get("blok_prawda").getAsJsonObject();
        blok_prawda = Instrukcja.nowaInstrukcja(prawda.get("typ").getAsString());
        assert blok_prawda != null;
        blok_prawda.robson(robson);
        blok_prawda.fromJson(prawda);
        
        if (json.has("blok_falsz")) {
            JsonObject falsz = json.get("blok_falsz").getAsJsonObject();
            blok_falsz = Instrukcja.nowaInstrukcja(falsz.get("typ").getAsString());
            assert blok_falsz != null;
            blok_falsz.robson(robson);
            blok_falsz.fromJson(falsz);
        } 
    }

    @Override
    public String toJava(String nazwaWyjscia) {
        String wynik = "{\n";
        wynik += "double " + nazwaWyjscia + "w = 0, " + nazwaWyjscia + "p = 0, " + nazwaWyjscia + "f = 0;\n";
        wynik += warunek.toJava(nazwaWyjscia + "w");
        wynik += "if (" + nazwaWyjscia + "w != 0)\n";
        wynik += "{\n" + blok_prawda.toJava(nazwaWyjscia + "p") + "\n";
        wynik += nazwaWyjscia + " = " + nazwaWyjscia + "p;\n}\n";
        if (!Objects.isNull(blok_falsz)) {
            wynik += "else{\n" + blok_falsz.toJava(nazwaWyjscia + "f") + "\n";
            wynik += nazwaWyjscia + " = " + nazwaWyjscia + "f;\n}\n";
        } else
            wynik += "else\n" + nazwaWyjscia + " = 0;\n";
        return wynik + "}";
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double war = warunek.wykonaj();
        if (war != 0 && war != 1)
            throw new Robson.BladWykonania();
        if (war == 1)
            return blok_prawda.wykonaj();
        if (!Objects.isNull(blok_falsz))
            return blok_falsz.wykonaj();
        return 0;
    }
}
