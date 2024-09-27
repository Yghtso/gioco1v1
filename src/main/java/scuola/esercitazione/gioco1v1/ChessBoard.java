package scuola.esercitazione.gioco1v1;

public class ChessBoard {

    public static int ROWS = 8;
    public static int COLUMNS = 8;
    public static int PAWNS_STARTING_ROW = 2;

    private Piece selectedPiece;
    private Piece[][] pieces;

    public ChessBoard() {
        this.selectedPiece = null;
        this.pieces = new Piece[ROWS][COLUMNS];

        // POPOLO LA TABELLA CON LE PEDINE

        for (int i = 1; i < COLUMNS; i++) {
            pieces[PAWNS_STARTING_ROW - 1][i - 1] = new Pawn(new Position(PAWNS_STARTING_ROW, i), Player.WHITE, i);
        }

        pieces[0][0] = new Rook(new Position(1, 1), Player.WHITE, 0);
        pieces[0][7] = new Rook(new Position(1, 8), Player.WHITE, 1);

        pieces[0][1] = new Knight(new Position(1, 2), Player.WHITE, 0);
        pieces[0][6] = new Knight(new Position(1, 7), Player.WHITE, 1);

        pieces[0][2] = new Bishop(new Position(1, 3), Player.WHITE, 0);
        pieces[0][5] = new Bishop(new Position(1, 6), Player.WHITE, 1);

        pieces[0][3] = new Queen(new Position(1, 4), Player.WHITE, 0);

        pieces[0][4] = new King(new Position(1, 5), Player.WHITE, 0);

        // PEDINE NEGRE
        for (int i = 1; i < COLUMNS; i++) {
            pieces[ROWS - PAWNS_STARTING_ROW][i - 1] = new Pawn(new Position(ROWS - PAWNS_STARTING_ROW + 1, i), Player.BLACK, i);
        }

        pieces[7][0] = new Rook(new Position(8, 1), Player.BLACK, 0);
        pieces[7][7] = new Rook(new Position(8, 8), Player.BLACK, 1);

        pieces[7][1] = new Knight(new Position(8, 2), Player.BLACK, 0);
        pieces[7][6] = new Knight(new Position(8, 7), Player.BLACK, 1);

        pieces[7][2] = new Bishop(new Position(8, 3), Player.BLACK, 0);
        pieces[7][5] = new Bishop(new Position(8, 6), Player.BLACK, 1);

        pieces[7][3] = new Queen(new Position(8, 4), Player.BLACK, 0);

        pieces[7][4] = new King(new Position(8, 5), Player.BLACK, 0);
    }

    public Piece getPiece(Position position) {
        return pieces[position.getRow() - 1][position.getColumn() - 1];
    }
}
