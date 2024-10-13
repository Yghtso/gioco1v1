package scuola.esercitazione.gioco1v1;

import java.util.ArrayList;
import java.util.Scanner;

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

    public void start() {

        if (this.player == Player.BLACK) {
            Move readMove = (Move) sock.read();
            board.applyMove(readMove);
        }

        do {
            ArrayList<Piece> pieces = board.getPieces();
            System.out.println("Stato della scacchiera : ");
            for (Piece singlePiece : pieces) {
                System.out.println("Pedina = " + singlePiece + ", id : " + singlePiece.getId() + ", posizione : " + singlePiece.getPosition().getRow() + "," + singlePiece.getPosition().getColumn());
            }

            Piece selectedPiece = getPiece();
            Move move = getMove(board, selectedPiece);
            checker.checkMoves();

            for (Move iteratorMove : move.getPiece().getValidMoves()) {
                if (iteratorMove.equals(move)) {
                    sock.send(move);
                }
            }

            Move readMove = sock.read();
            board.applyMove(readMove);
        } while(true);
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
