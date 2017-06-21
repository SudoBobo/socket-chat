package chat.server;

import chat.server.store.*;
import chat.server.store.impls.MessageStoreImpl;
import chat.server.store.impls.UserStoreImpl;

import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    static ServerSocket serverSocket;

    public static void main(String[] ar) {

        List<Session> sessions = new LinkedList<>();

        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            ExecutorService pool = Executors.newFixedThreadPool(2);

            ConnectionPool connectionPool = new ConnectionPool(10);

            UserStore userStore = new UserStoreImpl(connectionPool);
            MessageStore messageStore = new MessageStoreImpl(connectionPool);

            while (true) {
                pool.execute(new Session(serverSocket.accept(), new CommandHandler(userStore, messageStore), sessions));
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}


