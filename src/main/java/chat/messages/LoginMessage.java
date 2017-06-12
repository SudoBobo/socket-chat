package chat.messages;

/**
 *
 */
public class LoginMessage extends Message {
    private String name;
    private String pass;


    public LoginMessage () {};

    public LoginMessage(String name, String pass) {
        objectTypeString = LoginMessage.class.toString();
        messageType = MessageType.MSG_LOGIN;
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "chat.messages.LoginMessage{" +
                "name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
