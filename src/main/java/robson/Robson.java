package robson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import robson.instrukcje.Instrukcja;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Hashtable;


public class Robson {
    private static class NieprawidlowyProgram extends Exception {}
    public static class BladWykonania extends Exception {}
    private Instrukcja program;
    private Hashtable<String, Double> zmienne;
    private String json;
    
    public void ustawianieZmiennej(String nazwa, double wartosc) {
        zmienne.put(nazwa, wartosc);
    }
    
    public double wartoscZmiennej(String nazwa) {
        return zmienne.get(nazwa);
    }
    
    public void fromJson(String filename) throws NieprawidlowyProgram {
        Gson gson = new Gson();
        try {
            json = Files.readString(Path.of(filename));
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
        try {
            Files.writeString(Path.of(filename), json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toJava(String filename) {
        try {
            program.wykonaj();
            for (String k : zmienne.keySet()) {
                System.out.println("double " + k + " = 0;");
            }
            zmienne = new Hashtable<>();
        } catch (Exception ignored) {
        }
        System.out.println("double a = 0;");
        System.out.println(program.toJava("a"));
        System.out.println("System.out.println(a);");
    }

    public double wykonaj() throws BladWykonania {
        double wynik = program.wykonaj();
        this.zmienne = new Hashtable<>();
        return wynik;
    }
}
