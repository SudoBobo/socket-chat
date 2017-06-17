package chat.messages;

public class InfoMessage extends Message {
    private Long userId;

    public InfoMessage(){};
    public InfoMessage(Long userId){
        this.userId = userId;
        messageType = MessageType.MSG_INFO;
    }
    public InfoMessage(Long userId, Long senderId, Long id){
        super(senderId);

        messageType = MessageType.MSG_INFO;
        this.userId = userId;
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public Long getUserId(){
        return userId;
    }

    public boolean equals(InfoMessage message) {
        return (super.equals(message) && userId.equals(message.userId));
    }
}
