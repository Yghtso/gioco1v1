package scuola.esercitazione.gioco1v1;

import java.util.Vector;

public abstract class Piece {

    protected Position position;
    protected Player owner;
    protected int id;
    protected Vector<Move> validMoves;

    public abstract Vector<Move> calculateMoves();

    public Piece(Position position, Player owner, int id) {
        this.position = position;
        this.owner = owner;
        this.id = id;
        this.validMoves = new Vector<Move>();
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

    public void setId(int id) {
        this.id = id;
    }
}
