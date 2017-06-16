package chat.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] ar) {

        CommandHandler commandHandler = new CommandHandler();
        ClientUnit clientUnit = new ClientUnit(6666, commandHandler);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(clientUnit);
    }
}


