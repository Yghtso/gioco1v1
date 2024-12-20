package scuola.esercitazione.gioco1v1;

import java.util.ArrayList;

public class Knight extends Piece {

    public final static int WHITE_KNIGHTS_STARTING_ROW = 1;
    public final static int WHITE_LEFTKNIGHT_STARTING_COLUMN = 2;
    public final static int WHITE_RIGHTKNIGHT_STARTING_COLUMN = 7;

    public final static int BLACK_KNIGHTS_STARTING_ROW = 8;
    public final static int BLACK_LEFTKNIGHT_STARTING_COLUMN = 2;
    public final static int BLACK_RIGHTKNIGHT_STARTING_COLUMN = 7;

    public void calculateMoves() {
        this.validMoves.clear();

        int row = this.position.getRow();
        int col = this.position.getColumn();

        // MOSSE IN AVANTI
        this.validMoves.add(new Move(new Position(row + 2, col + 1), this, false, false));
        this.validMoves.add(new Move(new Position(row + 2, col - 1), this, false, false));

        // MOSSE VERSO DESTRA
        this.validMoves.add(new Move(new Position(row + 1, col + 2), this, false, false));
        this.validMoves.add(new Move(new Position(row - 1, col + 2), this, false, false));

        // MOSSE VERSO SINISTRA
        this.validMoves.add(new Move(new Position(row + 1, col - 2), this, false, false));
        this.validMoves.add(new Move(new Position(row - 1, col - 2), this, false, false));

        // MOSSE IN BASSO
        this.validMoves.add(new Move(new Position(row - 2, col + 1), this, false, false));
        this.validMoves.add(new Move(new Position(row - 2, col - 1), this, false, false));

        @SuppressWarnings("unchecked")
        ArrayList<Move> validMovesTEMP = (ArrayList<Move>) this.validMoves.clone();

        for (Move move : validMovesTEMP) {
            if (!move.getPosition().isInBoard()) {
                validMoves.remove(move);
            }
        }
    }

    public Knight(Position position, Player owner, int id) {
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

    public Piece clone() {return new King(position.clone(), owner, id);}
}
