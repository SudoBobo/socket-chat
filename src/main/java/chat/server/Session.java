package chat.server;

import chat.User;
import chat.messages.Message;
import chat.messages.MessageType;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

class Session implements Runnable {
    Socket socket;
    User user;
    // Chat chat;
    final static Logger log = Logger.getLogger(Session.class);


    public Session(Socket socket) {
        this.socket = socket;
    }

    public void run() {


        try {

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();


            ObjectInputStream in = new ObjectInputStream(sin);
            ObjectOutputStream out = new ObjectOutputStream(sout);

//            Gson gson = new GsonBuilder().registerTypeAdapter(Message.class, new MessageDeserializer())
//                    .registerTypeAdapter(Message.class, new MessageSerializer()).create();

            Gson gson = new Gson();

            while (true) {

                String messageJson = (String) in.readObject();
                log.info(messageJson);

                // TODO не работает с типом Message
                Message message = gson.fromJson(messageJson, Message.class);

                log.info(message.getMessageType());
                onMessage((Message) in.readObject());
            }

            // Вот тут пользователь просто подключился, ему надо автоизоваться, мы шлём ему сообщение на авторизацию
            // Клиент его получает, видит поле для ввода логина/пароля
            // sendLoginRequest();


            // ждём какое-то рельное время, пока придёт ответ
            // если он не приходит, освобождаем сокет, освобождаем тред

            // если он приходит, переходим в режим обработки ответов, где также есть максималное время ожидания


            // Надо добавить освобождение ресурсов
            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.


            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
        } catch (Exception x) {
            x.printStackTrace();
        }


    }

    public void send(Message msg) throws IOException {
        // TODO: Отправить клиенту сообщение

    }

    public void onMessage(Message msg) {
        if (msg.getMessageType().equals(MessageType.MSG_LOGIN)){
            System.out.println("We did it!");
        } else {
            System.out.println("Smth went wrong");
        }
    }
}