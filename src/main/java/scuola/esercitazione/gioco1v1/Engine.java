package scuola.esercitazione.gioco1v1;

import java.net.Socket;
import java.util.Scanner;

public class Engine {

    private Socket sock;
    private Player player;

    public Engine(Socket sock, Player player) {
        this.sock = sock;
        this.player = player;
    };

    void start() {
        Scanner s = new Scanner(System.in);
        if (this.player == Player.white) {
            System.out.println("Inserisci un una mossa : ");
            s.nextLine();
        }
        s.close();
    }

    // FUNZIONE CHE SERVE A MAPPARE LA MOSSA(STRINGA) ALLA MOSSA EFFETTIVA
    // SOLO TEMPORANEA FINO A QUANDO NON SARA' GRAFICO, NON A LINEA DI COMANDO
    void mapToMove(String move) {

    }
}

enum Player {
    white,
    black,
}
