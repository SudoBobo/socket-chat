import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] ar) {

        ClientUnit clientUnit = new ClientUnit(6666);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(clientUnit);
    }


}

class ClientUnit implements Runnable {
    int serverPort;

    ClientUnit(int port) {
        serverPort = port;
    }

    public void run() {
        String address = "127.0.0.1";
        try {
            InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес.
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера.



            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
            String line = null;


            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            User user = new User("bobo", "password");
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();


            while (true) {
                line = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.

                out.writeUTF(line); // отсылаем введенную строку текста серверу.
                out.flush(); // заставляем поток закончить передачу данных.

                line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                System.out.println(line);

            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}