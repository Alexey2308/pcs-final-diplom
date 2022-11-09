import java.io.*;
import java.net.Socket;


public class Client {
    public static void main(String[] args) {
        String HOST = "localhost";
        String result;
        String searchWord="бизнес";
        int PORT = 8989;
        try (Socket clientSocket = new Socket(HOST, PORT);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter output = new PrintWriter(new BufferedWriter(
                     new OutputStreamWriter(clientSocket.getOutputStream())), true)) {
            output.println(searchWord);
            for (int i=0;(result = in.readLine()) !=null; i++) {
            System.out.println(result);
        }
        }catch (IOException e){
            e.printStackTrace();
    }
        }
        }



