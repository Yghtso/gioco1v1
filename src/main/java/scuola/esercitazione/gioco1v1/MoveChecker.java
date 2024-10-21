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
                System.out.println("Mossa verso : " + move.getPosition().getRow() + ", " + move.getPosition().getColumn() + ", validita mossa : " + validForRooks(move));
                if (!validForRooks(move)) {
                    piece.getValidMoves().remove(move);
                }
            }
        }
    }

    private boolean validForPawns(Move move) {
        Pawn pawn = (Pawn) move.getPiece();

        boolean frontOccupied = board.getPiece(pawn.FORWARD1.getPosition()) != null;
        boolean frontForwardOccupied = board.getPiece(pawn.FORWARD2.getPosition()) != null;
        boolean frontLeftOccupied = board.getPiece(pawn.DIAGONALLEFT.getPosition()) != null;
        boolean frontRightOccupied = board.getPiece(pawn.DIAGONALRIGHT.getPosition()) != null;

        if (move.equals(pawn.FORWARD1)) {
            return !frontOccupied;
        }

        if (move.equals(pawn.FORWARD2)) {
            if (frontOccupied || frontForwardOccupied) {
                return false;
            } else {
                return true;
            }
        }

        if (move.equals(pawn.DIAGONALLEFT)) {
            return frontLeftOccupied;
        }

        if (move.equals(pawn.DIAGONALRIGHT)) {
            return frontRightOccupied;
        }

        return false;
    }

    private boolean validForRooks(Move move) {
        int currentRow = move.getPiece().getPosition().getRow();
        int currentCol = move.getPiece().getPosition().getColumn();

        boolean orizzontal = move.getPosition().getRow() == currentRow;
        int difference;

        Rook selectedRook = (Rook) move.getPiece();
        Piece pieceInMoveDest = move.getPiece() == null ? null : board.getPiece(move.getPosition());
        Player ownerDest = pieceInMoveDest == null ? null : pieceInMoveDest.owner;
        boolean destinationEmpty = pieceInMoveDest == null ? true : false;

        if (orizzontal) {
            boolean right = move.getPosition().getColumn() > currentCol;
            difference = Math.abs(move.getPosition().getColumn() - currentCol);
            
            if (right) {
                for (int i = 1; i <= difference; i++) {

                    if (i == difference) {
                        if (!destinationEmpty) { return selectedRook.getOwner() != ownerDest; } else { return true; }
                    }

                    else if (board.getPiece(new Position(currentRow, currentCol + i)) != null) {
                        return false;
                    } 
                }
                return true;
            } else {
                for (int i = 1; i <= difference; i++) {

                    if (i == difference) {
                        if (!destinationEmpty) { return selectedRook.getOwner() != ownerDest; } else { return true; }
                    }

                    else if (board.getPiece(new Position(currentRow, currentCol - i)) != null) {
                        return false;
                    } 
                }
                return true;
            }
        } else {
            boolean up = move.getPosition().getRow() > currentRow;
            difference = Math.abs(move.getPosition().getRow() - currentRow);
            // SE UNA MOSSA VERSO L ALTO
            if (up) {
                for (int i = 1; i <= difference; i++) {

                    if (i == difference) {
                        if (!destinationEmpty) { return selectedRook.getOwner() != ownerDest; } else { return true; }
                    }

                    else if (board.getPiece(new Position(currentRow + i, currentCol)) != null) {
                        return false;
                    } 
                }
                return true;
            } 
            // SE VERSO IL BASSO
            else {
                for (int i = 1; i <= difference; i++) {

                    if (i == difference) {
                        if (!destinationEmpty) { return selectedRook.getOwner() != ownerDest; } else { return true; }
                    }

                    else if (board.getPiece(new Position(currentRow - i, currentCol)) != null) {
                        return false;
                    } 
                }
                return false;
            }
        }
    }
}
