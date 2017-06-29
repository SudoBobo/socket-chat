package chat.server.store;

import chat.Chat;
import chat.messages.TextMessage;
import chat.server.store.impls.MessageStoreImpl;

import java.util.ArrayList;
import java.util.List;


public interface MessageStore {

    ArrayList<TextMessage> getMessagesFromChat(String chatTitle);

    void addMessage(String chatTitle, TextMessage message);

    void addUserToChat(Integer userId, String chatTitle);

    Chat addChat(List<Long> usersId, String title) throws MessageStoreImpl.ChatExistsException;
}
