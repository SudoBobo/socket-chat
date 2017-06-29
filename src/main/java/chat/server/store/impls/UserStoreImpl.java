package chat.server.store.impls;

import chat.User;
import chat.server.store.ConnectionPool;
import chat.server.store.UserStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserStoreImpl implements UserStore {
    private ConnectionPool connectionPool;

    public static final String SELECT_USER = "SELECT * FROM users WHERE login=? and password=?";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE ID=?";

    public static final String SELECT_USER_IN_CHAT = "SELECT users.id, users.login, users.password\n" +
            "FROM users\n" +
            "  INNER JOIN chats_users\n" +
            "    ON users.id = chats_users.user_id WHERE chats_users.chat_id = ?;";

    public static final String GET_CHAT_ID = "SELECT id FROM chats WHERE title=?;";

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
    public List<User> getUsersInChat(String chatTitle) {
        Integer chatId = getChatIdByTitle(chatTitle);
        ArrayList<User> users = new ArrayList<>();

        try (PreparedStatement getUsersPS = connectionPool.retrieve().prepareStatement(SELECT_USER_IN_CHAT)) {
            getUsersPS.setInt(1, chatId);
            ResultSet resultSet = getUsersPS.executeQuery();

            if (resultSet.next()) {
                users.add(new User(resultSet.getString(2), resultSet.getString(3), resultSet.getInt(1)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public class NoSuchUserException extends Exception {
        public NoSuchUserException(Throwable e) {
            initCause(e);
        }
    }

    private Integer getChatIdByTitle(String chatTitle) {
        Integer chatId = null;

        try (PreparedStatement getIdPS = connectionPool.retrieve().prepareStatement(GET_CHAT_ID)) {
            getIdPS.setString(1, chatTitle);
            ResultSet rs = getIdPS.executeQuery();
            if (rs.next()) {
                chatId = rs.getInt(1);
            }
            getIdPS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chatId;
    }
}
