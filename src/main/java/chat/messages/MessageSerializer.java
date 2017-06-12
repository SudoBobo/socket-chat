package chat.messages;

import com.google.gson.*;

import java.lang.reflect.Type;

public class MessageSerializer implements JsonSerializer<Message> {
    public JsonElement serialize(Message message, Type type, JsonSerializationContext jsonSerializationContext) {
//        return gson.toJsonTree(message, Message.class);
        JsonElement jsonElement = new JsonPrimitive("test");
        return jsonElement;
    }
}
