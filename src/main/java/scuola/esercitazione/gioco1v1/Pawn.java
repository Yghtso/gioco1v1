package scuola.esercitazione.gioco1v1;

import java.util.Vector;

public class Pawn extends Piece {

    private boolean firstMove;

    private final static int BASE_PAWN_MOVE = 1;
    private final static int STARTING_PAWN_MOVE = 2;

    private Vector<Move> possibleMoves = new Vector<Move>();

    public Move FORWARD_1 = new Move(null, this);
    public Move FORWARD_2 = new Move(null, this);
    public Move DIAGONAL_LEFT = new Move(null, this);
    public Move DIAGONAL_RIGHT = new Move(null, this);

    public Vector<Move> calculateMoves() {
        validMoves.clear();
        updateMoves();

        for(Move move : possibleMoves) {
            if(move.getPosition().isInBoard()) {
                validMoves.add(move);
            }
        }
        return this.validMoves;
    }

    public void moveTo(Position position) {
        this.position = position;
        firstMove = false;
    }

    public Pawn(Position position, Player owner, int id) {
        super(position, owner, id);
        this.firstMove = true;
    }

    public void updateMoves() {
        if (this.owner == Player.WHITE) {
            this.FORWARD_1.setPosition(new Position(this.position.getRow() + BASE_PAWN_MOVE, this.position.getColumn()));
            this.possibleMoves.add(FORWARD_1);
            if (firstMove) {
                this.FORWARD_2.setPosition(new Position(this.position.getRow() + STARTING_PAWN_MOVE, this.position.getColumn()));
                this.possibleMoves.add(FORWARD_2);
                this.firstMove = !this.firstMove;
            } else {
                this.FORWARD_2.setPosition(null);
            }
            this.DIAGONAL_LEFT.setPosition(new Position(this.position.getRow() + BASE_PAWN_MOVE, this.position.getColumn() - BASE_PAWN_MOVE));
            this.possibleMoves.add(DIAGONAL_LEFT);
            this.DIAGONAL_RIGHT.setPosition(new Position(this.position.getRow() + BASE_PAWN_MOVE, this.position.getColumn() + BASE_PAWN_MOVE));
            this.possibleMoves.add(DIAGONAL_RIGHT);
        }else {
            this.FORWARD_1.setPosition(new Position(this.position.getRow() - BASE_PAWN_MOVE, this.position.getColumn()));
            this.possibleMoves.add(FORWARD_1);
            if (firstMove) {
                this.FORWARD_2.setPosition(new Position(this.position.getRow() - STARTING_PAWN_MOVE, this.position.getColumn()));
                this.possibleMoves.add(FORWARD_2);
                this.firstMove = !this.firstMove;
            } else {
                this.FORWARD_2.setPosition(null);
            }
            this.DIAGONAL_LEFT.setPosition(new Position(this.position.getRow() - BASE_PAWN_MOVE, this.position.getColumn() + BASE_PAWN_MOVE));
            this.possibleMoves.add(DIAGONAL_LEFT);
            this.DIAGONAL_RIGHT.setPosition(new Position(this.position.getRow() - BASE_PAWN_MOVE, this.position.getColumn() - BASE_PAWN_MOVE));
            this.possibleMoves.add(DIAGONAL_RIGHT);
        }
    }

    public Piece clone() {
        return new Pawn(this.position.clone(), this.owner, this.id);
    }
}
