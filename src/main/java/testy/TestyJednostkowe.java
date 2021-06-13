package testy;

import junit.framework.*;
import robson.Robson;
import robson.instrukcje.Instrukcja;

import java.util.Objects;

public class TestyJednostkowe extends TestCase {
    public void testujPrzyklad() {
        try {
            Robson robson = new Robson(true);
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
            robson.toJson(null);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
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
    
    public void testujEuklides() {
        try {
            Robson robson = new Robson();
            robson.fromJson("euklides.json");
            robson.toJson(null);
            System.out.println(robson.wykonaj());
            robson.toJava(null);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    public void testujCzyPierwsza() {
        try {
            Robson robson = new Robson();
            robson.fromJson("czyPierwsza.json");
            robson.toJson(null);
            System.out.println(robson.wykonaj());
            robson.toJava(null);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }
    
    public void testujMyTest() {
        try {
            Robson robson = new Robson();
            robson.fromJson("myTest.json");
            robson.toJson(null);
            System.out.println(robson.wykonaj());
            robson.toJava(null);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    public void testujSilnia() {
        try {
            Robson robson = new Robson();
            robson.fromJson("silnia.json");
            robson.toJson(null);
            System.out.println(robson.wykonaj());
            robson.toJava(null);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    public void testujBinomial() {
        try {
            Robson robson = new Robson();
            robson.fromJson("binomial.json");
            robson.toJson(null);
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
}
