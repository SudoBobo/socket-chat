package chat.client;

import chat.messages.*;

import java.util.LinkedList;
import java.util.List;


class ClientMessageFactory {
    public Message createMessage(String messageString, Long senderId) {

        Message message = null;

        String[] tokens = messageString.split(" ");

        String commandType = tokens[0];

        switch (commandType) {
            case "/login":
                message =  new LoginMessage(tokens[1], tokens[2]);
                break;

            case "/text":
                String text = "";
                // TODO замени на regexp
                for (int i = 2; i < tokens.length; i++){
                    text += tokens[i];
                }
                message = new TextMessage(Integer.getInteger(tokens[1]), text);
                break;

            case "/info":
                // userId - user we want to know about
                Long userId = (tokens.length == 2) ? Long.parseLong(tokens[1]) : senderId;
                message = new InfoMessage(userId);
                break;

            case "/chat_list":
                message = new ChatListMessage(senderId);
                break;

            case "/chat_create":
                List<Long> usersId = new LinkedList<>();
                for (int i = 1; i < tokens.length; i++){
                    usersId.add(Long.getLong(tokens[i]));
                }
                message = new ChatCreateMessage(usersId);
                break;

            case "/chat_hist":
                message = new ChatHistMessage(Integer.getInteger(tokens[1]));
                break;
        }


        message.setSenderId(senderId);

        return message;
    }
}
