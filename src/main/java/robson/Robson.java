package robson;

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import robson.instrukcje.Instrukcja;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.Objects;


public class Robson {
    private static class NieprawidlowyProgram extends Exception {}
    public static class BladWykonania extends Exception {}
    private Instrukcja program;
    private Hashtable<String, Double> zmienne;
    
    private boolean verbose;
    public boolean verbose() {
        return verbose;
    }
    
    public void ustawianieZmiennej(String nazwa, double wartosc) {
        zmienne.put(nazwa, wartosc);
    }
    
    public double wartoscZmiennej(String nazwa) {
        if (!zmienne.contains(nazwa))
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
        String json = new Gson().toJson(program);
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
        try {
            program.wykonaj();
            for (String k : zmienne.keySet()) {
                wynik.append("double ").append(k).append(" = 0;\n");
            }
            zmienne = new Hashtable<>();
        } catch (Exception ignored) {
        }
        wynik.append("double a = 0;\n");
        wynik.append(program.toJava("a")).append("\n");
        wynik.append("System.out.println(a);\n}\n}");

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
        double wynik = program.wykonaj();
        this.zmienne = new Hashtable<>();
        return wynik;
    }
    
    public Robson() {
        this.verbose = false;
    }
    public Robson(boolean verbose) {
        this.verbose = verbose;
    }
}
