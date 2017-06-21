package chat.messages.resultMessages;

import chat.messages.Message;
import chat.messages.MessageType;

public class ErrorMessage extends Message {
    private String errorMessage;

    public ErrorMessage(){
    }
    public ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        this.messageType = MessageType.MSG_ERROR;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
