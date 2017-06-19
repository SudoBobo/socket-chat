package chat.messages;

public class ChatListMessage extends Message {
    private Long userId;

    public ChatListMessage(){}

    public ChatListMessage(Long userId){
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
