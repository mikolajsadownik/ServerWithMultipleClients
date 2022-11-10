import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
                                        // ip zadania   // port zadania
        Socket sPJWSTK = new Socket("172.21.48.34", 29799);
        PrintWriter prPJWSTK = new PrintWriter(sPJWSTK.getOutputStream(), true);
        // wysłanie flagi do serwera
        prPJWSTK.println("162979");
        //wysłanie mojego adresu + : + mojego portu
        prPJWSTK.println("172.23.129.49:8000");
        //resztę robimy w ServerThreadzie

    }
}
