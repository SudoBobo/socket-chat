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

    final static Logger log = Logger.getLogger(Session.class);


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
                    onMessage(message);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
    }

    public void send(Message message) throws IOException {
        String messageJson = gson.toJson(message, Message.class);
        out.writeObject(messageJson);
        out.flush();
    }

    public void onMessage(Message message) {
        commandHandler.execute(this, message);
    }
}