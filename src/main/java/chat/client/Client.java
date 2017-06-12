package chat.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] ar) {

        ClientUnit clientUnit = new ClientUnit(6666);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(clientUnit);
    }
}


