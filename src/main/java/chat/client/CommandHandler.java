package chat.client;

import chat.messages.ErrorMessage;
import chat.messages.InfoResultMessage;
import chat.messages.Message;

   class CommandHandler {

    public void execute(Client client, Message message) {
        System.out.println(message.getMessageType().toString());

        switch (message.getMessageType()) {
            case MSG_CHAT_LIST_RESULT:
                break;
            case MSG_CHAT_HIST_RESULT:
                break;
            case MSG_INFO_RESULT:
                InfoResultMessage infoResultMessage = (InfoResultMessage) message;
                System.out.println("Пользователь : " + infoResultMessage.getName() + " с id = " + infoResultMessage.getUserId());
                break;
            case MSG_ERROR:
                ErrorMessage errorMessage = (ErrorMessage) message;
                System.out.println("Ошибка : " + errorMessage.getErrorMessage());
        }

    }
}