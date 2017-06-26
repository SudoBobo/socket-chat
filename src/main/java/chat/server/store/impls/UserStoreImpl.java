package chat.server.store.impls;

import chat.User;
import chat.server.store.ConnectionPool;
import chat.server.store.UserStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserStoreImpl implements UserStore {
    private ConnectionPool connectionPool;

    public static final String SELECT_USER = "SELECT * FROM users WHERE login=? and password=?";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE ID=?";

    public UserStoreImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public User addUser(User user) {

        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }


    @Override
    public User getUser(String login, String pass) throws NoSuchUserException {
        User user = null;
        try {
            PreparedStatement preparedStatement = connectionPool.retrieve().prepareStatement(SELECT_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString(3), rs.getString(4), rs.getInt(2));
            } else {
                throw new NoSuchUserException(new Throwable("There is no user " + login));
            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public List<User> getUsersInChat(Integer chatId) {
        return null;
    }

    public class NoSuchUserException extends Exception {
        public NoSuchUserException(Throwable e) {
            initCause(e);
        }
    }
}
