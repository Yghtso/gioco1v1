package scuola.esercitazione.gioco1v1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class Game {

    private Engine eng;
    public static int port = 60000;

    public void start() {

        // LEGGO L INPUT DA LINEA DI COMANDO :
        // ALE AGO FAI LA PARTE GRAFICA DI QUESTA COSA
        System.out.println("1 -> server/listener \n0 -> client");
        Scanner s = new Scanner(System.in);
        int risposta = s.nextInt();
        s.nextLine();

        // CREO LA SOCKET PER LA FUTURA CONNESSIONE TRA I 2 GIOCATORI
        Socket connection = null;

        if (risposta == 1) {
            ServerSocket serverSock;
            try {
                serverSock = new ServerSocket(port);
                System.out.println("Server in attesa di un altro giocatore . . .");
                connection = serverSock.accept();
            } catch (IOException ex) {
                System.out.println("Errore nella creazione della server socket" + ex.getMessage());
            }

            if (connection != null) {
                this.eng = new Engine(connection, Player.black);
                eng.start();
            }

        } else if (risposta == 0) {
            connection = new Socket();
            System.out.println("Inserisci l' indirizzo ip al quale connettersi");
            String ip = s.nextLine();
            SocketAddress addr = new InetSocketAddress(ip, port);
            try {
                connection.connect(addr);
            } catch (Exception ex) {
                System.out.println("Errore nel connettersi all altro giocatore" + ex.getMessage());
            }

            if (connection != null) {
                this.eng = new Engine(connection, Player.white);
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
