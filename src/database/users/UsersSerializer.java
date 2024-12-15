package database.users;
import com.google.gson.*;
import java.lang.reflect.Type;

public class UsersSerializer implements JsonDeserializer<Users>, JsonSerializer<Users> {
    private static final String CLASS_META_KEY = "type";

    @Override
    public JsonElement serialize(Users src, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();
        jsonObject.addProperty(CLASS_META_KEY, src.getClass().getName());
        return jsonObject;
    }

    @Override
    public Users deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String classname = jsonObject.get(CLASS_META_KEY).getAsString();
        try {
            Class<?> c = Class.forName(classname);
            return jsonDeserializationContext.deserialize(jsonObject, c);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
