package chat.server.store;

import chat.messages.Message;

import java.util.List;

public class MessageStoreImpl implements MessageStore {

    private ConnectionPool connectionPool;

    public static final String SELECT_USER = "SELECT * FROM USER WHERE NAME=? and PASSWORD=?";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM USER WHERE ID=?";

    public MessageStoreImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Long> getChatsByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Long> getMessagesFromChat(Long chatId) {
        return null;
    }

    @Override
    public Message getMessageById(Long messageId) {
        return null;
    }

    @Override
    public void addMessage(Long chatId, Message message) {

    }

    @Override
    public void addUserToChat(Long userId, Long chatId) {

    }
}
