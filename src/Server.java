
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static final int PORT = 8000;
    static int sum = 0;
    static int amountOfClients = 0;

    public static void main(String[] args) throws IOException {
        log("Starting");
        log("Server socket opening");

        //nasz adres
        InetAddress sa = InetAddress.getByName("172.23.129.49");

        ServerSocket welcomeSocket = new ServerSocket(PORT, 10, sa);
        log("Server socket opened");

        while (true) {
            log("Server socket listening");
            Socket clientSocket = welcomeSocket.accept();
            log("Server socket listening");
            ServerThread st = new ServerThread(clientSocket);
            //rozpoczynamy wątek
            new Thread(st).start();
        }
    }

    public static void log(String message) {
        System.out.println("[S]: " + message);
        System.out.flush();
    }
}
