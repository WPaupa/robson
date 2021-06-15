import org.junit.jupiter.api.Test;
import robson.Robson;
import robson.instrukcje.Instrukcja;

import java.util.Objects;

// klasa i metody pakietowe, bo wymaga tego JUnit
class TestyJednostkowe {
    @Test
    void testujPrzyklad1() {
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

    @Test
    void testujFibonacci() {
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

    @Test
    void testujForum() {
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

    @Test
    void testujEuklides() {
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

    @Test
    void testujCzyPierwsza() {
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

    @Test
    void testujMyTest() {
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

    @Test
    void testujSilnia() {
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

    @Test
    void testujBinomial() {
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

    @Test
    void testujPochodnaArytmetyczna() {
        try {
            Robson robson = new Robson();
            robson.fromJson("pochodnaArytmetyczna.json");
            robson.toJson(null);
            System.out.println(robson.wykonaj());
            robson.toJava(null);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    void testujInstrukcje() {
        try {
            System.out.println(Objects.requireNonNull(Instrukcja.nowaInstrukcja("Blok")).typ());
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }
}
