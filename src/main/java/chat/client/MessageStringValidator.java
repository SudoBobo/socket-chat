package chat.client;

import java.util.Arrays;

class MessageStringValidator {

    public boolean isValid(String messageString) {

        // Что более читаемо - много ретурнов или один ?

        String[] tokens = messageString.split(" ");
        String commandType = tokens[0];

        tokens = Arrays.copyOfRange(tokens, 1, tokens.length);


        if (!isCommandTypeValid(commandType)) {
            return false;
        }

        switch (commandType) {
            case "/login":
                return isLoginArgsValid(tokens);

            case "/info":
               return isInfoArgsValid(tokens);

            case "/chat_list":
                return isChatListArgsValid(tokens);

            case "/chat_create":
                return isChatCreateArgsValid(tokens);

            case "/chat_hist":
                return isChatHistArgsValid(tokens);

            case "/text":
                return isTextArgsValid(tokens);
        }

        return false;
    }

    private boolean isCommandTypeValid(String commandType) {
        return UserCommands.getInstance().exists(commandType);
    }

    private boolean isLoginArgsValid(String[] tokens) {
        return (tokens.length == 2);
    }

    private boolean isInfoArgsValid(String[] tokens) {
        return (tokens.length < 2);
    }

    private boolean isChatListArgsValid(String[] tokens) { return (tokens.length == 1); }

    // /chat_create 1,2,3 boboChat
    private boolean isChatCreateArgsValid(String[] tokens) { return (tokens.length == 2); }

    private boolean isChatHistArgsValid(String[] tokens) { return (tokens.length == 1) ;}

    private boolean isTextArgsValid(String[] tokens) {
        return (tokens.length >= 1);
    }


    public void fixMethod(){

    }
}