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
                System.out.println("Mossa verso : " + move.getPosition().getRow() + ", "
                        + move.getPosition().getColumn() + ", validita mossa : " + validForRooks(move));
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

        Rook selectedRook = (Rook) move.getPiece();
        Piece destinationPiece = board.getPiece(move.getPosition());
        Player ownerDestinationPiece = destinationPiece == null ? null : destinationPiece.getOwner();
        int distance;
        
        for (Move singleMove : selectedRook.TOP) {
            if (singleMove.equals(move)) {
                distance = move.getPosition().getRow() - selectedRook.getPosition().getRow();

                for (int i = 1; i <= distance; i++) {
                    if (i == distance) {
                        return selectedRook.getOwner() != ownerDestinationPiece;
                    }

                    if (board.getPiece(new Position(selectedRook.getPosition().getRow() + i,
                            selectedRook.getPosition().getColumn())) != null) {
                        return false;
                    }
                }
                return true;
            }
        }

        for (Move singleMove : selectedRook.RIGHT) {
            if (singleMove.equals(move)) {
                distance = move.getPosition().getColumn() - selectedRook.getPosition().getColumn();

                for (int i = 1; i <= distance; i++) {
                    if (i == distance) {
                        return selectedRook.getOwner() != ownerDestinationPiece;
                    }

                    if (board.getPiece(new Position(selectedRook.getPosition().getRow(),
                            selectedRook.getPosition().getColumn() + i)) != null) {
                        return false;
                    }
                }
                return true;
            }
        }

        for (Move singleMove : selectedRook.BOTTOM) {
            if (singleMove.equals(move)) {
                distance = selectedRook.getPosition().getRow() - move.getPosition().getRow();

                for (int i = 1; i <= distance; i++) {
                    if (i == distance) {
                        return selectedRook.getOwner() != ownerDestinationPiece;
                    }

                    if (board.getPiece(new Position(selectedRook.getPosition().getRow() - i,
                            selectedRook.getPosition().getColumn())) != null) {
                        return false;
                    }
                }
                return true;
            }
        }

        for (Move singleMove : selectedRook.LEFT) {
            if (singleMove.equals(move)) {
                distance = selectedRook.getPosition().getColumn() - move.getPosition().getColumn();

                for (int i = 1; i <= distance; i++) {
                    if (i == distance) {
                        return selectedRook.getOwner() != ownerDestinationPiece;
                    }

                    if (board.getPiece(new Position(selectedRook.getPosition().getRow(),
                            selectedRook.getPosition().getColumn() - i)) != null) {
                        return false;
                    }
                }
            }
        }
        return true;

    }
}
