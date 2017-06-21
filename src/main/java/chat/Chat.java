package chat;

import java.util.ArrayList;

public class Chat {
    private Integer id;
    private ArrayList<User> users;

    public Chat() {
    }

    public Chat(Integer id, ArrayList<User> users) {
        this.id = id;
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public ArrayList<User> getUsers() {
        return users;
    }


}
