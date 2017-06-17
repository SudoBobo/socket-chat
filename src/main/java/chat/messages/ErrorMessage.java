package chat.messages;

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
