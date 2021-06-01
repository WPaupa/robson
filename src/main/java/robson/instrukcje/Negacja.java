package robson.instrukcje;

import com.google.gson.JsonObject;
import robson.Robson;

public class Negacja implements Instrukcja {
    
    private Instrukcja argument;
    
    @Override
    public String typ() {
        return "\"Not\"";
    }

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").toString().equals(this.typ()));

        JsonObject arg = json.get("argument").getAsJsonObject();
        argument = Instrukcja.nowaInstrukcja(arg.get("typ").toString());
        assert argument != null;
        argument.fromJson(arg);
    }

    @Override
    public double wykonaj() throws Robson.BladWykonania {
        double arg = argument.wykonaj();
        if (arg != 0 && arg != 1)
            throw new Robson.BladWykonania();
        if (arg == 1)
            return 0;
        return 1;
    }
}
