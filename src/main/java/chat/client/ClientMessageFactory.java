package chat.client;

import chat.messages.*;


class ClientMessageFactory {
    public Message createMessage(String messageString, Long senderId) {

        Message message = null;

        String[] tokens = messageString.split(" ");

        String commandType = tokens[0];

        switch (commandType) {
            case "/login":
                //TODO Сделай нормальное заполнение полей
                message =  new LoginMessage(tokens[1], tokens[2]);
                break;
            case "/text":
                // FIXME: пример реализации для простого текстового сообщения
                break;
            case "/info":
                // TODO check "/info " case
                // userId - user we want to know about
                Long userId = (tokens.length == 2) ? Long.parseLong(tokens[1]) : senderId;
                message = new InfoMessage(userId);
                break;
        }

        message.setId(1L);
        message.setSenderId(senderId);

        return message;
    }
}
