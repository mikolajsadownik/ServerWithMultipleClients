import java.io.*;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ServerThread implements Runnable {

    private Socket clientSocket;

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            log("Client connected from: " + clientSocket.getInetAddress().toString() + ":" + clientSocket.getPort());
            log("Streams collecting");
            InputStream is = clientSocket.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            PrintWriter pr = new PrintWriter(clientSocket.getOutputStream(),true);
            log("Streams collected");


            {
                //dodajemy klienta
                addClient();
                log("Data reading phase");
                String inputLine = br.readLine();
                pr.println(inputLine);
                log("FINAL:" + inputLine);

                addStringToSum(inputLine);
                //alternatywa dla metody jak nie działa
                //Server.sum+=Integer.parseInt(inputLine);

                log("current sum:" + Server.sum);
                //odbieramy liczbę
                inputLine = br.readLine();
                System.out.println("Przed " + inputLine);
                //inputLine = inputLine + inputLine + inputLine + inputLine + inputLine;
                inputLine = concat(inputLine,5);
                System.out.println("Po " + inputLine);
                //odsyłamy skonkatenowaną 5 razy
                pr.println(inputLine);
                //wysyłamy sumę liczb otrzymanych przez masz serwer od wszystkich klientów w ich pierwszych komunikatach
                String sumaJakoString = Integer.toString(Server.sum);
                System.out.println("Suma jako string: " + sumaJakoString);
                pr.println(sumaJakoString);

                //Odsyłamy numer portu z którego się komunikujemy
                System.out.println("Nasz numer portu: " + Integer.toString(Server.PORT));
                pr.println(Integer.toString(Server.PORT));



                //Wczytujemy 5 liczb od serwera i odsyłamy ich sumę
                String str = br.readLine();
        Integer a = Integer.parseInt(str);
        System.out.println(str);

        str = br.readLine();
        Integer b = Integer.parseInt(str);
        System.out.println(str);

        str = br.readLine();
        Integer c = Integer.parseInt(str);
        System.out.println(str);

        str = br.readLine();
        Integer d = Integer.parseInt(str);
        System.out.println(str);

        str = br.readLine();
        Integer e = Integer.parseInt(str);
        System.out.println(str);

                Integer res = a + b + c + d + e;
                String anwser = res.toString();
                System.out.println(" a: " + a + " b: " + b + " c: " + c + " d: " + d + " e: " + e + " res: " + anwser);
                //tutaj finalnie odsyłamy
                pr.println(anwser);

                //wczytujemy od serwera i usuwamy wszystkie wystąpienia 8
                str = br.readLine();
                System.out.println("Liczba z 8: " + str);
                ArrayList<Integer> array = toIntArray(Integer.parseInt(str));
                String nareszcie = deleteNumber(array,8);
                System.out.println("Bez 8 " + nareszcie);
                //odsyłamy liczbę na serwer
                pr.println(nareszcie);
                //pobieramy finalną flagę
                str = br.readLine();
                System.out.println("!!!NASZA FINALNA FLAGA!!!!: "  + str);
                System.out.println("Amount of clients: " + Server.amountOfClients);
            }


            log("Client socket closing");
            clientSocket.close();
            log("Client socket closed");

            log("Ending");
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public static void log(String message) {
        System.out.println("[ST]: " + message);
        System.out.flush();
    }

    public static ArrayList<Integer> toIntArray(int a) {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        do {
            lista.add(a % 10);
            a /= 10;
        } while (a > 0);

        Collections.reverse(lista);

        return lista;
    }

    public static String deleteNumber(ArrayList<Integer> array, int number) {
        String str = "";

        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == number){
                array.remove(i);
                i--;
            }
        }


        for (int i = 0; i < array.size(); i++) {
            str += array.get(i);
        }

        return str;
    }
    public static int nwd(int a, int b) {
        if (a % b == 0)
            return b;
        else {
            int t = a;
            a = b;
            b = t % b;
            return nwd(a, b);
        }

    }

    public static String concat(String str, int amount){
        String tmp = "";
        for(int i = 0; i < amount ; i++){
            tmp += str;
        }
        return tmp;
    }



    public static synchronized void addStringToSum(String inputLine){
        Server.sum+=Integer.parseInt(inputLine);
    }
    public static synchronized void addClient(){
        Server.amountOfClients = Server.amountOfClients + 1;
    }
}