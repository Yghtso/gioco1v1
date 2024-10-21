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
        Move calculatedMove;
        Position calculatedPosition = new Position(row + 1, col);
        

        while (calculatedPosition.isInBoard()) {
            calculatedMove = new Move(calculatedPosition.clone(), this , false);

            validMoves.add(calculatedMove);
            TOP.add(calculatedMove);

            calculatedPosition.setRow(calculatedPosition.getRow() + 1);
        }

        calculatedPosition.setRow(row);
        calculatedPosition.setColumn(col + 1);

        while (calculatedPosition.isInBoard()) {
            calculatedMove = new Move(calculatedPosition.clone(), this , false);

            validMoves.add(calculatedMove);
            RIGHT.add(calculatedMove);

            calculatedPosition.setColumn(calculatedPosition.getColumn() + 1);
        }

        calculatedPosition.setRow(row - 1);
        calculatedPosition.setColumn(col);

        while (calculatedPosition.isInBoard()) {
            calculatedMove = new Move(calculatedPosition.clone(), this , false);

            validMoves.add(calculatedMove);
            BOTTOM.add(calculatedMove);

            calculatedPosition.setRow(calculatedPosition.getColumn() - 1);
        }

        calculatedPosition.setRow(row);
        calculatedPosition.setColumn(col - 1);

        while (calculatedPosition.isInBoard()) {
            calculatedMove = new Move(calculatedPosition.clone(), this , false);
            
            validMoves.add(calculatedMove);
            LEFT.add(calculatedMove);

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
