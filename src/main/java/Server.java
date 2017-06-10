import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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


class Session implements Runnable {
    Socket socket;
    User user = new User("bobo", "password");
    // Chat chat;


    public Session(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {

            // Надо добавить освобождение ресурсов
            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String line = null;

            // Сначала получаем строчку с сообщением регистрации


            ObjectInputStream oin = new ObjectInputStream(sin);
            User user = (User) oin.readObject();

            System.out.println(user.name + " " + user.pass);
            // Если не получаем её слищком долго или команда неправильная, то удаляем сессию и открываем сокет
            while (true) {
                line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                out.flush(); // заставляем поток закончить передачу данных.
                out.writeUTF(line + "remote addr = " + socket.getRemoteSocketAddress() + "local addr =" +
                        socket.getLocalSocketAddress()); // отсылаем клиенту обратно ту самую строку текста.
                System.out.println(Thread.currentThread().getName());
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}