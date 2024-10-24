package scuola.esercitazione.gioco1v1;

import java.util.ArrayList;

public class Pawn extends Piece {

    private boolean firstMove;

    private final static int BASE_PAWN_MOVE = 1;
    private final static int STARTING_PAWN_MOVE = 2;

    public final static int WHITE_PAWNS_STARTING_ROW = 2;
    public final static int BLACK_PAWNS_STARTING_ROW = 7;


    public Move FORWARD1 = new Move(null, this, false);
    public Move FORWARD2 = new Move(null, this, false);
    public Move DIAGONALLEFT = new Move(null, this, false);
    public Move DIAGONALRIGHT = new Move(null, this, false);

    public void calculateMoves() {
        validMoves.clear();
        ArrayList<Move> allPossibleMoves = new ArrayList<Move>();

        if (this.owner == Player.WHITE) {
            this.FORWARD1
                    .setPosition(new Position(this.position.getRow() + BASE_PAWN_MOVE, this.position.getColumn()));
            allPossibleMoves.add(FORWARD1);
            if (firstMove) {
                this.FORWARD2.setPosition(
                        new Position(this.position.getRow() + STARTING_PAWN_MOVE, this.position.getColumn()));
                allPossibleMoves.add(FORWARD2);
                this.firstMove = !this.firstMove;
            } else {
                this.FORWARD2.setPosition(null);
            }
            this.DIAGONALLEFT.setPosition(
                    new Position(this.position.getRow() + BASE_PAWN_MOVE, this.position.getColumn() - BASE_PAWN_MOVE));
            allPossibleMoves.add(DIAGONALLEFT);
            this.DIAGONALRIGHT.setPosition(
                    new Position(this.position.getRow() + BASE_PAWN_MOVE, this.position.getColumn() + BASE_PAWN_MOVE));
            allPossibleMoves.add(DIAGONALRIGHT);
        } else {
            this.FORWARD1
                    .setPosition(new Position(this.position.getRow() - BASE_PAWN_MOVE, this.position.getColumn()));
            allPossibleMoves.add(FORWARD1);
            if (firstMove) {
                this.FORWARD2.setPosition(
                        new Position(this.position.getRow() - STARTING_PAWN_MOVE, this.position.getColumn()));
                allPossibleMoves.add(FORWARD2);
                this.firstMove = !this.firstMove;
            } else {
                this.FORWARD2.setPosition(null);
            }
            this.DIAGONALLEFT.setPosition(
                    new Position(this.position.getRow() - BASE_PAWN_MOVE, this.position.getColumn() + BASE_PAWN_MOVE));
            allPossibleMoves.add(DIAGONALLEFT);
            this.DIAGONALRIGHT.setPosition(
                    new Position(this.position.getRow() - BASE_PAWN_MOVE, this.position.getColumn() - BASE_PAWN_MOVE));
            allPossibleMoves.add(DIAGONALRIGHT);
        }

        for (Move move : allPossibleMoves) {
            if (move.getPosition().isInBoard()) {
                validMoves.add(move);
            }
        }
    }

    public Pawn(Position position, Player owner, int id) {
        super(position, owner, id);
        this.firstMove = true;
    }

    public void moveTo(Position position) {
        super.moveTo(position);
        this.firstMove = false;
    }

    public Move getFORWARD1() {return this.FORWARD1;}
    public Move getFORWARD2() {return this.FORWARD2;}
    public Move getDIAGONALLEFT() {return this.DIAGONALLEFT;}
    public Move getDIAGONALRIGHT() {return this.DIAGONALRIGHT;}
    public ArrayList<Move> getTOP() {return null;}
    public ArrayList<Move> getRIGHT() {return null;}
    public ArrayList<Move> getBOTTOM() {return null;}
    public ArrayList<Move> getLEFT() {return null;}
    public ArrayList<Move> getTOPLEFTDIAGONAL() {return null;}
    public ArrayList<Move> getTOPRIGHTDIAGONAL() {return null;}
    public ArrayList<Move> getBOTTOMLEFTDIAGONAL() {return null;}
    public ArrayList<Move> getBOTTOMRIGHTDIAGONAL() {return null;}

    public Piece clone() {return new Pawn(position.clone(), owner, id);}
}
