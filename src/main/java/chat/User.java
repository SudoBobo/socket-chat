package chat;

import java.util.UUID;

public class User {

    private String id = createID();
    private String name;
    private String pass;

    private static long idCounter = 0;

    private static synchronized String createID()
    {
        return UUID.randomUUID().toString();
    }

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "chat.User{" +
                "name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
