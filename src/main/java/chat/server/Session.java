package chat.server;

import chat.messages.Message;
import chat.messages.MessageDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

class Session implements Runnable {

    private CommandHandler commandHandler;
    private Socket socket;
    private Gson gson;

    ObjectInputStream in;
    ObjectOutputStream out;

    final static public Logger log = Logger.getLogger(Session.class);


    public Session(Socket socket, CommandHandler commandHandler) {
        this.socket = socket;
        this.commandHandler = commandHandler;

        gson = new GsonBuilder().registerTypeAdapter(Message.class, new MessageDeserializer()).create();

        try {
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

          in = new ObjectInputStream(sin);
          out = new ObjectOutputStream(sout);

        } catch (Exception x) {
            x.printStackTrace();
        }

    }

    public void run() {
            while (true) {
                String messageJson = null;
                try {
                    messageJson = (String) in.readObject();
                    Message message = gson.fromJson(messageJson, Message.class);
                    System.out.println(message.getMessageType().toString());
                    onMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
    }

    public void send(Message message) throws IOException {
        System.out.println(message);
        String messageJson = gson.toJson(message, message.getClass());
        out.writeObject(messageJson);
        System.out.println(messageJson);
        out.flush();
    }

    public void onMessage(Message message) {
        commandHandler.execute(this, message);
    }
}