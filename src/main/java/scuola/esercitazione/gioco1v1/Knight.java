package scuola.esercitazione.gioco1v1;

import java.util.ArrayList;

public class Knight extends Piece {

    public final static int WHITE_KNIGHTS_STARTING_ROW = 1;
    public final static int WHITE_KNIGHT1_STARTING_COLUMN = 2;
    public final static int WHITE_KNIGHT2_STARTING_COLUMN = 7;

    public final static int BLACK_KNIGHTS_STARTING_ROW = 8;
    public final static int BLACK_KNIGHT1_STARTING_COLUMN = 2;
    public final static int BLACK_KNIGHT2_STARTING_COLUMN = 7;

    public void calculateMoves() {
        this.validMoves.clear();

        int row = this.position.getRow();
        int col = this.position.getColumn();

        // MOSSE IN AVANTI
        this.validMoves.add(new Move(new Position(row + 2, col + 1), this, false));
        this.validMoves.add(new Move(new Position(row + 2, col - 1), this, false));

        // MOSSE VERSO DESTRA
        this.validMoves.add(new Move(new Position(row + 1, col + 2), this, false));
        this.validMoves.add(new Move(new Position(row - 1, col + 2), this, false));

        // MOSSE VERSO SINISTRA
        this.validMoves.add(new Move(new Position(row + 1, col - 2), this, false));
        this.validMoves.add(new Move(new Position(row - 1, col - 2), this, false));

        // MOSSE IN BASSO
        this.validMoves.add(new Move(new Position(row - 2, col + 1), this, false));
        this.validMoves.add(new Move(new Position(row - 2, col - 1), this, false));

        @SuppressWarnings("unchecked")
        ArrayList<Move> validMovesTEMP = (ArrayList<Move>) this.validMoves.clone();

        for (Move move : validMovesTEMP) {
            if (!move.getPosition().isInBoard()) {
                validMoves.remove(move);
            }
        }
    }

    public Knight(Position position, Player owner, int id) {
        super(position, owner, id);
    }
}
