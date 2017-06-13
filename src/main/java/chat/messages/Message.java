package chat.messages;

public class Message {

    protected Long id;
    protected Long senderId;
    protected MessageType messageType;
    protected String objectTypeString = this.getClass().toString();

    public Message() {
    };

    public Message (Long id, Long senderId){
        this.id = id;
        this.senderId = senderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;
        if(obj == null) return false;


        if (getClass().equals(obj.getClass())){
            Message message = (Message) obj;
            return (id.equals(message.id) && (senderId.equals(message.senderId)) &&
                    messageType.equals(message.messageType) && objectTypeString.equals(message.objectTypeString));
        } else {
            return false;
        }
    }
}

