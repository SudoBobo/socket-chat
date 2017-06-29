package chat.messages;

import java.util.Date;

public class TextMessage extends Message {
    private String text;
    private Date time;
    private String chatTitle;

    public TextMessage(){}

    public TextMessage(String chatTitle, String text){
        this.messageType = MessageType.MSG_TEXT;
        time = new Date();
        this.chatTitle = chatTitle;
        this.text = text;
    }

    public TextMessage(String chatTitle, String text, Date date){
        this.messageType = MessageType.MSG_TEXT;
        this.time = date;
        this.chatTitle = chatTitle;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getChatTitle() {
        return chatTitle;
    }

    public Date getTime() {
        return time;
    }

}
