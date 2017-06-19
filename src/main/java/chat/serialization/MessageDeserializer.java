package chat.serialization;

import chat.messages.Message;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;

public class MessageDeserializer implements JsonDeserializer<Message> {

    private HashMap<String, Class> messageTypeToClass;

    public Message deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {

        final JsonObject jsonObject = json.getAsJsonObject();
        JsonElement typeJson = jsonObject.get("objectTypeString");
        String objectTypeString = context.deserialize(typeJson, String.class);

        Class messageClass = null;
        try {
            // "class actual_class_name" -> "actual_class_name"
            objectTypeString = objectTypeString.split(" ")[1];
            messageClass = Class.forName(objectTypeString);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return context.deserialize(jsonObject, messageClass);
    }
}