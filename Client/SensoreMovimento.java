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

        while (true) {

            JSONObject json = new JSONObject();
            json.put("id", rand.nextInt(1000));
            json.put("movimento", rand.nextBoolean());
            json.put("tipoSensore", "movimento");

            out.println(json.toString());

            String response = in.readLine();
            if (response != null) System.out.println("Server: " + response);

            try { Thread.sleep(5000); } catch (InterruptedException e) {}



        }



    }
}