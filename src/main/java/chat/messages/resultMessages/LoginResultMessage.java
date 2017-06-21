package chat.messages.resultMessages;

import chat.messages.Message;
import chat.messages.MessageType;

public class LoginResultMessage extends Message {
    private Long id;
    private String name;

    public LoginResultMessage(){}

    public LoginResultMessage(Long id, String name){
        this.messageType = MessageType.MSG_LOGIN_RESULT;
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
