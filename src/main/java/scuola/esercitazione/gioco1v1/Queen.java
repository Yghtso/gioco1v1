package scuola.esercitazione.gioco1v1;

import java.util.ArrayList;
public class Queen extends Piece {

    public ArrayList<Move> TOP = new ArrayList<>();
    public ArrayList<Move> RIGHT = new ArrayList<>();
    public ArrayList<Move> BOTTOM = new ArrayList<>();
    public ArrayList<Move> LEFT = new ArrayList<>();
    public ArrayList<Move> TOPLEFTDIAGONAL = new ArrayList<>();
    public ArrayList<Move> TOPRIGHTDIAGONAL = new ArrayList<>();
    public ArrayList<Move> BOTTOMLEFTDIAGONAL = new ArrayList<>();
    public ArrayList<Move> BOTTOMRIGHTDIAGONAL = new ArrayList<>();
    
    public final static int WHITE_QUEEN_STARTING_ROW = 1;
    public final static int BLACK_QUEEN_STARTING_ROW = 8;

    public final static int WHITE_QUEEN_STARTING_COLUMN = 4;
    public final static int BLACK_QUEEN_STARTING_COLUMN = 4;
    
    public void calculateMoves() {

        this.validMoves.clear();
        this.TOP.clear();
        this.RIGHT.clear();
        this.BOTTOM.clear();
        this.LEFT.clear();
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
        
        // MOSSE SIMIL-TORRE
        calculatedPosition = new Position(currentRow + 1, currentCol);
        

        while (calculatedPosition.isInBoard()) {
            calculatedMove = new Move(calculatedPosition.clone(), this , false);

            validMoves.add(calculatedMove);
            TOP.add(calculatedMove);

            calculatedPosition.setRow(calculatedPosition.getRow() + 1);
        }

        calculatedPosition.setRow(currentRow);
        calculatedPosition.setColumn(currentCol + 1);

        while (calculatedPosition.isInBoard()) {
            calculatedMove = new Move(calculatedPosition.clone(), this , false);

            validMoves.add(calculatedMove);
            RIGHT.add(calculatedMove);

            calculatedPosition.setColumn(calculatedPosition.getColumn() + 1);
        }

        calculatedPosition.setRow(currentRow - 1);
        calculatedPosition.setColumn(currentCol);

        while (calculatedPosition.isInBoard()) {
            calculatedMove = new Move(calculatedPosition.clone(), this , false);

            validMoves.add(calculatedMove);
            BOTTOM.add(calculatedMove);

            calculatedPosition.setRow(calculatedPosition.getRow() - 1);
        }

        calculatedPosition.setRow(currentRow);
        calculatedPosition.setColumn(currentCol - 1);

        while (calculatedPosition.isInBoard()) {
            calculatedMove = new Move(calculatedPosition.clone(), this , false);
            
            validMoves.add(calculatedMove);
            LEFT.add(calculatedMove);

            calculatedPosition.setColumn(calculatedPosition.getColumn() - 1);
        }
    }

    public Queen(Position position, Player owner, int id) {
        super(position, owner, id);
    }

    public Move getFORWARD1() {return null;}
    public Move getFORWARD2() {return null;}
    public Move getDIAGONALLEFT() {return null;}
    public Move getDIAGONALRIGHT() {return null;}
    public ArrayList<Move> getTOP() {return this.TOP;}
    public ArrayList<Move> getRIGHT() {return this.RIGHT;}
    public ArrayList<Move> getBOTTOM() {return this.BOTTOM;}
    public ArrayList<Move> getLEFT() {return this.LEFT;}
    public ArrayList<Move> getTOPLEFTDIAGONAL() {return this.TOPLEFTDIAGONAL;}
    public ArrayList<Move> getTOPRIGHTDIAGONAL() {return this.TOPRIGHTDIAGONAL;}
    public ArrayList<Move> getBOTTOMLEFTDIAGONAL() {return this.BOTTOMLEFTDIAGONAL;}
    public ArrayList<Move> getBOTTOMRIGHTDIAGONAL() {return this.BOTTOMRIGHTDIAGONAL;}

    public Piece clone() {return new Queen(position.clone(), owner, id);}
}
