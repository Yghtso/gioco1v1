package scuola.esercitazione.gioco1v1;

import java.util.ArrayList;

public class MoveChecker {

    private ChessBoard board;

    public MoveChecker(ChessBoard board) {
        this.board = board;
    }

    public void checkMoves(Piece piece) {

        piece.calculateMoves();

        @SuppressWarnings("unchecked")
        ArrayList<Move> allMoves = (ArrayList<Move>) piece.getValidMoves().clone();

        if (piece instanceof Pawn) {
            for (Move move : allMoves) {
                if (!validForPawns(move)) {
                    piece.getValidMoves().remove(move);
                }
            }
        }

        if (piece instanceof Rook) {
            for (Move move : allMoves) {
                if (!validForRooks(move)) {
                    piece.getValidMoves().remove(move);
                }
            }
        }
    }

    private boolean validForPawns(Move move) {
        Pawn pawn = (Pawn) move.getPiece();

        boolean frontOccupied = board.getPiece(pawn.FORWARD_1.getPosition()) != null;
        boolean frontForwardOccupied = board.getPiece(pawn.FORWARD_2.getPosition()) != null;
        boolean frontLeftOccupied = board.getPiece(pawn.DIAGONAL_LEFT.getPosition()) != null;
        boolean frontRightOccupied = board.getPiece(pawn.DIAGONAL_RIGHT.getPosition()) != null;

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

    private boolean validForRooks(Move move) {
        int currentRow = move.getPiece().getPosition().getRow();
        int currentCol = move.getPiece().getPosition().getColumn();

        boolean orizzontal = move.getPosition().getRow() == currentRow;
        int difference;

        if (orizzontal) {
            boolean right = move.getPiece().getPosition().getColumn() > currentCol;
            difference = Math.abs(move.getPiece().getPosition().getColumn() - currentCol);
            if (right) {
                for (int i = 1; i < difference; i++) {
                    if (board.getPiece(new Position(currentRow, currentCol + i)) != null) {
                        return false;
                    }
                }
                return true;
            } else {
                for (int i = 1; i < difference; i++) {
                    if (board.getPiece(new Position(currentRow, currentCol - i)) != null) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            boolean up = move.getPiece().getPosition().getRow() > currentCol;
            difference = Math.abs(move.getPiece().getPosition().getRow() - currentRow);
            if (up) {
                for (int i = 1; i < difference; i++) {
                    if (board.getPiece(new Position(currentRow + i, currentCol)) != null) {
                        return false;
                    }
                }
                return true;
            } else {
                for (int i = 1; i < difference; i++) {
                    if (board.getPiece(new Position(currentRow, currentCol - i)) != null) {
                        return false;
                    }
                }
                return true;
            }
        }
    }
}
