package chat.client;

import chat.messages.Message;

public class CommandHandler {

    public void execute(ClientUnit clientUnit, Message message) {
        switch (message.getMessageType()) {
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