import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    private final int port;
    private final BooleanSearchEngine searchResult;

    public Server(int port) throws IOException {
        this.port = port;
        searchResult = new BooleanSearchEngine(new File("pdfs"));
    }

    public static String listToJson(List<PageEntry> pageEntryList) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        return gson.toJson(pageEntryList);
    }

    public void serverStart() {

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен!!!");
            while (true) {

                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter output = new PrintWriter(new BufferedWriter(
                                     new OutputStreamWriter(socket.getOutputStream())), true)) {
                    String answer = in.readLine();
                    List<PageEntry> resultList = searchResult.search(answer);

                    String gson = listToJson(resultList);
                    output.println(gson);
                }
            }
        } catch (IOException e) {
            System.out.println("Сервер не запущен!!!");
            e.printStackTrace();
        }
    }

}
