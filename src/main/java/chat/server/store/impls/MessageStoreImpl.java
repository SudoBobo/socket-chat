package chat.server.store.impls;

import chat.Chat;
import chat.messages.TextMessage;
import chat.server.store.ConnectionPool;
import chat.server.store.MessageStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Date;

public class MessageStoreImpl implements MessageStore {

    private ConnectionPool connectionPool;

    public static final String ADD_USER = "INSERT INTO chats_users (chat_id, user_id) VALUES(?, ?);";
    public static final String ADD_CHAT = "INSERT INTO chats (title) VALUES(?);";
    public static final String ADD_MESSAGE = "INSERT INTO messages (chat_id, text, time) VALUES (?, ?, ?);";

    public static final String GET_CHAT_ID = "SELECT id FROM chats WHERE title=?;";
    public static final String GET_MESSAGES = "SELECT text, time FROM messages WHERE id = ?;";

    public MessageStoreImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public ArrayList<TextMessage> getMessagesFromChat(String chatTitle) {
        Integer chatId = getChatIdByTitle(chatTitle);
        ArrayList<TextMessage> messages = new ArrayList<>();

        try (PreparedStatement getMessagesPS = connectionPool.retrieve().prepareStatement(GET_MESSAGES)){
            getMessagesPS.setInt(1, chatId);
            ResultSet rs = getMessagesPS.executeQuery();

            if (rs.next()) {
                messages.add(new TextMessage(chatTitle, rs.getString(1),  rs.getDate(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }


    @Override
    public void addMessage(String chatTitle, TextMessage message) {
        Integer chatId = getChatIdByTitle(chatTitle);
        try (PreparedStatement addMessagePS = connectionPool.retrieve().prepareStatement(ADD_MESSAGE)){
            addMessagePS.setInt(1, chatId);
            addMessagePS.setString(2, message.getText());
            addMessagePS.setDate(3, new Date(message.getTime().getDate()));
            int rs = addMessagePS.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUserToChat(Integer userId, String chatTitle) {

        Integer chatId = getChatIdByTitle(chatTitle);

        try (PreparedStatement addUserPS = connectionPool.retrieve().prepareStatement(ADD_USER)) {
            int rs = 0;
            addUserPS.setInt(1, chatId);
            addUserPS.setInt(2, userId);
            rs = addUserPS.executeUpdate();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Chat addChat(List<Long> usersId, String chatTitle) throws ChatExistsException {

        // проверка, существует ли уже такой чат
        final String GET_CHAT = "SELECT * FROM chats WHERE title = ?";

        try(PreparedStatement getChatPS = connectionPool.retrieve().prepareStatement(GET_CHAT)) {
            getChatPS.setString(1, chatTitle);
            ResultSet resultSet = getChatPS.executeQuery();

            if (resultSet.next()){
                getChatPS.close();
                throw new ChatExistsException(new Throwable("Chat " + chatTitle + " already exists!"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        try (PreparedStatement addChatPS = connectionPool.retrieve().prepareStatement(ADD_CHAT)) {
            addChatPS.setString(1, chatTitle);
            int res = addChatPS.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Integer chatId = getChatIdByTitle(chatTitle);


        try (PreparedStatement addUserPS = connectionPool.retrieve().prepareStatement(ADD_USER)) {
            int rs = 0;
            for (Long userId : usersId) {
                addUserPS.setInt(1, chatId);
                addUserPS.setInt(2, userId.intValue());
                rs = addUserPS.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public class ChatExistsException extends Exception {
        public ChatExistsException(Throwable e) {
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
