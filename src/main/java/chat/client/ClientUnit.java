package chat.client;

import chat.messages.Message;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;



class ClientUnit implements Runnable {
    private final static Logger log = Logger.getLogger(ClientUnit.class);
    private int serverPort;

    ClientUnit(int port) {
        serverPort = port;
    }

    public void run() {
        String address = "127.0.0.1";
        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, serverPort);

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            ObjectOutputStream out = new ObjectOutputStream(sout);
            ObjectInputStream in = new ObjectInputStream(sin);


            Gson gson = new Gson();

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String messageString = null;

            MessageStringValidator messageStringValidator = new MessageStringValidator();
            ClientMessageFactory messageFactory = new ClientMessageFactory();


            while (true) {
                messageString = keyboard.readLine();

                while (messageString.equals("/help")) {
                    System.out.println(Commands.getHelp());
                    messageString = keyboard.readLine();
                }

                if (messageStringValidator.isValid(messageString)) {
                    // TODO make it
                    Long myId = 666L;
                    Message message = messageFactory.createMessage(messageString, myId);

                    String messageJson = gson.toJson(message, Message.class);
                    out.writeObject(messageJson);
                    out.flush();
                    log.info("Отправлено сообщение : " + message.getMessageType());

                    String answerJson = (String) in.readObject();
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

    public void send(Message msg) throws IOException {
        // TODO: Отправить клиенту сообщение

    }

    public void onMessage(Message msg) {
        System.out.println(msg.getMessageType().toString());
    }
}