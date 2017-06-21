package chat.messages;

public class ChatHistMessage extends Message {
    private Integer chatId;

    public ChatHistMessage(){}

    public ChatHistMessage(Integer chatId){
        this.messageType = MessageType.MSG_CHAT_HIST;
        this.chatId = chatId;
    }

    public Integer getChatId() {
        return chatId;
    }
}
