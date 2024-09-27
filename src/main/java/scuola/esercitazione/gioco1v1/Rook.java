package scuola.esercitazione.gioco1v1;

import java.util.Vector;

public class Rook extends Piece {

    private boolean firstMove;

    public Vector<Move> calculateMoves() {
        return null;
    }

    public Rook(Position position, Player owner, int id) {
        super(position, owner, id);
        this.firstMove = false;
    }
}
