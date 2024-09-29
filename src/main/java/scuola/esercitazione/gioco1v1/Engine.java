package scuola.esercitazione.gioco1v1;

import java.net.Socket;
import java.util.Scanner;

public class Engine {

    private Socket sock;
    private Player player;
    private ChessBoard board;

    public Engine(Socket sock, Player player) {
        this.sock = sock;
        this.player = player;
        this.board = new ChessBoard();
    }

    void start() {
        System.out.println("CONNESSIONE AVVENUTA");
        if (this.player == Player.WHITE) {
            Piece piece = getPiece();
            System.out.println(
                    "PEDINA SELEZIONATA : id#" + piece.getId() + "," + piece.getOwner() + ","
                            + piece.getPosition().getRow() + "," + piece.getPosition().getColumn());
            Move move = getMove(board, piece);
            System.out.println(
                    "MOSSA SELEZIONATA : id#" + move.getPiece().getId() + ", MOSSA VERSO : "
                            + move.getPosition().getRow() + "," + move.getPosition().getColumn());
            boolean result = moveValidation(move, piece);
        }
    }

    boolean moveValidation(Move move, Piece piece) {
        return false;
    }

    // FUNZIONI TEMPORANEE, QUANDO SARA PRESENTE LA PARTE GRAFICA VERRA' LEVATA
    private Piece getPiece() {
        Piece piece = null;
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("Inserisci la casella della pedina da muovere corretta : ");
            int row = s.nextInt();
            int col = s.nextInt();
            s.nextLine();
            piece = board.getPiece(new Position(row, col));
            System.out.println("TROVATO IL PEZZO : " + piece.getClass());
        } while (piece == null);
        return piece;
    }

    private Move getMove(ChessBoard board, Piece piece) {
        Scanner s = new Scanner(System.in);
        System.out.println("Inserisci la casella dove muovere la pedina : ");

        int row = s.nextInt();
        int col = s.nextInt();
        return new Move(new Position(row, col), piece);
    }

}

enum Player {
    WHITE, BLACK,
}
