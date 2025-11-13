import java.io.*;
import java.net.*;
import org.json.JSONObject;
import java.util.Random;

public class SensoreMovimento {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Random rand = new Random();




    }
}