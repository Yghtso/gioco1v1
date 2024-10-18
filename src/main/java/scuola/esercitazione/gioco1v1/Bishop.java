package scuola.esercitazione.gioco1v1;

import java.util.ArrayList;

public class Bishop extends Piece {

    public ArrayList<Move> mainDiagonal = new ArrayList<>();
    public ArrayList<Move> secondaryDiagonal = new ArrayList<>();

    public void calculateMoves() {

        int currentCol = this.getPosition().getColumn();
        int currentRow = this.getPosition().getRow();

        
    }

    public Bishop(Position position, Player owner, int id) {
        super(position, owner, id);
    }
}
