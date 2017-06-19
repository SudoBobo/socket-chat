package chat.messages;

public class ChatHistMessage extends Message {
    private Integer chatId;

    public ChatHistMessage(){}

    public ChatHistMessage(Integer chatId){
        this.chatId = chatId;
    }

    public Integer getChatId() {
        return chatId;
    }
}
