package chat.messages;

public class ChatListMessage extends Message {
    private Long userId;

    public ChatListMessage(){}

    public ChatListMessage(Long userId){
        this.messageType = MessageType.MSG_CHAT_LIST;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
