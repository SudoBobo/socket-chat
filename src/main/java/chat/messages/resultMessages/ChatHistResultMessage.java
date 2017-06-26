package chat.messages.resultMessages;

import chat.messages.Message;
import chat.messages.TextMessage;

import java.util.ArrayList;

public class ChatHistResultMessage extends Message {
    private ArrayList<TextMessage> messages;
    private Integer chatId;

    public ChatHistResultMessage() {
    }

    public ChatHistResultMessage(ArrayList<TextMessage> messages, Integer chatId) {
        this.messages = messages;
        this.chatId = chatId;
    }

    public ArrayList<TextMessage> getMessages() {
        return messages;
    }

    public Integer getChatId() {
        return chatId;
    }
}
