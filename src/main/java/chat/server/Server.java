package chat.server;

import chat.server.store.*;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    static ServerSocket serverSocket;

    public static void main(String[] ar) {

        try {

            ServerSocket serverSocket = new ServerSocket(6666);
            ExecutorService pool = Executors.newFixedThreadPool(2);

            ConnectionPool connectionPool = new ConnectionPool(10);

            UserStore userStore = new UserStoreImpl(connectionPool);
            MessageStore messageStore = new MessageStoreImpl(connectionPool);

            CommandHandler commandHandler = new CommandHandler(userStore, messageStore);

            while (true) {
                pool.execute(new Session(serverSocket.accept(), commandHandler));
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}


