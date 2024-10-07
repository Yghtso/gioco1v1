package scuola.esercitazione.gioco1v1;

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

        if (this.player == Player.WHITE) {

            while (true) {
                Piece piece = getPiece();
                Move move = getMove(board, piece);
                Vector<Move> validatedMoves = checker.checkMoves(piece.calculateMoves());

            for (Move singleMove : validatedMoves) {
                if (singleMove.equals(move)) {
                    System.out.println(sock.send(move));
                }
            }
                Move mossa = sock.read();
            }

        } else {
            while (true) {
                // ATTENZIONE AD ESSERE NULL
                Move mossa = (Move) sock.read();
                Piece piece = getPiece();
                Move move = getMove(board, piece);
                Vector<Move> validatedMoves = checker.checkMoves(piece.calculateMoves());

                for (Move singleMove : validatedMoves) {
                    if (singleMove.equals(move)) {
                        System.out.println(sock.send(move));
                    }
                }
            }
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
