package scuola.esercitazione.gioco1v1;

import java.util.ArrayList;

public class King extends Piece {

    public void calculateMoves() {
        this.validMoves.clear();
        validMoves.add(new Move(new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn() - 1), this, false));
        validMoves.add(new Move(new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn()), this, false));
        validMoves.add(new Move(new Position(this.getPosition().getRow() + 1, this.getPosition().getColumn() + 1), this, false));
        validMoves.add(new Move(new Position(this.getPosition().getRow(), this.getPosition().getColumn() - 1), this, false));
        validMoves.add(new Move(new Position(this.getPosition().getRow(), this.getPosition().getColumn() + 1), this, false));
        validMoves.add(new Move(new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn() - 1), this, false));
        validMoves.add(new Move(new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn()), this, false));
        validMoves.add(new Move(new Position(this.getPosition().getRow() - 1, this.getPosition().getColumn() + 1), this, false));

        @SuppressWarnings("unchecked")
        ArrayList<Move> validMovesClone = (ArrayList<Move>) this.validMoves.clone();
        for (Move singleMove : validMovesClone) {
            if (!singleMove.getPosition().isInBoard()) {
                this.validMoves.remove(singleMove);
            }
        }
    }

    public King(Position position, Player owner, int id) {
        super(position, owner, id);
    }

    public Move getFORWARD1() {return null;}
    public Move getFORWARD2() {return null;}
    public Move getDIAGONALLEFT() {return null;}
    public Move getDIAGONALRIGHT() {return null;}
    public ArrayList<Move> getTOP() {return null;}
    public ArrayList<Move> getRIGHT() {return null;}
    public ArrayList<Move> getBOTTOM() {return null;}
    public ArrayList<Move> getLEFT() {return null;}
    public ArrayList<Move> getTOPLEFTDIAGONAL() {return null;}
    public ArrayList<Move> getTOPRIGHTDIAGONAL() {return null;}
    public ArrayList<Move> getBOTTOMLEFTDIAGONAL() {return null;}
    public ArrayList<Move> getBOTTOMRIGHTDIAGONAL() {return null;}

    public Piece clone() {return new Knight(position.clone(), owner, id);}
}
