import java.io.*;
import java.net.*;
import org.json.JSONObject;

public class MultiServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Server in ascolto sulla porta 12345...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(new ClientHandler(clientSocket)).start();
        }
    }
}
