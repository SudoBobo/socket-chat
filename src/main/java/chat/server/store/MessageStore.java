package chat.server.store;

import chat.Chat;
import chat.messages.Message;
import chat.messages.TextMessage;

import java.util.ArrayList;
import java.util.List;


public interface MessageStore {
    ArrayList<Integer> getChatsByUserId(Integer userId);

    ArrayList<TextMessage> getMessagesFromChat(Integer chatId);

    Message getMessageById(Integer messageId);

    void addMessage(Integer chatId, Message message);

    void addUserToChat(Integer userId, Integer chatId);

    Chat addChat(List<Long> usersId, String title);
}
