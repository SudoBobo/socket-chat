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
                    text += tokens[i] + " ";
                }
                message = new TextMessage(tokens[1], text);
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
                // chat_create 1,2,3 boboChat

                List<Long> usersId = new LinkedList<>();
                String usersIdString = tokens[1];
                String [] usersSeparated = usersIdString.split(",");

                for (String userString : usersSeparated){
                    usersId.add(Long.getLong(userString));
                }

                message = new ChatCreateMessage(usersId, tokens[2]);
                break;

            case "/chat_hist":
                // /chat_hist boboChat
                message = new ChatHistMessage(tokens[1]);
                break;
        }


        message.setSenderId(senderId);

        return message;
    }
}
