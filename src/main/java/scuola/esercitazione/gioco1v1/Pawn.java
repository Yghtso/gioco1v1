package scuola.esercitazione.gioco1v1;

import java.util.Vector;

public class Pawn extends Piece {

    private boolean firstMove;

    public Vector<Move> calculateMoves() {
        Vector<Move> moves = new Vector<Move>();

        if (this.getPosition().getRow() != 8) {
            if (firstMove) {
                moves.add(new Move(new Position(this.getPosition().getRow() + 2, this.getPosition().getColumn()), this));
                firstMove = false;
            } else {
                moves.add(new Move(new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn()), this));
            }
        }

        if (this.getPosition().getColumn() != 1) {
            moves.add(new Move(new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn() - 1), this));
        }

        if (this.getPosition().getColumn() != 8) {
            moves.add(new Move(new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn() + 1), this));
        }

        return moves;
    }

    public Pawn(Position position, Player owner, int id) {
        super(position, owner, id);
        this.firstMove = true;
    }
}
