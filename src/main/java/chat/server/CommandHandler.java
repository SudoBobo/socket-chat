package chat.server;

import chat.User;
import chat.messages.*;
import chat.server.store.MessageStore;
import chat.server.store.UserStore;
import chat.server.store.UserStoreImpl;

import java.io.IOException;

public class CommandHandler {

    private UserStore userStore;
    private MessageStore messageStore;

    public CommandHandler (UserStore userStore, MessageStore messageStore){
        this.userStore = userStore;
        this.messageStore = messageStore;
    }

    public void execute(Session session, Message message){
        switch (message.getMessageType()) {

            case MSG_LOGIN:

                LoginMessage loginMessage = (LoginMessage) message;
                Message answerMessage = null;

                try {
                    User user = userStore.getUser(loginMessage.getName(), loginMessage.getPass());
//                    Session.setUser(user);
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

            case MSG_TEXT:
                // достать текст, отправить в чат
                break;

            case MSG_INFO:
                break;

            case MSG_CHAT_LIST:
                break;

            case MSG_CHAT_CREATE:
                break;

            case MSG_CHAT_HIST:
                break;

            case MSG_STATUS:
                break;

            case MSG_CHAT_LIST_RESULT:
                break;
            case MSG_CHAT_HIST_RESULT:
                break;
            case MSG_INFO_RESULT:
                break;
        }

    }
}
