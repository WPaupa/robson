package testy;

import junit.framework.*;
import robson.Robson;

public class UnitTest extends TestCase {
    public void testujRobsona() {
        try {
            Robson robson = new Robson();
            robson.fromJson("fibonacci.json");
            robson.fromJson("przyklad1.json");
        } catch (Exception ignored) {
            assert(false);
        }
    }
}
