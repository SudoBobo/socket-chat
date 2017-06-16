package chat;

public class User {

    private int id;
    private String name;
    private String pass;

    public User(String name, String pass, int id) {
        this.name = name;
        this.pass = pass;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    @Override
    public String toString() {
        return "chat.User{" +
                "name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }

}
