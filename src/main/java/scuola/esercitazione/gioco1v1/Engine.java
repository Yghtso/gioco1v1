package scuola.esercitazione.gioco1v1;

import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class Engine {

    private ClientNetManager sock;
    private Player player;
    private ChessBoard board;
    private MoveChecker checker;

    public Engine(ClientNetManager sock, Player player) {
        this.sock = sock;
        this.player = player;
        this.board = new ChessBoard();
        this.checker = new MoveChecker(this.board);
    }

    void start() {
        System.out.println("CONNESSIONE AVVENUTA");
        if (this.player == Player.WHITE) {
            Piece piece = getPiece();
            Move move = getMove(board, piece);
            Vector<Move> validatedMoves = checker.checkMoves(piece.calculateMoves());

            for (Move singleMove : validatedMoves) {
                if (singleMove.equals(move)) {
                    sock.send(move);
                }
            }
        } else {
            Move received = sock.read();
            System.out.println("DATA RECEIVED : " + received);
        }
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
