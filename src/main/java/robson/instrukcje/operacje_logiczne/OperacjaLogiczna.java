package robson.instrukcje.operacje_logiczne;

import com.google.gson.JsonObject;
import robson.instrukcje.Instrukcja;

public abstract class OperacjaLogiczna implements Instrukcja {
    protected Instrukcja argument1, argument2;

    @Override
    public void fromJson(JsonObject json) {
        assert(json.get("typ").toString().equals(this.typ()));

        JsonObject arg1 = json.get("argument1").getAsJsonObject();
        argument1 = Instrukcja.nowaInstrukcja(arg1.get("typ").toString());
        assert argument1 != null;
        argument1.fromJson(arg1);

        JsonObject arg2 = json.get("argument2").getAsJsonObject();
        argument2 = Instrukcja.nowaInstrukcja(arg2.get("typ").toString());
        assert argument2 != null;
        argument2.fromJson(arg2);
    }
}
