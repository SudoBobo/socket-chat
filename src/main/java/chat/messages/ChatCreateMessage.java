package chat.messages;

import java.util.List;

public class ChatCreateMessage extends Message {


    private List<Long> usersId;

    public ChatCreateMessage(){}
    public ChatCreateMessage(List<Long> usersId){
        messageType = MessageType.MSG_CHAT_CREATE;
        this.usersId = usersId;
    }

    public List<Long> getUsersId() {
        return usersId;
    }
}
