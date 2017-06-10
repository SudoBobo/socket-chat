import java.io.Serializable;

public class User implements Serializable {
   public String name;
   public String pass;

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
