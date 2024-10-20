package scuola.esercitazione.gioco1v1;

import java.util.ArrayList;

public class ChessBoard {

    public final static int ROWS = 8;
    public final static int COLUMNS = 8;
    public final static int WHITE_PAWNS_STARTING_ROW = 2;
    public final static int BLACK_PAWNS_STARTING_ROW = 7;
    public final static int FIRST_ROW = 1;
    public final static int FIRST_COLUMN = 1;
    public final static int LAST_ROW = ROWS;
    public final static int LAST_COLUMN = COLUMNS;
    public static final int LAST_ID = 64;

    private boolean[] ids;
    private Piece[][] pieces;

    public ChessBoard() {
        this.pieces = new Piece[ROWS][COLUMNS];
        this.ids = new boolean[LAST_ID];

        // PEINE BIANCHE
        for (int i = 1; i <= COLUMNS; i++) {            
            pieces[WHITE_PAWNS_STARTING_ROW - 1][i - 1] = new Pawn(new Position(WHITE_PAWNS_STARTING_ROW, i),
            Player.WHITE,
            getFirstAvailableId());
        }

        pieces[FIRST_ROW - 1][FIRST_COLUMN - 1] = new Rook(new Position(FIRST_ROW, FIRST_COLUMN), Player.WHITE,
                getFirstAvailableId());
        pieces[FIRST_ROW - 1][LAST_COLUMN - 1] = new Rook(new Position(FIRST_ROW, LAST_COLUMN), Player.WHITE,
                getFirstAvailableId());

        pieces[FIRST_ROW - 1][1] = new Knight(new Position(FIRST_ROW, 2), Player.WHITE, getFirstAvailableId());
        pieces[FIRST_ROW - 1][6] = new Knight(new Position(FIRST_ROW, 7), Player.WHITE, getFirstAvailableId());

        pieces[FIRST_ROW - 1][2] = new Bishop(new Position(FIRST_ROW, 3), Player.WHITE, getFirstAvailableId());
        pieces[FIRST_ROW - 1][5] = new Bishop(new Position(FIRST_ROW, 6), Player.WHITE, getFirstAvailableId());

        pieces[FIRST_ROW - 1][3] = new Queen(new Position(FIRST_ROW, 4), Player.WHITE, getFirstAvailableId());

        pieces[FIRST_ROW - 1][4] = new King(new Position(FIRST_ROW, 5), Player.WHITE, getFirstAvailableId());

        // PEDINE NEGRE
        for (int i = 1; i <= COLUMNS; i++) {
            pieces[BLACK_PAWNS_STARTING_ROW - 1][i - 1] = new Pawn(new Position(BLACK_PAWNS_STARTING_ROW, i),
                    Player.BLACK, getFirstAvailableId());
        }

        pieces[LAST_ROW - 1][FIRST_COLUMN - 1] = new Rook(new Position(LAST_ROW, FIRST_COLUMN), Player.BLACK,
                getFirstAvailableId());
        pieces[LAST_ROW - 1][LAST_COLUMN - 1] = new Rook(new Position(LAST_ROW, LAST_COLUMN), Player.BLACK,
                getFirstAvailableId());

        pieces[LAST_ROW - 1][1] = new Knight(new Position(LAST_ROW, 2), Player.BLACK, getFirstAvailableId());
        pieces[LAST_ROW - 1][6] = new Knight(new Position(LAST_ROW, 7), Player.BLACK, getFirstAvailableId());

        pieces[LAST_ROW - 1][2] = new Bishop(new Position(LAST_ROW, 3), Player.BLACK, getFirstAvailableId());
        pieces[LAST_ROW - 1][5] = new Bishop(new Position(LAST_ROW, 6), Player.BLACK, getFirstAvailableId());

        pieces[LAST_ROW - 1][3] = new Queen(new Position(LAST_ROW, 4), Player.BLACK, getFirstAvailableId());

        pieces[LAST_ROW - 1][4] = new King(new Position(LAST_ROW, 5), Player.BLACK, getFirstAvailableId());
    }

    public void createPiece(Piece piece) {
        piece.setId(getFirstAvailableId());
        this.pieces[piece.getPosition().getRow() - 1][piece.getPosition().getColumn() - 1] = piece;
    }

    public void destroyPiece(Piece piece) {
        destroiedId(piece.id);
        this.pieces[piece.getPosition().getRow() - 1][piece.getPosition().getColumn() - 1] = null;
    }

    private int getFirstAvailableId() {
        for (int i = 0; i < ids.length; i++) {
            if (!ids[i]) {
                ids[i] = true;
                return i + 1;
            }
        }
        return 0;
    }

    private void destroiedId(int id) {
        this.ids[id - 1] = false;
    }

    public Piece getPiece(Position position) {
        if(position == null) {
            return null;
        } else if (position.isInBoard()) {
            return pieces[position.getRow() - 1][position.getColumn() - 1];
        } else {
            return null;
        }
    }

    public void applyMove(Move move) {
        Position precPosition = move.getPiece().getPosition();
        this.pieces[precPosition.getRow() - 1][precPosition.getColumn() - 1] = null;
        move.getPiece().moveTo(move.getPosition());
        this.pieces[move.getPosition().getRow() - 1][move.getPosition().getColumn() - 1] = move.getPiece();
    }

    public ArrayList<Piece> getPieces() {
        ArrayList<Piece> returnedPieces = new ArrayList<>();
        for (Piece[] row : pieces) {
            for (Piece piece : row) {
                if (piece != null) {
                    returnedPieces.add(piece);
                }
            }
        }
        return  returnedPieces;
    }
}
