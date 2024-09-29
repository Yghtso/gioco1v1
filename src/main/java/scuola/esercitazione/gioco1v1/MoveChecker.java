package scuola.esercitazione.gioco1v1;

import java.util.Vector;

public class MoveChecker {

    private ChessBoard board;

    public Vector<Move> checkMoves(Vector<Move> moves) {
        for (Move move : moves) {
            if (move.getPiece() instanceof Pawn) {
                checkForPawns(move);
            } else if (move.getPiece() instanceof Rook) {
                checkForRooks(move);
            } else if (move.getPiece() instanceof Knight) {
                checkForKnights(move);
            } else if (move.getPiece() instanceof Bishop) {
                checkForBishops(move);
            } else if (move.getPiece() instanceof Queen) {
                checkForQueens(move);
            } else if (move.getPiece() instanceof King) {
                checkForKings(move);
            }
        }
        return null;
    }

    private Vector<Move> checkForPawns(Move move) {
        return null;
    }

    private Vector<Move> checkForRooks(Move move) {
        return null;
    }

    private Vector<Move> checkForKnights(Move move) {
        return null;
    }

    private Vector<Move> checkForBishops(Move move) {
        return null;
    }

    private Vector<Move> checkForQueens(Move move) {
        return null;
    }

    private Vector<Move> checkForKings(Move move) {
        return null;
    }

}
