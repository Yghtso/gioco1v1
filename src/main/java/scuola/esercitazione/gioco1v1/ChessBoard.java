package scuola.esercitazione.gioco1v1;

import java.util.ArrayList;

public class ChessBoard {

    public final static int ROWS = 8;
    public final static int COLUMNS = 8;
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
            pieces[Pawn.WHITE_PAWNS_STARTING_ROW - 1][i - 1] = new Pawn(new Position(Pawn.WHITE_PAWNS_STARTING_ROW, i),
            Player.WHITE,
            getFirstAvailableId());
        }

        pieces[Rook.WHITE_ROOKS_STARTING_ROW - 1][Rook.WHITE_ROOK1_STARTING_COLUMN - 1] = new Rook(new Position(Rook.WHITE_ROOKS_STARTING_ROW, Rook.WHITE_ROOK1_STARTING_COLUMN), Player.WHITE,
                getFirstAvailableId());
        pieces[Rook.WHITE_ROOKS_STARTING_ROW - 1][Rook.WHITE_ROOK2_STARTING_COLUMN - 1] = new Rook(new Position(Rook.WHITE_ROOKS_STARTING_ROW, Rook.WHITE_ROOK2_STARTING_COLUMN), Player.WHITE,
                getFirstAvailableId());

        pieces[Knight.WHITE_KNIGHTS_STARTING_ROW - 1][Knight.WHITE_KNIGHT1_STARTING_COLUMN - 1] = new Knight(new Position(Knight.WHITE_KNIGHTS_STARTING_ROW, Knight.WHITE_KNIGHT1_STARTING_COLUMN), Player.WHITE, getFirstAvailableId());
        pieces[Knight.WHITE_KNIGHTS_STARTING_ROW - 1][Knight.WHITE_KNIGHT2_STARTING_COLUMN - 1] = new Knight(new Position(Knight.WHITE_KNIGHTS_STARTING_ROW, Knight.WHITE_KNIGHT2_STARTING_COLUMN), Player.WHITE, getFirstAvailableId());

        pieces[Bishop.WHITE_BISHOPS_STARTING_ROW - 1][Bishop.WHITE_BISHOP1_STARTING_COLUMN - 1] = new Bishop(new Position(Bishop.WHITE_BISHOPS_STARTING_ROW, Bishop.WHITE_BISHOP1_STARTING_COLUMN), Player.WHITE, getFirstAvailableId());
        pieces[Bishop.WHITE_BISHOPS_STARTING_ROW - 1][Bishop.WHITE_BISHOP2_STARTING_COLUMN - 1] = new Bishop(new Position(Bishop.WHITE_BISHOPS_STARTING_ROW, Bishop.WHITE_BISHOP2_STARTING_COLUMN), Player.WHITE, getFirstAvailableId());

        pieces[Queen.WHITE_QUEEN_STARTING_ROW - 1][Queen.WHITE_QUEEN_STARTING_COLUMN - 1] = new Queen(new Position(Queen.WHITE_QUEEN_STARTING_ROW, Queen.WHITE_QUEEN_STARTING_COLUMN), Player.WHITE, getFirstAvailableId());

        pieces[FIRST_ROW - 1][5 - 1] = new King(new Position(FIRST_ROW, 5), Player.WHITE, getFirstAvailableId());

        // PEDINE NERE
        for (int i = 1; i <= COLUMNS; i++) {
            pieces[Pawn.BLACK_PAWNS_STARTING_ROW - 1][i - 1] = new Pawn(new Position(Pawn.BLACK_PAWNS_STARTING_ROW, i),
                    Player.BLACK, getFirstAvailableId());
        }

        pieces[Rook.BLACK_ROOKS_STARTING_ROW - 1][Rook.BLACK_ROOK1_STARTING_COLUMN - 1] = new Rook(new Position(Rook.BLACK_ROOKS_STARTING_ROW, Rook.BLACK_ROOK1_STARTING_COLUMN), Player.BLACK,
                getFirstAvailableId());
        pieces[Rook.BLACK_ROOKS_STARTING_ROW - 1][Rook.BLACK_ROOK2_STARTING_COLUMN - 1] = new Rook(new Position(Rook.BLACK_ROOKS_STARTING_ROW, Rook.BLACK_ROOK1_STARTING_COLUMN), Player.BLACK,
                getFirstAvailableId());

        pieces[Knight.BLACK_KNIGHTS_STARTING_ROW - 1][Knight.BLACK_KNIGHT1_STARTING_COLUMN - 1] = new Knight(new Position(Knight.BLACK_KNIGHTS_STARTING_ROW, Knight.BLACK_KNIGHT1_STARTING_COLUMN), Player.BLACK, getFirstAvailableId());
        pieces[Knight.BLACK_KNIGHTS_STARTING_ROW - 1][Knight.BLACK_KNIGHT2_STARTING_COLUMN - 1] = new Knight(new Position(Knight.BLACK_KNIGHTS_STARTING_ROW, Knight.BLACK_KNIGHT2_STARTING_COLUMN), Player.BLACK, getFirstAvailableId());

        pieces[Bishop.BLACK_BISHOPS_STARTING_ROW - 1][Bishop.BLACK_BISHOP1_STARTING_COLUMN - 1] = new Bishop(new Position(Bishop.BLACK_BISHOPS_STARTING_ROW, Bishop.BLACK_BISHOP1_STARTING_COLUMN), Player.BLACK, getFirstAvailableId());
        pieces[Bishop.BLACK_BISHOPS_STARTING_ROW - 1][Bishop.BLACK_BISHOP2_STARTING_COLUMN - 1] = new Bishop(new Position(Bishop.BLACK_BISHOPS_STARTING_ROW, Bishop.BLACK_BISHOP2_STARTING_COLUMN), Player.BLACK, getFirstAvailableId());

        pieces[Queen.BLACK_QUEEN_STARTING_ROW - 1][Queen.BLACK_QUEEN_STARTING_COLUMN - 1] = new Queen(new Position(Queen.BLACK_QUEEN_STARTING_ROW, Queen.BLACK_QUEEN_STARTING_COLUMN), Player.BLACK, getFirstAvailableId());

        pieces[LAST_ROW - 1][5 - 1] = new King(new Position(LAST_ROW, 5), Player.BLACK, getFirstAvailableId());
    }

    public ChessBoard(boolean [] ids, Piece[][] pieces) {
        this.pieces = new Piece[ROWS][COLUMNS];
        this.ids = new boolean[LAST_ID];

        for (int i = 0; i < LAST_ID; i++) {
            this.ids[i] = ids[i];
        }

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces.length; j++) {
                this.pieces[i][j] = this.pieces[i][j];
            }
        }
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

    public ChessBoard clone() {
        return new ChessBoard(this.ids, this.pieces);
    }
}
