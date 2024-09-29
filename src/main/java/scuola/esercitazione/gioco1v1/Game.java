package scuola.esercitazione.gioco1v1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class Game {

    private Engine eng;
    public static int PORT = 60000;

    public void start() {

        // LEGGO L INPUT DA LINEA DI COMANDO :
        // ALE AGO FAI LA PARTE GRAFICA DI QUESTA COSA
        System.out.println("1 -> server/listener \n0 -> client");
        Scanner s = new Scanner(System.in);
        int response = s.nextInt();
        s.nextLine();

        // CREO LA SOCKET PER LA FUTURA CONNESSIONE TRA I 2 GIOCATORI
        Socket connection = null;

        if (response == 1) {
            ServerSocket serverSock = null;
            try {
                serverSock = new ServerSocket(PORT);
                System.out.println("Server in attesa di un altro giocatore . . .");
                connection = serverSock.accept();
            } catch (IOException ex) {
                System.out.println("Errore nella creazione della server socket" + ex.getMessage());
            }

            if (connection != null) {
                try {
                    serverSock.close();
                } catch (Exception ex) {
                    System.out.println("Errore nella chiusura della server socket" + ex.getMessage());
                }
                this.eng = new Engine(connection, Player.BLACK);
                eng.start();
            }

        } else if (response == 0) {
            connection = new Socket();
            System.out.println("Inserisci l' indirizzo ip al quale connettersi");
            String ip = s.nextLine();
            SocketAddress addr = new InetSocketAddress(ip, PORT);
            try {
                connection.connect(addr);
            } catch (Exception ex) {
                System.out.println("Errore nel connettersi all altro giocatore" + ex.getMessage());
            }

            if (connection != null) {
                this.eng = new Engine(connection, Player.WHITE);
                eng.start();
            }
        }
        s.close();

        boolean connectionClosed = false;
        do {
            try {
                connection.close();
                connectionClosed = true;
            } catch (Exception ex) {
                System.out.println("Errore nella chiusura della connessione" + ex.getMessage());
            }
        } while (connectionClosed);

    }
}
