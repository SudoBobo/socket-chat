package chat.client;

import chat.messages.Message;
import chat.serialization.MessageDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


class Client implements Runnable {

    private final static String address = "127.0.0.1";

    // set after authorisation
    private Long id;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private Gson gson;
    private CommandHandler commandHandler;

    private final static Logger log = Logger.getLogger(Client.class);

    public static void main(String[] args) {

        int port = 6666;
        CommandHandler commandHandler = new CommandHandler();

        Client client = new Client(port, commandHandler);
        client.run();

    }

    public Client(int port, CommandHandler commandHandler) {

        try {

            InetAddress inetAddress = InetAddress.getByName(address);
            Socket socket = new Socket(inetAddress, port);

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            out = new ObjectOutputStream(sout);
            in = new ObjectInputStream(sin);

            gson = new GsonBuilder().registerTypeAdapter(Message.class, new MessageDeserializer()).create();
            this.commandHandler = commandHandler;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {

        // input reader & command sender
        Runnable sender = () -> {

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String messageString = null;

            MessageStringValidator messageStringValidator = new MessageStringValidator();
            ClientMessageFactory messageFactory = new ClientMessageFactory();


            boolean toProceed = true;
            while (toProceed) {
                try {
                    messageString = keyboard.readLine();

                    while (messageString.equals("/help")) {
                        System.out.println(UserCommands.getHelp());
                        messageString = keyboard.readLine();
                    }

                    if (messageStringValidator.isValid(messageString)) {
                        // TODO make it
                        Message message = messageFactory.createMessage(messageString, id);
                        send(message);
                    } else {
                        System.out.println("Sorry, your input is wrong. MessageType /help for list of valid commands");
                    }
                } catch (Exception x) {
                    x.printStackTrace();
                    toProceed = false;
                }
            }
        };
        // socket listener

        Runnable listener = () -> {
            boolean toProceed = true;
            while (toProceed) {
                try {
                    {
                        String answerJson = (String) in.readObject();
                        Message answer = gson.fromJson(answerJson, Message.class);
                        onMessage(answer);
                    }
                } catch (Exception x) {
                    x.printStackTrace();
                    toProceed = false;
                }
            }
        };

        Thread senderThread = new Thread(sender);
        Thread listenerThread = new Thread(listener);

        senderThread.start();
        listenerThread.start();
    }

    public void send(Message message) throws IOException {
        String messageJson = gson.toJson(message, message.getClass());
        out.writeObject(messageJson);
        out.flush();

        log.debug("Отправлено сообщение : " + message.getMessageType());

    }

    public void onMessage(Message message) {

        log.info("Принято сообщение : " + message.getMessageType());
        commandHandler.execute(this, message);
    }

    public void setId(Long id) {
        this.id = id;
    }
}