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
        if (this.player == Player.WHITE) {
            Piece piece = getPiece();
            Move move = getMove(board);
            boolean result = moveValidation(move, piece);
            System.out.println(
                    "PEDINA SELEZIONATA : id#" + piece.getId() + "," + piece.getOwner() + "," + piece.getPosition());
            System.out.println("MOSSA SELEZIONATA : " + move.getPosition());
        }
    }

    boolean moveValidation(Move move, Piece piece) {
        return false;
    }

    // FUNZIONI TEMPORANEE, QUANDO SARA PRESENTE LA PARTE GRAFICA VERRA' LEVATA
    private Piece getPiece() {
        Scanner s = new Scanner(System.in);
        Piece piece = null;
        do {
            System.out.println("Inserisci la casella della pedina da muovere corretta : ");
            int row = s.nextInt();
            int col = s.nextInt();
            piece = board.getPiece(new Position(row, col));
        } while (piece == null);
        s.close();
        return piece;
    }

    private Move getMove(ChessBoard board) {
        Scanner s = new Scanner(System.in);
        System.out.println("Inserisci la casella dove muovere la pedina : ");
        int row = s.nextInt();
        int col = s.nextInt();
        s.close();
        return new Move(new Position(row, col), board.getPiece(new Position(row, col)));
    }
}

enum Player {
    WHITE, BLACK,
}
