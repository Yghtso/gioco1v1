package scuola.esercitazione.gioco1v1;

import java.util.ArrayList;

public class Rook extends Piece {

    private boolean firstMove;

    public ArrayList<Move> TOP = new ArrayList<>();
    public ArrayList<Move> RIGHT = new ArrayList<>();
    public ArrayList<Move> BOTTOM = new ArrayList<>();
    public ArrayList<Move> LEFT = new ArrayList<>();



    public void calculateMoves() {

        this.validMoves.clear();
        this.TOP.clear();
        this.RIGHT.clear();
        this.BOTTOM.clear();
        this.LEFT.clear();
        
        int row = this.getPosition().getRow();
        int col = this.getPosition().getColumn();
        Position calculatedPosition = new Position(row + 1, col);
        
        while (calculatedPosition.isInBoard()) {
            validMoves.add(new Move(calculatedPosition.clone(), this , false));
            TOP.add(new Move(calculatedPosition.clone(), this , false));
            calculatedPosition.setRow(calculatedPosition.getRow() + 1);
        }

        calculatedPosition = new Position(row, col + 1);

        while (calculatedPosition.isInBoard()) {
            validMoves.add(new Move(calculatedPosition.clone(), this , false));
            RIGHT.add(new Move(calculatedPosition.clone(), this , false));
            calculatedPosition.setColumn(calculatedPosition.getColumn() + 1);
        }

        calculatedPosition = new Position(row - 1, col);

        while (calculatedPosition.isInBoard()) {
            validMoves.add(new Move(calculatedPosition.clone(), this , false));
            RIGHT.add(new Move(calculatedPosition.clone(), this , false));
            calculatedPosition.setRow(calculatedPosition.getColumn() - 1);
        }

        calculatedPosition = new Position(row, col - 1);

        while (calculatedPosition.isInBoard()) {
            validMoves.add(new Move(calculatedPosition.clone(), this , false));
            RIGHT.add(new Move(calculatedPosition.clone(), this , false));
            calculatedPosition.setColumn(calculatedPosition.getColumn() - 1);
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
