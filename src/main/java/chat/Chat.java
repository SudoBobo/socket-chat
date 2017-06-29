package chat;

import java.util.List;

public class Chat {
    private Integer id;
    private String title;
    private List<Long> usersId;

    public Chat() {
    }

    public Chat(String title, Integer id, List<Long> usersId) {
        this.id = id;
        this.usersId = usersId;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public List<Long> getUsersId() {
        return usersId;
    }

    public String getTitle() {
        return title;
    }
}
