package scuola.esercitazione.gioco1v1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerNetManager {

    public static final int PORT = 60000;
    private ServerSocket socket;

    public Socket startListening() {
        try {
            return this.socket.accept();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean close() {
        try {
            socket.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ServerNetManager(int port) throws IOException {
        try {
            this.socket = new ServerSocket(PORT);
            System.out.println("Server Aperto!!!!!!!!!!!!!!!!!!!!!!!!!");
        } catch (Exception e) {
            throw e;
        }
    }
}
