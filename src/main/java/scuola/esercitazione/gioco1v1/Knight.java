package scuola.esercitazione.gioco1v1;

public class Knight extends Piece {

    public void calculateMoves() {
        int row = this.position.getRow();
        int col = this.position.getColumn();

        // MOSSE IN AVANTI
        this.validMoves.add(new Move(new Position(row + 2, col + 1), this, false));
        this.validMoves.add(new Move(new Position(row + 2, col - 1), this, false));

        // MOSSE VERSO DESTRA
        this.validMoves.add(new Move(new Position(row + 1, col + 2), this, false));
        this.validMoves.add(new Move(new Position(row - 1, col + 2), this, false));

        // MOSSE VERSO SINISTRA
        this.validMoves.add(new Move(new Position(row + 1, col + 2), this, false));
    }

    public Knight(Position position, Player owner, int id) {
        super(position, owner, id);
    }
}
