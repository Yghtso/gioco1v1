package scuola.esercitazione.gioco1v1;

import java.util.ArrayList;

public class Bishop extends Piece {

    public ArrayList<Move> mainDiagonal = new ArrayList<>();
    public ArrayList<Move> secondaryDiagonal = new ArrayList<>();

    public void calculateMoves() {

        int currentCol = this.getPosition().getColumn();
        int currentRow = this.getPosition().getRow();
    
        while (currentCol > 1 && currentRow > 1) {
            int row = currentRow;
            int col = currentCol;

            row--;
            col--;

            mainDiagonal.add(new Move(new Position(row, col), this));
        }
    }

    public Bishop(Position position, Player owner, int id) {
        super(position, owner, id);
    }
}