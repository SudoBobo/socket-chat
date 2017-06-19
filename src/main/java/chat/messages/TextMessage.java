package chat.messages;

public class TextMessage extends Message {
    private String text;
    private Integer chatId;

    public TextMessage(){}

    public TextMessage(Integer chatId, String text){
        this.chatId = chatId;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Integer getChatId() {
        return chatId;
    }
}
