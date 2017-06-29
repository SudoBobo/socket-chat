package chat.messages;

public class ChatHistMessage extends Message {
    private String chatTitle;

    public ChatHistMessage(){}

    public ChatHistMessage(String chatTitle){
        this.messageType = MessageType.MSG_CHAT_HIST;
        this.chatTitle = chatTitle;
    }

    public String getChatTitle() {
        return chatTitle;
    }
}
