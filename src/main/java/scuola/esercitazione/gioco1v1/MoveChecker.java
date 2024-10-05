package scuola.esercitazione.gioco1v1;

import java.util.Vector;

public class MoveChecker {

    private ChessBoard board;

    public MoveChecker(ChessBoard board) {
        this.board = board;
    }

    public Vector<Move> checkMoves(Vector<Move> moves) {
        int moves_to_validate = moves.size();
        Vector<Move> validatedMoves = new Vector<Move>();

        for (int i = 0; i < moves_to_validate; i++) {
            Move move = moves.get(i);

            System.out.println("Validando una mossa . . .");
            if (move.getPiece() instanceof Pawn) {
                if (validForPawns(move)) {
                    System.out.println("Validazione con successo");
                    validatedMoves.add(move);
                }
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
        return validatedMoves;
    }

    private boolean validForPawns(Move move) {
        Pawn pawn = (Pawn) move.getPiece();

        boolean frontOccupied = board.getPiece(pawn.FORWARD_1.getPosition().clone()) != null;
        boolean frontForwardOccupied = board.getPiece(pawn.FORWARD_2.getPosition().clone()) != null;
        boolean frontLeftOccupied = board.getPiece(pawn.DIAGONAL_LEFT.getPosition().clone()) != null;
        boolean frontRightOccupied = board.getPiece(pawn.DIAGONAL_RIGHT.getPosition().clone()) != null;

        if (move.equals(pawn.FORWARD_1)) {
            return !frontOccupied;
        }

        if (move.equals(pawn.FORWARD_2)) {
            if (frontOccupied || frontForwardOccupied) {
                return false;
            } else {
                return true;
            }
        }

        if (move.equals(pawn.DIAGONAL_LEFT)) {
            return frontLeftOccupied;
        }

        if (move.equals(pawn.DIAGONAL_RIGHT)) {
            return frontRightOccupied;
        }

        return false;

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
