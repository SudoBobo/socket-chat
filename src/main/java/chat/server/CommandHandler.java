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
import chat.server.store.impls.MessageStoreImpl;
import chat.server.store.impls.UserStoreImpl;

import java.io.IOException;
import java.util.ArrayList;
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
        session.log.info("Получено сообщение " + message.getMessageType());
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

                if (!session.isAuthorized()){
                    try {
                        session.send(new ErrorMessage(" Вы не авторизованы!"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                TextMessage textMessage = (TextMessage) message;
                messageStore.addMessage(textMessage.getChatTitle(), textMessage);

                List<User> usersInChat = userStore.getUsersInChat(textMessage.getChatTitle());

                sendToUsers(usersInChat, textMessage);

                break;
            }

            case MSG_INFO: {

                if (!session.isAuthorized()){
                    try {
                        session.send(new ErrorMessage(" Вы не авторизованы!"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }

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

                if (!session.isAuthorized()){
                    try {
                        session.send(new ErrorMessage(" Вы не авторизованы!"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }

//                только для залогиненных пользователей
                ChatListMessage chatListMessage = (ChatListMessage) message;
                User user = userStore.getUserById(chatListMessage.getUserId());

//                ChatListResultMessage result = new ChatListResultMessage(messageStore.getChatsByUserId(user.getId()));
                ChatListResultMessage result = new ChatListResultMessage();

                try {
                    session.send(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }

            case MSG_CHAT_CREATE: {

                if (!session.isAuthorized()){
                    try {
                        session.send(new ErrorMessage(" Вы не авторизованы!"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                ChatCreateMessage chatCreateMessage = (ChatCreateMessage) message;
                Chat chat = null;

                try {
                    chat = messageStore.addChat(chatCreateMessage.getUsersId(), chatCreateMessage.getChatTitle());
                } catch (MessageStoreImpl.ChatExistsException e) {
                    try {
                        session.send(new ErrorMessage("Чат с названием " + chatCreateMessage.getChatTitle() + "уже существует!"));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                }


                List<Long> usersInChatIds = chat.getUsersId();
                List<User> usersInChat = new ArrayList<>();

                for (Long id : usersInChatIds){
                    usersInChat.add(userStore.getUserById(id));
                }
                TextMessage notification = new TextMessage(chatCreateMessage.getChatTitle(),
                        "Вас добавили в чат с id = " + chatCreateMessage.getChatTitle());

                sendToUsers(usersInChat, notification);
            }
            break;

            case MSG_CHAT_HIST: {

                if (!session.isAuthorized()){
                    try {
                        session.send(new ErrorMessage(" Вы не авторизованы!"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                ChatHistMessage chatHistMessage = (ChatHistMessage) message;
                String title = chatHistMessage.getChatTitle();

                ChatHistResultMessage result =
                        new ChatHistResultMessage(messageStore.getMessagesFromChat(title), title);
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
                Session session = getSessionOfUser(user);
                if (session != null) {
                    session.send(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private Session getSessionOfUser(User user) {
        for (Session session : sessions) {
            if (session.getUser().equals(user)) {
                return session;
            }
        }
        return null;
    }
}
