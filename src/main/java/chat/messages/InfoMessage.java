package chat.messages;

public class InfoMessage extends Message {
    private Long userId;


    public InfoMessage(){};
    public InfoMessage(Long userId){
        this.userId = userId;
        messageType = MessageType.MSG_INFO;
        objectTypeString = InfoMessage.class.toString();
    }

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public Long getUserId(){
        return userId;
    }
}
