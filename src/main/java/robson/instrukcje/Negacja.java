package robson.instrukcje;

import com.google.gson.JsonObject;

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
}
