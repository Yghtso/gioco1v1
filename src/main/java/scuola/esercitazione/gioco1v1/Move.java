package scuola.esercitazione.gioco1v1;

import java.io.Serializable;

public class Move implements Serializable {

    private Position position;
    private Piece piece;
    private boolean surrender;
    private boolean startMatch;

    public Move(Position position, Piece piece, boolean surrender, boolean startMatch) {
        this.position = position;
        this.piece = piece;
        this.surrender = surrender;
        this.startMatch = startMatch;
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean getSurrender() {
        return this.surrender;
    }

    public boolean getStartMatch() {
        return this.startMatch;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setSurrender(boolean surrender) {
        this.surrender = surrender;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setStartMatch(boolean startMatch) {
        this.startMatch = startMatch;
    }

    public boolean equals(Move other) {
        if (other.getPosition() == null) {
            return false;
        }
        return this.position.getRow() == other.position.getRow()
                && this.position.getColumn() == other.position.getColumn();
    }

    public Move clone() {
        return new Move(position.clone(), piece.clone(), this.surrender, this.startMatch);
    }
}
