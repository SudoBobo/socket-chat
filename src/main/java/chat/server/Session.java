package chat.server;

import chat.User;
import chat.messages.Message;
import chat.serialization.MessageDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.util.List;

class Session implements Runnable {

    private User currentUser;
    private CommandHandler commandHandler;
    private Socket socket;
    private Gson gson;

    private List<Session> sessions;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    final static public Logger log = Logger.getLogger(Session.class);


    public Session(Socket socket, CommandHandler commandHandler, List<Session> sessions) {
        this.sessions = sessions;
        sessions.add(this);



        currentUser = null;
        this.socket = socket;
        this.commandHandler = commandHandler;

        commandHandler.setSession(this);
        commandHandler.setSessions(sessions);

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
        boolean toProceed = true;

            while (toProceed) {
                String messageJson = null;
                try {
                    messageJson = (String) in.readObject();
                    Message message = gson.fromJson(messageJson, Message.class);
                    onMessage(message);

                } catch (EOFException  e){
                    log.debug("Пользователь закрыл соединение");
                    toProceed = false;
                    this.close();
                }
                catch (IOException e) {
                    toProceed = false;
                    this.close();
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    toProceed = false;
                    this.close();
                    e.printStackTrace();
                }

            }
    }

    public void send(Message message) throws IOException {
        String messageJson = gson.toJson(message, message.getClass());
        out.writeObject(messageJson);
        out.flush();
    }

    public void onMessage(Message message) {
        commandHandler.execute(message);
    }

    public void setUser(User user){
        currentUser = user;
    }

    public User getUser(){
        return currentUser;
    }

    public boolean isAuthorized(){
        return (currentUser != null);
    }

    private void close(){
        try {
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Session> getSessions() {
        return sessions;
    }
}