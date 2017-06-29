package chat.client;

import chat.messages.TextMessage;
import chat.messages.resultMessages.*;
import chat.messages.Message;

   class CommandHandler {

    public void execute(Client client, Message message) {

        switch (message.getMessageType()) {
            case MSG_LOGIN_RESULT:
                LoginResultMessage loginResultMessage = (LoginResultMessage) message;
                client.setId(loginResultMessage.getId());
                break;

            case MSG_CHAT_LIST_RESULT: {
                ChatListResultMessage answer = (ChatListResultMessage) message;
                System.out.println("Список чатов : ");
                System.out.println(answer.getChatIdList());
                break;
            }
            case MSG_CHAT_HIST_RESULT: {
                ChatHistResultMessage answer = (ChatHistResultMessage) message;
                System.out.println("Полная история чата = " + answer.getChatTitle());
                for (TextMessage textMessage : answer.getMessages()){
                    printTextMessage(textMessage);
                }
                break;
            }
            case MSG_INFO_RESULT: {
                InfoResultMessage infoResultMessage = (InfoResultMessage) message;
                System.out.println("Пользователь : " + infoResultMessage.getName() + " с id = " + infoResultMessage.getUserId());
                break;
            }
            case MSG_ERROR: {
                ErrorMessage errorMessage = (ErrorMessage) message;
                System.out.println("Ошибка : " + errorMessage.getErrorMessage());
                break;
            }
            case MSG_TEXT:{
                TextMessage textMessage = (TextMessage) message;
                printTextMessage(textMessage);
                break;
            }
        }

    }

    private void printTextMessage(TextMessage textMessage){
        System.out.println("chat " + textMessage.getChatTitle() + ", time " + textMessage.getTime() +
                " " + textMessage.getText());
    }
}