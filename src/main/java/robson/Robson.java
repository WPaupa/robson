package robson;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import robson.instrukcje.Instrukcja;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Objects;
import java.util.Set;


public class Robson {
    private static class NieprawidlowyProgram extends Exception {}
    public static class BladWykonania extends Exception {}
    private Instrukcja program;
    private Hashtable<String, Double> zmienne;
    private Set<String> nazwyZmiennych;
    
    private boolean verbose;
    public boolean verbose() {
        return verbose;
    }
    
    public void dodanieZmiennej(String nazwa) {
        nazwyZmiennych.add(nazwa);
    }
    
    public void ustawianieZmiennej(String nazwa, double wartosc) {
        zmienne.put(nazwa, wartosc);
    }
    
    public double wartoscZmiennej(String nazwa) {
        if (!zmienne.containsKey(nazwa))
            zmienne.put(nazwa, 0.);
        return zmienne.get(nazwa);
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
            zmienne = new Hashtable<>();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NieprawidlowyProgram();
        } 
    }

    public void toJson(String filename) {
        GsonBuilder g = new GsonBuilder();
        // żeby wypisywało też typ, który jest klasowy
        g.excludeFieldsWithModifiers(java.lang.reflect.Modifier.TRANSIENT);
        Gson gson = g.setPrettyPrinting().create();
        String json = gson.toJson(program);
        if (Objects.isNull(filename))
            System.out.println(json);
        else try {
            Files.writeString(Path.of(filename), json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toJava(String filename) {
        StringBuilder wynik = new StringBuilder("public class Main\n{\npublic static void main(String[] args)\n{\n");
        program.dodajZmienne();
        for (String x : nazwyZmiennych) {
            wynik.append("double ").append(x).append(" = 0;\n");
        }
        wynik.append("double $ = 0;\n");
        wynik.append(program.toJava("$")).append("\n");
        wynik.append("System.out.println($);\n}\n}");

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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public double wykonaj() throws BladWykonania {
        this.zmienne = new Hashtable<>();
        double wynik = program.wykonaj();
        this.zmienne = null;
        return wynik;
    }
    
    public Robson() {
        this.nazwyZmiennych = new HashSet<>();
        this.verbose = false;
    }
    public Robson(boolean verbose) {
        this.nazwyZmiennych = new HashSet<>();
        this.verbose = verbose;
    }
}
