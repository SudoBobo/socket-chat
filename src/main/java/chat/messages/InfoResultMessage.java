package chat.messages;

public class InfoResultMessage extends Message {
    private String name;
    private Long userId;

    public InfoResultMessage(){

    }
    public InfoResultMessage(String name, long userId) {
        this.name = name;
        this.userId = userId;
        messageType = MessageType.MSG_INFO_RESULT;
    }

    public String getName() {
        return name;
    }

    public long getUserId() {
        return userId;
    }

    public boolean equals(InfoResultMessage message) {
        return (super.equals(message) && userId.equals(message.userId) && name.equals(message.name));
    }
}
