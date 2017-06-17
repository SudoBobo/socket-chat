package chat.client;

import chat.messages.Message;
import chat.messages.MessageDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;



class ClientUnit implements Runnable {



    private final static String address = "127.0.0.1";
    private int serverPort;

    Socket socket;

    ObjectInputStream in;
    ObjectOutputStream out;

    Gson gson;
    CommandHandler commandHandler;

    private final static Logger log = Logger.getLogger(ClientUnit.class);

    public ClientUnit(int port, CommandHandler commandHandler) {

        try {
            serverPort = port;

            InetAddress inetAddress = InetAddress.getByName(address);
            socket = new Socket(inetAddress, serverPort);

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

        try {
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String messageString = null;

            MessageStringValidator messageStringValidator = new MessageStringValidator();
            ClientMessageFactory messageFactory = new ClientMessageFactory();


            while (true) {
                messageString = keyboard.readLine();

                while (messageString.equals("/help")) {
                    System.out.println(UserCommands.getHelp());
                    messageString = keyboard.readLine();
                }

                if (messageStringValidator.isValid(messageString)) {
                    // TODO make it
                    Long myId = 666L;
                    Message message = messageFactory.createMessage(messageString, myId);
                    send(message);

                    String answerJson = (String) in.readObject();
                    System.out.println(answerJson);
                    Message answer = gson.fromJson(answerJson, Message.class);
                    onMessage(answer);

                } else {
                    System.out.println("Sorry, your input is wrong. MessageType /help for list of valid commands");
                }
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public void send(Message message) throws IOException {
        String messageJson = gson.toJson(message, message.getClass());
        out.writeObject(messageJson);
        out.flush();

        log.info("Отправлено сообщение : " + message.getMessageType());

    }

    public void onMessage(Message message) {

        log.info("Принято сообщение : " + message.getMessageType());
        commandHandler.execute(this, message);
    }
}