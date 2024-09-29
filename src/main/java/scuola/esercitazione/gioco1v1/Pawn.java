package scuola.esercitazione.gioco1v1;

import java.util.Vector;

public class Pawn extends Piece {

    private boolean firstMove;

    private final static int BASE_PAWN_MOVE = 1;
    private final static int STARTING_PAWN_MOVE = 2;

    public Vector<Move> calculateMoves() {
        Vector<Move> moves = new Vector<Move>();

        if (this.owner == Player.WHITE) {
            checkWhiteMoves(moves);
        } else {
            checkBlackMoves(moves);
        }

        return moves;
    }

    public void moveTo(Position position) {
        this.position = position;
        firstMove = false;
    }

    public Pawn(Position position, Player owner, int id) {
        super(position, owner, id);
        this.firstMove = true;
    }

    private void checkWhiteMoves(Vector<Move> moves) {
        if (firstMove) { // MOSSA INIZIALE 2 IN AVANTI
            moves.add(new Move(
                    new Position(ChessBoard.WHITE_PAWNS_STARTING_ROW + STARTING_PAWN_MOVE,
                            this.getPosition().getColumn()),
                    this));
        }
        if (this.getPosition().getRow() != ChessBoard.LAST_ROW) { // MOSSA BASE 1 IN AVANTI
            moves.add(new Move(
                    new Position(this.getPosition().getRow() + BASE_PAWN_MOVE, this.getPosition().getColumn()),
                    this));
        }
        if (this.getPosition().getColumn() != ChessBoard.FIRST_COLUMN
                || this.getPosition().getRow() != ChessBoard.LAST_ROW) { // MANGIARE IN DIAGONALE A SINISTRA
            moves.add(new Move(
                    new Position(this.getPosition().getRow() + BASE_PAWN_MOVE,
                            this.getPosition().getColumn() - BASE_PAWN_MOVE),
                    this));
        }
        if (this.getPosition().getColumn() != ChessBoard.LAST_COLUMN
                || this.getPosition().getRow() != ChessBoard.LAST_ROW) { // MANGIARE IN DIAGONALE A DESTRA
            moves.add(new Move(
                    new Position(this.getPosition().getRow() + BASE_PAWN_MOVE,
                            this.getPosition().getColumn() + BASE_PAWN_MOVE),
                    this));
        }
    }

    private void checkBlackMoves(Vector<Move> moves) {
        if (firstMove) { // MOSSA INIZIALE 2 IN AVANTI
            moves.add(new Move(
                    new Position(ChessBoard.LAST_ROW - ChessBoard.BLACK_PAWNS_STARTING_ROW + STARTING_PAWN_MOVE - 1,
                            this.getPosition().getColumn()),
                    this));
        }
        if (this.getPosition().getRow() != ChessBoard.LAST_ROW) { // MOSSA BASE 1 IN AVANTI
            moves.add(new Move(
                    new Position(this.getPosition().getRow() + BASE_PAWN_MOVE, this.getPosition().getColumn()),
                    this));
        }
        if (this.getPosition().getColumn() != ChessBoard.FIRST_COLUMN
                || this.getPosition().getRow() != ChessBoard.LAST_ROW) { // MANGIARE IN DIAGONALE A SINISTRA
            moves.add(new Move(
                    new Position(this.getPosition().getRow() + BASE_PAWN_MOVE,
                            this.getPosition().getColumn() - BASE_PAWN_MOVE),
                    this));
        }
        if (this.getPosition().getColumn() != ChessBoard.LAST_COLUMN
                || this.getPosition().getRow() != ChessBoard.LAST_ROW) { // MANGIARE IN DIAGONALE A DESTRA
            moves.add(new Move(
                    new Position(this.getPosition().getRow() + BASE_PAWN_MOVE,
                            this.getPosition().getColumn() + BASE_PAWN_MOVE),
                    this));
        }
    }
}
