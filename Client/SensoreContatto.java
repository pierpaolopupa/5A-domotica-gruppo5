import java.io.*;
import java.net.*;
import org.json.JSONObject;
import java.util.Random;

public class SensoreContattoPorta {
    public static void main(String[] args) throws IOException 
    {
        Socket socket = new Socket("localhost", 12345); 
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Random rand = new Random();

        while (true) {
           
            boolean statoContatto = rand.nextBoolean();
         
            JSONObject json = new JSONObject();
            json.put("id", rand.nextInt(1000)); 
            json.put("tipo", "contatto");
            json.put("contatto", statoContatto); 
        
            output.println(json.toString());
            
            String risposta = input.readLine();
            if (risposta != null) {
                System.out.println("Server: " + risposta);
            }

            try 
            {
                Thread.sleep(5000);
            } catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
    }
}