package scuola.esercitazione.gioco1v1;

import java.util.ArrayList;

public class Rook extends Piece {

    private boolean firstMove;

    public ArrayList<Move> verticalMoves = new ArrayList<>();
    public ArrayList<Move> orizzontalMoves = new ArrayList<>();

    public void calculateMoves() {
        int row = this.getPosition().getRow();
        int col = this.getPosition().getColumn();

        for (int i = 1; i <= ChessBoard.ROWS; i++) {
            if (i != row) {
                this.verticalMoves.add(new Move(new Position(i, col), this));
                this.validMoves.add(new Move(new Position(i, col), this));
            }
        }

        for (int i = 1; i <= ChessBoard.COLUMNS; i++) {
            if (i != col) {
                this.orizzontalMoves.add(new Move(new Position(row, i), this));
                this.validMoves.add(new Move(new Position(row, i), this));
            }
        }
    }

    public Rook(Position position, Player owner, int id) {
        super(position, owner, id);
        this.firstMove = true;
    }

    public void moveTo(Position position) {
        super.moveTo(position);
        this.firstMove = false;
    }
}
