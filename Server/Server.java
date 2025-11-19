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

class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String line;
            while ((line = in.readLine()) != null) {
                JSONObject json = new JSONObject(line); // Parsing JSON
                String tipo = json.getString("tipoSensore");

                if (tipo.equals("temperatura")) {
                    int temp = json.getInt("temperatura");
                    if (temp >= 40) {
                        out.println("ALLARME: Temperatura troppo alta!");
                    }
                } else if (tipo.equals("contatto")) {
                    boolean contatto = json.getBoolean("contatto");
                    if (!contatto) {
                        out.println("ALLARME: Porta aperta!");
                    }
                } else if (tipo.equals("movimento")) {
                    boolean movimento = json.getBoolean("movimento");
                    if (movimento) {
                        out.println("ALLARME: Movimento rilevato!");
                    }
                } else if (tipo.equals("fumo")) {
                    boolean fumo = json.getBoolean("fumo");
                    if (fumo) {
                        out.println("ALLARME: Fumo rilevato!");
                    }
                }
            }
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
