package chat.messages.resultMessages;

import chat.messages.Message;
import chat.messages.TextMessage;

import java.util.ArrayList;

public class ChatHistResultMessage extends Message {
    private ArrayList<TextMessage> messages;

    public ChatHistResultMessage() {
    }

    public ChatHistResultMessage(ArrayList<TextMessage> messages) {
        this.messages = messages;
    }

    public ArrayList<TextMessage> getMessages() {
        return messages;
    }
}
