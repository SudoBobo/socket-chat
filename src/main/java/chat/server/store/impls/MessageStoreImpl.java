package chat.server.store.impls;

import chat.messages.Message;
import chat.messages.TextMessage;
import chat.server.store.ConnectionPool;
import chat.server.store.MessageStore;

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
    public List<Integer> getChatsByUserId(Integer userId) {
        return null;
    }

    @Override
    public ArrayList<TextMessage> getMessagesFromChat(Long chatId) {
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
    public void addUserToChat(Long userId, Long chatId) {

    }
}
