import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Arrays;

public class API {

    private final String serverName;
    private final int serverPort;
    private OutputStream serverOut;
    private BufferedReader bufferedIn;

    public API(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    public void run(String token) throws IOException {
        if (!connect()) {
            System.err.println("Server Offline");
        } else {
            if (verify(token)) {
                System.out.println("Success!");
            }
        }
    }

    boolean verify(String token) throws IOException {
        String cmd = "token" + token;
        serverOut.write(cmd.getBytes());
        String response = bufferedIn.readLine();
        if (response.contains("404")) {
            System.out.println(response);
            return false;
        } else {
            System.out.println(response);
            return true;
        }
    }

    public boolean connect() {
        try {
            try {
                Socket socket = new Socket(serverName, serverPort);
                this.serverOut = socket.getOutputStream();
                InputStream serverIn = socket.getInputStream();
                this.bufferedIn = new BufferedReader(new InputStreamReader(serverIn));
                return true;
            } catch(ConnectException e) {
                e.printStackTrace();
                return false;
            }
        } catch (IOException e) {
            System.out.println("Server Offline");
        }
        return false;
    }
}