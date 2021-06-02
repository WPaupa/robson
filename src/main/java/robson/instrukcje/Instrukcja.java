package robson.instrukcje;

import com.google.gson.JsonObject;
import robson.Robson;
import robson.instrukcje.operacje_arytmetyczne.*;
import robson.instrukcje.operacje_logiczne.*;
import robson.instrukcje.porownania.*;
import robson.instrukcje.wartosci_logiczne.*;

public interface Instrukcja {
    String typ();

    void fromJson(JsonObject json);
    double wykonaj() throws Robson.BladWykonania;
    void robson(Robson robson);

    // funkcja wygenerowana automatycznie
    static Instrukcja nowaInstrukcja(String nazwa) {
        if (nazwa.equals(new Dzielenie().typ()))
            return new Dzielenie();
        if (nazwa.equals(new Minus().typ()))
            return new Minus();
        if (nazwa.equals(new Plus().typ()))
            return new Plus();
        if (nazwa.equals(new Razy().typ()))
            return new Razy();
        if (nazwa.equals(new Lub().typ()))
            return new Lub();
        if (nazwa.equals(new Oraz().typ()))
            return new Oraz();
        if (nazwa.equals(new Mniejszy().typ()))
            return new Mniejszy();
        if (nazwa.equals(new MniejszyLubRowny().typ()))
            return new MniejszyLubRowny();
        if (nazwa.equals(new Rowny().typ()))
            return new Rowny();
        if (nazwa.equals(new Wiekszy().typ()))
            return new Wiekszy();
        if (nazwa.equals(new WiekszyLubRowny().typ()))
            return new WiekszyLubRowny();
        if (nazwa.equals(new Falsz().typ()))
            return new Falsz();
        if (nazwa.equals(new Prawda().typ()))
            return new Prawda();
        if (nazwa.equals(new Blok().typ()))
            return new Blok();
        if (nazwa.equals(new Liczba().typ()))
            return new Liczba();
        if (nazwa.equals(new Negacja().typ()))
            return new Negacja();
        if (nazwa.equals(new Petla().typ()))
            return new Petla();
        if (nazwa.equals(new Przypisanie().typ()))
            return new Przypisanie();
        if (nazwa.equals(new Warunek().typ()))
            return new Warunek();
        if (nazwa.equals(new Zmienna().typ()))
            return new Zmienna();
        System.out.println(nazwa);
        return null;
    }
    
}
