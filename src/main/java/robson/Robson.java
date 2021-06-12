package robson;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import robson.instrukcje.Instrukcja;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Robson {
    private static class NieprawidlowyProgram extends Exception {}
    public static class BladWykonania extends Exception {}
    private Instrukcja program;
    
    // do wykonywania programu
    private Map<String, Double> zmienne;
    public void ustawianieZmiennej(String nazwa, double wartosc) {
        zmienne.put(nazwa, wartosc);
    }
    public double wartoscZmiennej(String nazwa) {
        return zmienne.getOrDefault(nazwa,0.);
    }
    
    // do deklarowania zmiennych w toJava
    private Set<String> nazwyZmiennych;
    public void dodanieZmiennej(String nazwa) {
        nazwyZmiennych.add(nazwa);
    }
    
    // jeśli konstruktor Robsona zostanie wywołany z argumentem gadatliwy=true,
    // to nazwy zmiennych będą długie i opisowe
    private boolean gadatliwy;
    public boolean gadatliwy() {
        return gadatliwy;
    }
    
    public void fromJson(String filename) throws NieprawidlowyProgram {
        Gson gson = new Gson();
        try {
            String json = Files.readString(Path.of(filename));
            JsonObject j = gson.fromJson(json, JsonObject.class);
            program = Instrukcja.nowaInstrukcja(j.get("typ").getAsString());
            assert program != null;
            program.robson(this);
            program.fromJson(j);
        } catch (IOException e) {
            e.printStackTrace();
            throw new NieprawidlowyProgram();
        } 
    }

    public void toJson(String filename) {
        assert program != null;
        GsonBuilder g = new GsonBuilder();
        // żeby wypisywało też typ, który jest klasowy
        g.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
        Gson gson = g.setPrettyPrinting().create();
        String json = gson.toJson(program);
        if (Objects.isNull(filename))
            System.out.println(json);
        else try {
            Files.writeString(Path.of(filename), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // chcemy zadbać o to, żeby pomocnicze zmienne w programie toJava
    // nie miały takich samych nazw jak zmienne z robsona. W tym celu sprawdzamy,
    // ile maksymalnie znaków $ mają zmienne z robsona i nasze zmienne pomocnicze będziemy
    // zaczynali od tej liczby plus jeden znaków $
    private String zmiennaPomocnicza() {
        String wynik = "$";
        for (String x : nazwyZmiennych) {
            int temp = 1 + (int) x.chars().filter(ch -> ch == '$').count();
            if (temp > wynik.length())
                wynik = "$".repeat(temp);
        }
        if (gadatliwy)
            return wynik + "zmiennaPomocnicza";
        return wynik;
    }

    // najpierw deklarujemy wszystkie zmienne robsona, potem za pomocą toJava wypisujemy kod programu,
    // a potem wypisujemy główną zmienną pomocniczą, która trzyma wynik programu. Cały ten kod formatujemy
    // za pomocą google-java-format i wypisujemy do pliku.
    public void toJava(String filename) {
        assert program != null;
        StringBuilder wynik = new StringBuilder("public class Main\n{\npublic static void main(String[] args)\n{\n");
        program.dodajZmienne();
        for (String x : nazwyZmiennych) {
            wynik.append("double ").append(x).append(" = 0;\n");
        }
        wynik.append("double ").append(zmiennaPomocnicza()).append(" = 0;\n");
        wynik.append(program.toJava(zmiennaPomocnicza())).append("\n");
        wynik.append("System.out.println(").append(zmiennaPomocnicza()).append(");\n}\n}");

        String formatowany = wynik.toString();
        try {
            formatowany = new Formatter().formatSource(formatowany);
        } catch (FormatterException e) {
            e.printStackTrace();
        }

        if (Objects.isNull(filename))
            System.out.println(formatowany);
        else {
            try {
                Files.writeString(Path.of(filename), formatowany);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Hashmapę ze zmiennymi tworzymy tylko na potrzeby wykonywania programu,
    // po wykonaniu od razu ją zapominamy, żeby zapisane w niej w trakcie wykonania wielkości
    // nie wpłynęły na późniejsze wykonania.
    public double wykonaj() throws BladWykonania {
        this.zmienne = new HashMap<>();
        double wynik = program.wykonaj();
        this.zmienne = null;
        return wynik;
    }
    
    public Robson() {
        this.nazwyZmiennych = new HashSet<>();
        this.gadatliwy = false;
    }
    public Robson(boolean gadatliwy) {
        this.nazwyZmiennych = new HashSet<>();
        this.gadatliwy = gadatliwy;
    }
}
