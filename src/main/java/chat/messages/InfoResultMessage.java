package chat.messages;

public class InfoResultMessage extends Message {
    private String name;
    private long userId;

    public InfoResultMessage(String name, long userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public long getUserId() {
        return userId;
    }
}
