package scuola.esercitazione.gioco1v1;

import java.io.Serializable;

public class Move implements Serializable{

    private Position position;
    private Piece piece;

    public Move(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean equals(Move other) {
        return this.position.getRow() == other.position.getRow() && this.position.getColumn() == other.position.getColumn();
    }
}
