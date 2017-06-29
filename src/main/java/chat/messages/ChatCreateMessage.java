package chat.messages;

import java.util.List;

public class ChatCreateMessage extends Message {


    private List<Long> usersId;
    private String chatTitle;

    public ChatCreateMessage(){}
    public ChatCreateMessage(List<Long> usersId, String chatTitle){
        messageType = MessageType.MSG_CHAT_CREATE;
        this.usersId = usersId;
        this.chatTitle = chatTitle;
    }

    public List<Long> getUsersId() {
        return usersId;
    }

    public String getChatTitle() {
        return chatTitle;
    }
}
