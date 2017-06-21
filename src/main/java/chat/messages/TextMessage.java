package chat.messages;

import java.util.Date;

public class TextMessage extends Message {
    private String text;
    private Date time;
    private Integer chatId;

    public TextMessage(){}

    public TextMessage(Integer chatId, String text){
        this.messageType = MessageType.MSG_TEXT;
        time = new Date();
        this.chatId = chatId;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Integer getChatId() {
        return chatId;
    }

    public Date getTime() {
        return time;
    }

}
