package chat.messages;

/**
 * Типы сообщений в системе
 */
public enum MessageType {
    // Сообщения от клиента к серверу
    MSG_LOGIN("MSG_LOGIN"), // в ответ MSG_STATUS
    MSG_TEXT("MSG_TEXT"), // в ответ MSG_STATUS
    MSG_INFO("MSG_INFO"), // в ответ MSG_INFO_RESULT
    MSG_CHAT_LIST("MSG_CHAT_LIST"), // в ответ MSG_CHAT_LIST_RESULT,
    MSG_CHAT_CREATE("MSG_CHAT_CREATE"), // в ответ MSG_STATUS
    MSG_CHAT_HIST("MSG_CHAT_HIST"), // в ответ MSG_CHAT_HIST_RESULT,

    // Сообщения от сервера клиенту
    MSG_STATUS("MSG_STATUS"),
    MSG_CHAT_LIST_RESULT("MSG_CHAT_LIST_RESULT"),
    MSG_CHAT_HIST_RESULT("MSG_CHAT_HIST_RESULT"),
    MSG_INFO_RESULT("MSG_INFO_RESULT"),
    MSG_ERROR("MSG_ERROR");

    private final String name;

    private MessageType(final String nameString){
        name = new String(nameString);
    }

    public String getName(){
        return name;
    }

    public boolean equals(MessageType anotherType) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(anotherType.getName());
    }

    @Override
    public String toString() {
        return name;
    }
}

