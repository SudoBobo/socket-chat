package chat.server;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    static ServerSocket serverSocket;

    public static void main(String[] ar) {

        try {
            ServerSocket serverSocket = new ServerSocket(6666);
            ExecutorService pool = Executors.newFixedThreadPool(2);
            while (true) {
                pool.execute(new Session(serverSocket.accept()));
            }
        } catch (Exception x)

        {
            x.printStackTrace();
        }
    }
}


