package robson.instrukcje.operacje_logiczne;

import com.google.gson.JsonObject;
import robson.Robson;
import robson.instrukcje.Instrukcja;

public abstract class OperacjaLogiczna implements Instrukcja {
    // pakietowe, żeby były widoczne tylko w innych operacjach
    Instrukcja argument1, argument2;
    private Robson robson;

    @Override
    public void robson(Robson robson) {
        this.robson = robson;
    }

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").toString().equals(this.typ()));

        JsonObject arg1 = json.get("argument1").getAsJsonObject();
        argument1 = Instrukcja.nowaInstrukcja(arg1.get("typ").toString());
        assert argument1 != null;
        argument1.robson(robson);
        argument1.fromJson(arg1);

        JsonObject arg2 = json.get("argument2").getAsJsonObject();
        argument2 = Instrukcja.nowaInstrukcja(arg2.get("typ").toString());
        assert argument2 != null;
        argument2.robson(robson);
        argument2.fromJson(arg2);
    }
}
