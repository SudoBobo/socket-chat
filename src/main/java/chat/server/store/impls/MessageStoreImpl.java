package chat.server.store.impls;

import chat.Chat;
import chat.messages.Message;
import chat.messages.TextMessage;
import chat.server.store.ConnectionPool;
import chat.server.store.MessageStore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageStoreImpl implements MessageStore {

    private ConnectionPool connectionPool;

    public static final String SELECT_USER = "SELECT * FROM USER WHERE NAME=? and PASSWORD=?";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM USER WHERE ID=?";

    public MessageStoreImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    @Override
    public ArrayList<Integer> getChatsByUserId(Integer userId) {
        return null;
    }

    @Override
    public ArrayList<TextMessage> getMessagesFromChat(Integer chatId) {
        return null;
    }

    @Override
    public Message getMessageById(Integer messageId) {
        return null;
    }

    @Override
    public void addMessage(Integer chatId, Message message) {

    }

    @Override
    public void addUserToChat(Integer userId, Integer chatId) {

    }

    @Override
    public Chat addChat(List<Long> usersId, String title) {
        // TODO добавить проверку на то, не существует ли уже такого чата

        // TODO добавить всю эту злоебучую хуйню с тайтлом в валидатор, тип сообщения, меседж фактори
        // и месседж хэндлер

        final String ADD_USER = "INSERT INTO chats_users (chat_id, user_id) VALUES(?, ?);";
        final String ADD_CHAT = "INSERT INTO chats (title) VALUES(?);";
        final String GET_CHAT_ID = "SELECT id FROM chats WHERE title=?;";
        // query work


        // добавление чата
        try {
            PreparedStatement addChatPS = connectionPool.retrieve().prepareStatement(ADD_CHAT);
            addChatPS.setString(1, title);
            int res = addChatPS.executeUpdate();
            addChatPS.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // получение айди добавленого чата

        Integer chatId = null;
        try {
            PreparedStatement getIdPS = connectionPool.retrieve().prepareStatement(GET_CHAT_ID);
            getIdPS.setString(1, title);
            ResultSet rs = getIdPS.executeQuery();
            if (rs.next()) {
                chatId = rs.getInt(1);
            }
            getIdPS.close();
        } catch (SQLException e){
            e.printStackTrace();
        }

        // добавление пользователей в chats_users

        try {
            PreparedStatement addUserPS = connectionPool.retrieve().prepareStatement(ADD_USER);
            int rs = 0;
            for (Long userId : usersId) {
                addUserPS.setInt(1, chatId);
                addUserPS.setInt(2, userId.intValue());
                rs = addUserPS.executeUpdate();
            }
            addUserPS.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
