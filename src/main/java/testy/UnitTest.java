package testy;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import junit.framework.*;
import robson.Robson;
import robson.instrukcje.Instrukcja;

import java.util.Objects;

public class UnitTest extends TestCase {
    public void testujPrzyklad() {
        try {
            Robson robson = new Robson();
            robson.fromJson("przyklad1.json");
            robson.toJson(null);
            System.out.println(robson.wykonaj());
            robson.toJava(null);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    public void testujFibonacci() {
        try {
            Robson robson = new Robson();
            robson.fromJson("fibonacci.json");
            System.out.println(robson.wykonaj());
            robson.toJava(null);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    public void testujInstrukcje() {
        try {
            System.out.println(Objects.requireNonNull(Instrukcja.nowaInstrukcja("Blok")).typ());
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }
    
    public void testujJson() {
        Gson gson = new Gson();
        JsonObject j = gson.fromJson("{\"json\":\"son\"}", JsonObject.class);
        System.out.println(j.get("json").getAsString());
    }
    
    public void testujForum() {
        try {
            Robson robson = new Robson();
            robson.fromJson("forum.json");
            robson.toJson(null);
            System.out.println(robson.wykonaj());
            robson.toJava(null);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }
}
