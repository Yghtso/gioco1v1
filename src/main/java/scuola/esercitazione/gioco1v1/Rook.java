package scuola.esercitazione.gioco1v1;

import java.util.ArrayList;

public class Rook extends Piece {

    private boolean firstMove;

    private ArrayList<Move> TOP = new ArrayList<>();
    private ArrayList<Move> RIGHT = new ArrayList<>();
    private ArrayList<Move> BOTTOM = new ArrayList<>();
    private ArrayList<Move> LEFT = new ArrayList<>();

    public final static int WHITE_ROOKS_STARTING_ROW = 1;
    public final static int WHITE_ROOK1_STARTING_COLUMN = 1;
    public final static int WHITE_ROOK2_STARTING_COLUMN = 8;

    public final static int BLACK_ROOKS_STARTING_ROW = 8;
    public final static int BLACK_ROOK1_STARTING_COLUMN = 1;
    public final static int BLACK_ROOK2_STARTING_COLUMN = 8;
    
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

            calculatedPosition.setRow(calculatedPosition.getRow() - 1);
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

    public Move getFORWARD1() {return null;}
    public Move getFORWARD2() {return null;}
    public Move getDIAGONALLEFT() {return null;}
    public Move getDIAGONALRIGHT() {return null;}
    public ArrayList<Move> getTOP() {return this.TOP;}
    public ArrayList<Move> getRIGHT() {return this.RIGHT;}
    public ArrayList<Move> getBOTTOM() {return this.BOTTOM;}
    public ArrayList<Move> getLEFT() {return this.LEFT;}
    public ArrayList<Move> getTOPLEFTDIAGONAL() {return null;}
    public ArrayList<Move> getTOPRIGHTDIAGONAL() {return null;}
    public ArrayList<Move> getBOTTOMLEFTDIAGONAL() {return null;}
    public ArrayList<Move> getBOTTOMRIGHTDIAGONAL() {return null;}

    public Piece clone() {return new Rook(position.clone(), owner, id);}
}
