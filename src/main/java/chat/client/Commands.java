package chat.client;

import java.util.HashMap;
import java.util.HashSet;

public class Commands {
    private static final Commands instance = new Commands();

    static public HashSet<String> commandTypes;
    static public HashMap<String, String> commandDescriptions;

    private Commands(){

        commandTypes.add("/login");
        commandTypes.add("/info");
        commandTypes.add("/chat_list");

        commandTypes.add("/chat_create");
        commandTypes.add("/chat_hist");
        commandTypes.add("/text");


        commandDescriptions.put("/login", "Login or register (Если используется в первый раз\n" +
                "/login <логин_пользователя> <пароль>\n" +
                "/login arhangeldim qwerty\n");

        commandDescriptions.put("/info", "получить всю информацию о пользователе, без аргументов - о себе\n" +
                "info [id]\n" +
                "/info инфа о себе\n" +
                "/info 3 - инфа о пользователе id=3\n");


        commandDescriptions.put("/chat_list", "получить список чатов пользователя\n" +
        "/chat_list\n");

        commandDescriptions.put("/chat_create", "создать новый чат\n" +
                "/chat_create <user_id list>\n" +
                "/chat_create 1,2,3,4 - создать чат с пользователями id=1, id=2, id=3, id=4\n" +
                "/chat_create 3 - создать чат с пользователем id=3, если такой чат уже существует, вернуть существующий\n");

        commandDescriptions.put("/chat_hist", "список сообщений из указанного чата\n" +
        "/chat_history <chat_id>\n" +
                "/chat_history 2 - сообщения из чата id=2\n");


        commandDescriptions.put("/text", "отправить сообщение в заданный чат, чат должен быть в списке чатов пользователя\n"+
        "/text <id> <message>\n" +
                "/text 3 Hello, it's pizza time! - отправить указанное сообщение в чат id=3\n");
    }

    public static Commands getInstance(){
        return instance;
    }
}