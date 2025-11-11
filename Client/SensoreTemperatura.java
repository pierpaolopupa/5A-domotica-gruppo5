import java.io.*;
import java.net.*;
import org.json.JSONObject;
import java.util.Random;

public class SensoreTemperatura {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345); 
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Random rand = new Random();

        while (true) {

            double temperaturaCasuale = rand.nextDouble() * 50.0;

            JSONObject json = new JSONObject();
            json.put("id", rand.nextInt(1000));
            json.put("tipo", "temperatura");
            json.put("valore", temperaturaCasuale);
    
            out.println(json.toString());

            String response = in.readLine();
            if (response != null) {
                System.out.println("Server: " + response);
            }

            try { Thread.sleep(5000); } catch (InterruptedException e) {}
        }
    }
}
