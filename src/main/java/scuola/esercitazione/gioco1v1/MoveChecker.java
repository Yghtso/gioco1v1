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
        System.out.println("Numero mosse : " + movesClone.size());
        for (Move move : movesClone) {
            System.out.println("Mossa : " + move.getPosition().getRow() + ", " + move.getPosition().getColumn());

            if (destinationOwnedBySame(move)) {
                move.getPiece().getValidMoves().remove(move);
            }

            if (leadsToKingCheck(move)) {
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

            } else if (move.getPiece() instanceof Queen) {
                
                if (!validForBishops(move) || !validForRooks(move)) {
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
        boolean frontOccupied = board.getPiece(((Pawn) move.getPiece()).getFORWARD1().getPosition()) != null;
        boolean diagonalLeftOccupied = board.getPiece(((Pawn) move.getPiece()).getDIAGONALLEFT().getPosition()) != null;
        boolean diagonalRightOccupied = board.getPiece(((Pawn) move.getPiece()).getDIAGONALRIGHT().getPosition()) != null;

        if (move.equals(((Pawn) move.getPiece()).getFORWARD2())) {
            return !frontOccupied;
        }

        if (move.equals(((Pawn) move.getPiece()).getDIAGONALLEFT())) {
            return diagonalLeftOccupied;
        }

        if (move.equals(((Pawn) move.getPiece()).getDIAGONALRIGHT())) {
            return diagonalRightOccupied;
        }

        return true;
    }

    private boolean validForRooks(Move move) {

        Piece selectedPiece = move.getPiece() instanceof Rook ? (Rook) move.getPiece() : (Queen) move.getPiece();
        int distance;

        for (Move singleMove : selectedPiece.getTOP()) {
            if (singleMove.equals(move)) {
                distance = move.getPosition().getRow() - selectedPiece.getPosition().getRow();

                for (int i = 1; i < distance; i++) {
                    if (board.getPiece(new Position(selectedPiece.getPosition().getRow() + i,
                            selectedPiece.getPosition().getColumn())) != null) {
                        return false;
                    }
                }
                return true;
            }
        }

        for (Move singleMove : selectedPiece.getRIGHT()) {
            if (singleMove.equals(move)) {
                distance = move.getPosition().getColumn() - selectedPiece.getPosition().getColumn();

                for (int i = 1; i < distance; i++) {
                    if (board.getPiece(new Position(selectedPiece.getPosition().getRow(),
                            selectedPiece.getPosition().getColumn() + i)) != null) {
                        return false;
                    }
                }
                return true;
            }
        }

        for (Move singleMove : selectedPiece.getBOTTOM()) {
            if (singleMove.equals(move)) {
                distance = selectedPiece.getPosition().getRow() - move.getPosition().getRow();

                for (int i = 1; i < distance; i++) {
                    if (board.getPiece(new Position(selectedPiece.getPosition().getRow() - i,
                            selectedPiece.getPosition().getColumn())) != null) {
                        return false;
                    }
                }
                return true;
            }
        }

        for (Move singleMove : selectedPiece.getLEFT()) {
            if (singleMove.equals(move)) {
                distance = selectedPiece.getPosition().getColumn() - move.getPosition().getColumn();

                for (int i = 1; i < distance; i++) {
                    if (board.getPiece(new Position(selectedPiece.getPosition().getRow(),
                            selectedPiece.getPosition().getColumn() - i)) != null) {
                        return false;
                    }
                }
            }
        }
        return true;

    }

    private boolean validForBishops(Move move) {
        Piece selectedPiece = move.getPiece() instanceof Bishop ? (Bishop) move.getPiece() : (Queen) move.getPiece();
        int distance;

        for (Move singleMove : selectedPiece.getTOPLEFTDIAGONAL()) {
            if (singleMove.equals(move)) {
                distance = Math.abs(move.getPosition().getRow() < move.getPosition().getColumn()
                        ? move.getPosition().getRow() - selectedPiece.getPosition().getRow()
                        : move.getPosition().getColumn() - selectedPiece.getPosition().getColumn());
                for (int i = 1; i < distance; i++) {
                    if (board.getPiece(new Position(selectedPiece.getPosition().getRow() + i,
                            selectedPiece.getPosition().getColumn() - i)) != null) {
                        return false;
                    }
                }
                return true;
            }
        }

        for (Move singleMove : selectedPiece.getTOPRIGHTDIAGONAL()) {
            if (singleMove.equals(move)) {
                distance = Math.abs(move.getPosition().getRow() < move.getPosition().getColumn()
                        ? move.getPosition().getRow() - selectedPiece.getPosition().getRow()
                        : move.getPosition().getColumn() - selectedPiece.getPosition().getColumn());

                for (int i = 1; i < distance; i++) {
                    if (board.getPiece(new Position(selectedPiece.getPosition().getRow() + i,
                            selectedPiece.getPosition().getColumn() + i)) != null) {
                        return false;
                    }
                }
                return true;
            }
        }

        for (Move singleMove : selectedPiece.getBOTTOMRIGHTDIAGONAL()) {
            if (singleMove.equals(move)) {
                distance = Math.abs(move.getPosition().getRow() < move.getPosition().getColumn()
                        ? move.getPosition().getRow() - selectedPiece.getPosition().getRow()
                        : move.getPosition().getColumn() - selectedPiece.getPosition().getColumn());

                for (int i = 1; i < distance; i++) {
                    if (board.getPiece(new Position(selectedPiece.getPosition().getRow() - i,
                            selectedPiece.getPosition().getColumn() + i)) != null) {
                        return false;
                    }
                }
                return true;
            }
        }

        for (Move singleMove : selectedPiece.getBOTTOMLEFTDIAGONAL()) {
            if (singleMove.equals(move)) {
                distance = Math.abs(move.getPosition().getRow() < move.getPosition().getColumn()
                        ? move.getPosition().getRow() - selectedPiece.getPosition().getRow()
                        : move.getPosition().getColumn() - selectedPiece.getPosition().getColumn());

                for (int i = 1; i < distance; i++) {
                    if (board.getPiece(new Position(selectedPiece.getPosition().getRow() - i,
                            selectedPiece.getPosition().getColumn() - i)) != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean leadsToKingCheck(Move move) {
        Player ownerMovedPiece = move.getPiece().getOwner();

        ChessBoard alternativeBoard = this.board.clone();
        System.out.println("Mossa : " + move.getPosition().getRow() + ", " + move.getPosition().getColumn());
        //alternativeBoard.applyMove(move);
        MoveChecker checker = new MoveChecker(alternativeBoard);

        for (Piece singlePiece : alternativeBoard.getPieces()) {
            singlePiece.calculateMoves();
            checker.checkMoves(singlePiece.getValidMoves());
            for (Move singleMove : singlePiece.getValidMoves()) {
                if (alternativeBoard.getPiece(singleMove.getPosition()) instanceof King) {
                    return ownerMovedPiece != alternativeBoard.getPiece(singleMove.getPosition()).getOwner();
                }
            }
        }
        return false;
    }

}
