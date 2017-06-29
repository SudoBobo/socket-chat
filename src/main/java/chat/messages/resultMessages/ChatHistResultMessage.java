package chat.messages.resultMessages;

import chat.messages.Message;
import chat.messages.MessageType;
import chat.messages.TextMessage;

import java.util.ArrayList;

public class ChatHistResultMessage extends Message {
    private ArrayList<TextMessage> messages;
    private String chatTitle;

    public ChatHistResultMessage() {
    }

    public ChatHistResultMessage(ArrayList<TextMessage> messages, String chatTitle) {
        messageType = MessageType.MSG_CHAT_HIST_RESULT;
        this.messages = messages;
        this.chatTitle = chatTitle;
    }

    public ArrayList<TextMessage> getMessages() {
        return messages;
    }

    public String getChatTitle() {
        return chatTitle;
    }
}
