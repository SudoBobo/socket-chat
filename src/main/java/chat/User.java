package chat;

public class User {

    private Integer id;
    private String name;
    private String pass;

    public User(String name, String pass, Integer id) {
        this.name = name;
        this.pass = pass;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;


        if (getClass().equals(obj.getClass())) {
            User user = (User) obj;
            return (
                    name.equals(user.name) && pass.equals(user.pass) && id.equals(user.id));
        } else {
            return false;
        }
    }
}
