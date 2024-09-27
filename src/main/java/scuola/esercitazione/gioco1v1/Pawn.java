package scuola.esercitazione.gioco1v1;

import java.util.Vector;

public class Pawn extends Piece {

    public Vector<Move> calculateMoves() {
        return null;
    }

    public Pawn(Position position, Player owner, int id) {
        super(position, owner, id);
    }
}
