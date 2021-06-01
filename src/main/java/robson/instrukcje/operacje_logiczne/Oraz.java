package robson.instrukcje.operacje_logiczne;

import com.google.gson.JsonObject;
import robson.Robson;

public class Oraz extends OperacjaLogiczna {
    @Override
    public String typ() {
        return "\"And\"";
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double arg1 = argument1.wykonaj();
        double arg2 = argument2.wykonaj();
        if ((arg1 != 0 && arg1 != 1) || (arg2 != 0 && arg2 != 1))
            throw new Robson.BladWykonania();

        if (arg1 == 0 || arg2 == 0)
            return 0;
        return 1;
    }
}