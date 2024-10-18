package scuola.esercitazione.gioco1v1;

import java.util.ArrayList;

public class Pawn extends Piece {

    private boolean firstMove;

    private final static int BASE_PAWN_MOVE = 1;
    private final static int STARTING_PAWN_MOVE = 2;

    public Move FORWARD_1 = new Move(null, this, false);
    public Move FORWARD_2 = new Move(null, this, false);
    public Move DIAGONAL_LEFT = new Move(null, this, false);
    public Move DIAGONAL_RIGHT = new Move(null, this, false);

    public void calculateMoves() {

        validMoves.clear();
        ArrayList<Move> allPossibleMoves = new ArrayList<Move>();
        
        if (this.owner == Player.WHITE) {
            this.FORWARD_1
                    .setPosition(new Position(this.position.getRow() + BASE_PAWN_MOVE, this.position.getColumn()));
            allPossibleMoves.add(FORWARD_1.clone());
            if (firstMove) {
                this.FORWARD_2.setPosition(
                        new Position(this.position.getRow() + STARTING_PAWN_MOVE, this.position.getColumn()));
                allPossibleMoves.add(FORWARD_2.clone());
                this.firstMove = !this.firstMove;
            } else {
                this.FORWARD_2.setPosition(null);
            }
            this.DIAGONAL_LEFT.setPosition(
                    new Position(this.position.getRow() + BASE_PAWN_MOVE, this.position.getColumn() - BASE_PAWN_MOVE));
            allPossibleMoves.add(DIAGONAL_LEFT.clone());
            this.DIAGONAL_RIGHT.setPosition(
                    new Position(this.position.getRow() + BASE_PAWN_MOVE, this.position.getColumn() + BASE_PAWN_MOVE));
            allPossibleMoves.add(DIAGONAL_RIGHT.clone());
        } else {
            this.FORWARD_1
                    .setPosition(new Position(this.position.getRow() - BASE_PAWN_MOVE, this.position.getColumn()));
            allPossibleMoves.add(FORWARD_1.clone());
            if (firstMove) {
                this.FORWARD_2.setPosition(
                        new Position(this.position.getRow() - STARTING_PAWN_MOVE, this.position.getColumn()));
                allPossibleMoves.add(FORWARD_2.clone());
                this.firstMove = !this.firstMove;
            } else {
                this.FORWARD_2.setPosition(null);
            }
            this.DIAGONAL_LEFT.setPosition(
                    new Position(this.position.getRow() - BASE_PAWN_MOVE, this.position.getColumn() + BASE_PAWN_MOVE));
            allPossibleMoves.add(DIAGONAL_LEFT.clone());
            this.DIAGONAL_RIGHT.setPosition(
                    new Position(this.position.getRow() - BASE_PAWN_MOVE, this.position.getColumn() - BASE_PAWN_MOVE));
            allPossibleMoves.add(DIAGONAL_RIGHT.clone());
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
}
