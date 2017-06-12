package chat.messages;

import com.google.gson.*;

import java.lang.reflect.Type;

public class MessageDeserializer implements JsonDeserializer<Message> {

    static private Gson gson = new Gson();

    public Message deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {

//        final JsonObject jsonObject = json.getAsJsonObject();
//        JsonElement typeJson = jsonObject.get("objectTypeString");
//
//        Type objectTypeString = gson.fromJson(typeJson, Type.class);
//        return gson.fromJson(jsonObject, objectTypeString);

        Message message = new LoginMessage("test","passTest");
        return message;
    }
}