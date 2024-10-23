package scuola.esercitazione.gioco1v1;

import java.util.ArrayList;

public class MoveChecker {

    private ChessBoard board;

    public MoveChecker(ChessBoard board) {
        this.board = board;
    }

    public void checkMoves(ArrayList<Move> moves) {

        @SuppressWarnings("unchecked")
        ArrayList<Move> movesClone = (ArrayList<Move>) moves.clone();

        for (Move move : movesClone) {
            if (destinationOwnedBySame(move)) {
                move.getPiece().getValidMoves().remove(move);
            }
            
            if (move.getPiece() instanceof Pawn) {
                if (!validForPawns(move)) {
                    move.getPiece().getValidMoves().remove(move);
                }

            } else if (move.getPiece() instanceof Rook) {

                if (!validForRooks(move)) {
                    move.getPiece().getValidMoves().remove(move);
                }
            
            } else if (move.getPiece() instanceof Bishop) {
            
                if (!validForBishops(move)) {
                    move.getPiece().getValidMoves().remove(move);
                }
            }
        }

    }

    private boolean destinationOwnedBySame(Move move) {
        Piece destPiece = board.getPiece(move.getPosition());
        Player destPlayer = destPiece == null ? null : destPiece.getOwner();
        return move.getPiece().getOwner() == destPlayer;
    }

    private boolean validForPawns(Move move) {
        boolean frontOccupied = board.getPiece(((Pawn) move.getPiece()).FORWARD1.getPosition()) != null;
        boolean diagonalLeftOccupied = board.getPiece(((Pawn) move.getPiece()).DIAGONALLEFT.getPosition()) != null;
        boolean diagonalRightOccupied = board.getPiece(((Pawn) move.getPiece()).DIAGONALRIGHT.getPosition()) != null;

        if (move.equals(((Pawn) move.getPiece()).FORWARD2)) {
            return !frontOccupied;
        }

        if (move.equals(((Pawn) move.getPiece()).DIAGONALLEFT)) {
            return diagonalLeftOccupied;
        }

        if (move.equals(((Pawn) move.getPiece()).DIAGONALRIGHT)) {
            return diagonalRightOccupied;
        }

        return true;
    }

    private boolean validForRooks(Move move) {

        Rook selectedRook = (Rook) move.getPiece();
        int distance;

        for (Move singleMove : selectedRook.TOP) {
            if (singleMove.equals(move)) {
                distance = move.getPosition().getRow() - selectedRook.getPosition().getRow();

                for (int i = 1; i < distance; i++) {
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

                for (int i = 1; i < distance; i++) {
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

                for (int i = 1; i < distance; i++) {
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

                for (int i = 1; i < distance; i++) {
                    if (board.getPiece(new Position(selectedRook.getPosition().getRow(),
                            selectedRook.getPosition().getColumn() - i)) != null) {
                        return false;
                    }
                }
            }
        }
        return true;

    }

    private boolean validForBishops(Move move) {
        Bishop selectedBishop = (Bishop) move.getPiece();
        Piece destinationPiece = board.getPiece(move.getPosition());
        Player ownerDestinationPiece = destinationPiece == null ? null : destinationPiece.getOwner();
        int distance;

        for (Move singleMove : selectedBishop.TOPLEFTDIAGONAL) {
            if (singleMove.equals(move)) {
                distance = Math.abs(move.getPosition().getRow() < move.getPosition().getColumn()
                        ? move.getPosition().getRow() - selectedBishop.getPosition().getRow()
                        : move.getPosition().getColumn() - selectedBishop.getPosition().getColumn());
                for (int i = 1; i < distance; i++) {
                    if (board.getPiece(new Position(selectedBishop.getPosition().getRow() + i,
                            selectedBishop.getPosition().getColumn() - i)) != null) {
                        return false;
                    }
                }
                return true;
            }
        }

        for (Move singleMove : selectedBishop.TOPRIGHTDIAGONAL) {
            if (singleMove.equals(move)) {
                distance = Math.abs(move.getPosition().getRow() < move.getPosition().getColumn()
                        ? move.getPosition().getRow() - selectedBishop.getPosition().getRow()
                        : move.getPosition().getColumn() - selectedBishop.getPosition().getColumn());

                for (int i = 1; i < distance; i++) {
                    if (board.getPiece(new Position(selectedBishop.getPosition().getRow() + i,
                            selectedBishop.getPosition().getColumn() + i)) != null) {
                        return false;
                    }
                }
                return true;
            }
        }

        for (Move singleMove : selectedBishop.BOTTOMRIGHTDIAGONAL) {
            if (singleMove.equals(move)) {
                distance = Math.abs(move.getPosition().getRow() < move.getPosition().getColumn()
                        ? move.getPosition().getRow() - selectedBishop.getPosition().getRow()
                        : move.getPosition().getColumn() - selectedBishop.getPosition().getColumn());

                for (int i = 1; i < distance; i++) {
                    if (board.getPiece(new Position(selectedBishop.getPosition().getRow() - i,
                            selectedBishop.getPosition().getColumn() + i)) != null) {
                        return false;
                    }
                }
                return true;
            }
        }

        for (Move singleMove : selectedBishop.BOTTOMLEFTDIAGONAL) {
            if (singleMove.equals(move)) {
                distance = Math.abs(move.getPosition().getRow() < move.getPosition().getColumn()
                        ? move.getPosition().getRow() - selectedBishop.getPosition().getRow()
                        : move.getPosition().getColumn() - selectedBishop.getPosition().getColumn());

                for (int i = 1; i < distance; i++) {
                    if (board.getPiece(new Position(selectedBishop.getPosition().getRow() - i,
                            selectedBishop.getPosition().getColumn() - i)) != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
