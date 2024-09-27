package scuola.esercitazione.gioco1v1;

import java.util.Vector;

public abstract class Piece {

    private Position position;
    private Player owner;
    private int id;

    public abstract Vector<Move> calculateMoves();

    public Piece(Position position, Player owner, int id) {
        this.position = position;
        this.owner = owner;
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public Player getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
