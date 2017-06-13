package chat.messages;

/**
 *
 */
public class LoginMessage extends Message {
    private String name;
    private String pass;

    public LoginMessage() {
    }

    ;

    public LoginMessage(String name, String pass) {
        messageType = MessageType.MSG_LOGIN;
        this.name = name;
        this.pass = pass;
    }

    public LoginMessage(String name, String pass, Long id, Long senderId) {
        super(id, senderId);

        this.name = name;
        this.pass = pass;

        this.messageType = MessageType.MSG_LOGIN;
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


    public boolean equals(LoginMessage loginMessage) {
        return (super.equals(loginMessage) && name.equals(loginMessage.name) && pass.equals(loginMessage.pass));
    }
}
