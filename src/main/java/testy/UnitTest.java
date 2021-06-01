package testy;

import junit.framework.*;
import robson.Robson;
import robson.instrukcje.Instrukcja;

import java.util.Objects;

public class UnitTest extends TestCase {
    public void testujPrzyklad() {
        try {
            Robson robson = new Robson();
            robson.fromJson("przyklad1.json");
            System.out.println(robson.wykonaj());
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
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }
    
    public void testujInstrukcje() {
        try {
            System.out.println(Objects.requireNonNull(Instrukcja.nowaInstrukcja("\"Blok\"")).typ());
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }
}
