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
    private static Hashtable<String, Double> zmienne;
    
    public static void ustawianieZmiennej(String nazwa, double wartosc) {
        zmienne.put(nazwa, wartosc);
    }
    
    public static double wartoscZmiennej(String nazwa) {
        return zmienne.get(nazwa);
    }
    
    public void fromJson(String filename) throws NieprawidlowyProgram {
        Gson gson = new Gson();
        try {
            String json = Files.readString(Path.of(filename));
            JsonObject j = gson.fromJson(json, JsonObject.class);
            program = Instrukcja.nowaInstrukcja(j.get("typ").toString());
            assert program != null;
            program.fromJson(j);
            zmienne = new Hashtable<>();
        } catch (Exception e) {
            e.printStackTrace();
            throw new NieprawidlowyProgram();
        } 
    }

    public void toJson(String filename) {
        
    }

    public void toJava(String filename) {
        
    }

    public double wykonaj() throws BladWykonania {
        return program.wykonaj();
    }
}
