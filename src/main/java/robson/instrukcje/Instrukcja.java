package robson.instrukcje;

import com.google.gson.JsonObject;
import robson.Robson;
import robson.instrukcje.operacje_arytmetyczne.*;
import robson.instrukcje.operacje_logiczne.*;
import robson.instrukcje.porownania.*;
import robson.instrukcje.wartosci_logiczne.*;

public interface Instrukcja {

    String typ();

    // daje instrukcji (ale nie podinstrukcjom) instancję klasy Robson, w której są trzymane jej zmienne
    void robson(Robson robson);

    // tworzy klasy dla wszystkich podinstrukcji.
    // wyłuskuje z danego JsonObject dane do siebie i wszystkich swoich podinstrukcji. Potem przypisuje
    // wszystkim swoim podinstrukcjom swoją instancję klasy Robson.
    void stworzOdJsona(JsonObject json);

    // przed poniższymi metodami musi być wywołana metoda robson, a potem stworzOdJsona

    // generuje kod javowy, który do istniejącej już zmiennej o podanej nazwie zapisuje
    // wynik instrukcji.
    // Wypisany kod działa w ten sposób, że każda Instrukcja tworzy pomocnicze zmienne
    // na wyniki swoich podinstrukcji (argumentów), prosi toJava o przypisanie tym zmiennym tych wyników
    // i na koniec przypisuje zmiennej o podanej nazwie swój wynik.
    String toJava(String nazwaWyjscia);

    double wykonaj() throws Robson.BladWykonania;

    // dodaje wszystkie nazwy zmiennych występujące w programie do zbioru w przypisanej klasie Robson
    void dodajZmienne();

    // zawartość funkcji wygenerowana skryptem genNowaInstrukcja.sh
    // tworzy instancję konkretnej podklasy klasy Instrukcja na podstawie nazwy
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
        return null;
    }

}
