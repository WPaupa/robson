package robson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import robson.instrukcje.Instrukcja;

import java.nio.file.Files;
import java.nio.file.Path;


public class Robson {
    private static class NieprawidlowyProgram extends Exception {}
    private static class BladWykonania extends Exception {}
    private Instrukcja program;
    
    public void fromJson(String filename) throws NieprawidlowyProgram {
        Gson gson = new Gson();
        try {
            String json = Files.readString(Path.of(filename));
            JsonObject j = gson.fromJson(json, JsonObject.class);
            program = Instrukcja.nowaInstrukcja(j.get("typ").toString());
            assert program != null;
            program.fromJson(j);
        } catch (Exception e) {
            throw new NieprawidlowyProgram();
        } 
    }

    public void toJson(String filename) {
        
    }

    public void toJava(String filename) {
        
    }

    public double wykonaj() throws BladWykonania {
        throw new BladWykonania();
    }
}
