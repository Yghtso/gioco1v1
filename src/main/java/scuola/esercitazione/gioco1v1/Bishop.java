package scuola.esercitazione.gioco1v1;

import java.util.ArrayList;

public class Bishop extends Piece {

    public ArrayList<Move> TOPLEFTDIAGONAL = new ArrayList<>();
    public ArrayList<Move> TOPRIGHTDIAGONAL = new ArrayList<>();
    public ArrayList<Move> BOTTOMLEFTDIAGONAL = new ArrayList<>();
    public ArrayList<Move> BOTTOMRIGHTDIAGONAL = new ArrayList<>();


    public void calculateMoves() {

        this.validMoves.clear();
        this.TOPLEFTDIAGONAL.clear();
        this.BOTTOMRIGHTDIAGONAL.clear();
        this.BOTTOMLEFTDIAGONAL.clear();
        this.TOPRIGHTDIAGONAL.clear();

        int currentCol = this.getPosition().getColumn();
        int currentRow = this.getPosition().getRow();

        Position calculatedPosition = new Position(currentRow + 1, currentCol - 1);
        Move calculatedMove = new Move(null, this, false);

        // DIAGONALE PRINCIPALE
        while (calculatedPosition.isInBoard()) {
            calculatedMove = new Move(calculatedPosition.clone(), this, false);

            this.validMoves.add(calculatedMove);
            this.TOPLEFTDIAGONAL.add(calculatedMove);

            calculatedPosition.setRow(calculatedPosition.getRow() + 1);
            calculatedPosition.setColumn(calculatedPosition.getColumn() - 1);
        }

        calculatedPosition = new Position(currentRow - 1, currentCol + 1);

        while (calculatedPosition.isInBoard()) {
            calculatedMove = new Move(calculatedPosition.clone(), this, false);

            this.validMoves.add(calculatedMove);
            this.BOTTOMRIGHTDIAGONAL.add(calculatedMove);

            calculatedPosition.setRow(calculatedPosition.getRow() - 1);
            calculatedPosition.setColumn(calculatedPosition.getColumn() + 1);
        }
        
        calculatedPosition = new Position(currentRow - 1, currentCol - 1);

        // DIAGONALE SECONDARIA
        while (calculatedPosition.isInBoard()) {
            calculatedMove = new Move(calculatedPosition.clone(), this, false);

            this.validMoves.add(calculatedMove);
            this.BOTTOMLEFTDIAGONAL.add(calculatedMove);

            calculatedPosition.setRow(calculatedPosition.getRow() - 1);
            calculatedPosition.setColumn(calculatedPosition.getColumn() - 1);
        }

        calculatedPosition = new Position(currentRow + 1, currentCol + 1);

        while (calculatedPosition.isInBoard()) {
            calculatedMove = new Move(calculatedPosition.clone(), this, false);

            this.validMoves.add(calculatedMove);
            this.TOPRIGHTDIAGONAL.add(calculatedMove);

            calculatedPosition.setRow(calculatedPosition.getRow() + 1);
            calculatedPosition.setColumn(calculatedPosition.getColumn() + 1);
        }
    }

    public Bishop(Position position, Player owner, int id) {
        super(position, owner, id);
    }
}
