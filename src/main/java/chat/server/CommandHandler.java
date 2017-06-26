package chat.server;

import chat.Chat;
import chat.User;
import chat.messages.*;
import chat.messages.resultMessages.ChatHistResultMessage;
import chat.messages.resultMessages.ChatListResultMessage;
import chat.messages.resultMessages.ErrorMessage;
import chat.messages.resultMessages.InfoResultMessage;
import chat.server.store.MessageStore;
import chat.server.store.UserStore;
import chat.server.store.impls.UserStoreImpl;

import java.io.IOException;
import java.util.List;

public class CommandHandler {

    private UserStore userStore;
    private MessageStore messageStore;
    private List<Session> sessions;

    private Session session;

    public CommandHandler(UserStore userStore, MessageStore messageStore) {
        this.userStore = userStore;
        this.messageStore = messageStore;
    }

    public CommandHandler(UserStore userStore, MessageStore messageStore, Session session, List<Session> sessions) {
        this.userStore = userStore;
        this.messageStore = messageStore;
        this.sessions = sessions;
        this.session = session;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void execute(Message message) {
        switch (message.getMessageType()) {

            case MSG_LOGIN: {

                // TODO: 21.06.17 добавить проверки на то, авторизован ли пользователь
                // можно свернуть в вызов одной функции
                LoginMessage loginMessage = (LoginMessage) message;
                Message answerMessage = null;

                try {
                    User user = userStore.getUser(loginMessage.getName(), loginMessage.getPass());
                    session.setUser(user);
                    answerMessage = new InfoResultMessage(user.getName(), user.getId());

                } catch (UserStoreImpl.NoSuchUserException e) {
                    session.log.info("fine");
                    answerMessage = new ErrorMessage("Такого пользователя не существует");
                }


                try {
                    session.send(answerMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }

            case MSG_TEXT: {
                TextMessage textMessage = (TextMessage) message;

                List<User> usersInChat = userStore.getUsersInChat(textMessage.getChatId());
                sendToUsers(usersInChat, textMessage);

                break;
            }

            case MSG_INFO: {
                InfoMessage infoMessage = (InfoMessage) message;
                User user = userStore.getUserById(infoMessage.getUserId());


                InfoResultMessage result = new InfoResultMessage(user.getName(), user.getId());

                // TODO читать тяжело
                try {
                    session.send(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

            case MSG_CHAT_LIST: {
//                только для залогиненных пользователей
                ChatListMessage chatListMessage = (ChatListMessage) message;
                User user = userStore.getUserById(chatListMessage.getUserId());

                ChatListResultMessage result = new ChatListResultMessage(messageStore.getChatsByUserId(user.getId()));

                try {
                    session.send(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }

            case MSG_CHAT_CREATE: {
                ChatCreateMessage chatCreateMessage = (ChatCreateMessage) message;

                Chat chat = messageStore.addChat(chatCreateMessage.getUsersId(), "DICK");
                Integer chatId = chat.getId();

                List<User> usersInChat = chat.getUsers();
                TextMessage notification = new TextMessage(chatId, "Вас добавили в чат с id = " + chatId);
                sendToUsers(usersInChat, notification);
            }
            break;

            case MSG_CHAT_HIST: {
                ChatHistMessage chatHistMessage = (ChatHistMessage) message;
                ChatHistResultMessage result =
                        new ChatHistResultMessage(messageStore.getMessagesFromChat(chatHistMessage.getChatId()), chatHistMessage.getChatId());
                try {
                    session.send(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }


        }

    }

    private void sendToUsers(List<User> users, Message message) {
        for (User user : users) {
            try {
                getSessionOfUser(user).send(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private Session getSessionOfUser(User user){
        for (Session session : sessions) {
            if (session.getUser().equals(user)){
                return session;
            }
        }
        return null;
    }
}
