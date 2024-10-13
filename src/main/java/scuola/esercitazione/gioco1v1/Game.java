package scuola.esercitazione.gioco1v1;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Game {

    private Engine eng;
    private ServerNetManager serverSockManager = null;
    private ClientNetManager clientSockManager = null;

    public void start() {

        System.out.println("1 -> server/listener \n0 -> client");
        Scanner s = new Scanner(System.in);
        int response = s.nextInt();
        s.nextLine();

        if (response == 1) {
            try {
                serverSockManager = new ServerNetManager(ServerNetManager.PORT);
            } catch (IOException ex) {
                System.out.println("Errore nella creazione della server socket" + ex.getMessage());
            }
            Socket clientSocket = serverSockManager.startListening();
            while (clientSocket == null) {
                clientSocket = serverSockManager.startListening();
            }
            clientSockManager = new ClientNetManager(clientSocket);
            boolean closed;
            do {
                closed = serverSockManager.close();
            } while (!closed);
            this.eng = new Engine(clientSockManager, Player.BLACK);
        
        }else if (response == 0) {
            System.out.println("Inserisci l' indirizzo ip al quale connettersi");
            String ip = s.nextLine();
            clientSockManager = new ClientNetManager();
            boolean connected = clientSockManager.connect(ip);

            while (!connected) {
                connected = clientSockManager.connect(ip);
            }
            this.eng = new Engine(clientSockManager, Player.WHITE);
        }

        eng.start();
    }
}
