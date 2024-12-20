package scuola.esercitazione.gioco1v1;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientNetManager {

    private Socket socket;

    public boolean send(Move move) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(move);
            outputStream.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Move read() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            return (Move) inputStream.readObject();
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

    public ClientNetManager(Socket socket) {
        this.socket = socket;
    }

    public ClientNetManager() {
        this.socket = new Socket();
    }

    public boolean connect(String ip) {
        try {
            socket.connect(new InetSocketAddress(ip, ServerNetManager.PORT));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Socket getUnderlineSocket() {
        return this.socket;
    }
}
