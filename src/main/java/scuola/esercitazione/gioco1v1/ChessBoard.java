package scuola.esercitazione.gioco1v1;

public class ChessBoard {

    public static int ROWS = 8;
    public static int COLUMNS = 8;

    private Piece[][] pieces;

    public ChessBoard() {
        this.pieces = new Piece[ROWS][COLUMNS];

        // POPOLO LA TABELLA CON LE PEDINE

        for (int i = 0; i < COLUMNS; i++) {
            pieces[1][i] = new Pawn(new Position(1, i), Player.WHITE, i);
        }

        pieces[0][0] = new Rook(new Position(0, 0), Player.WHITE, 0);
        pieces[0][7] = new Rook(new Position(0, 7), Player.WHITE, 1);

        pieces[0][1] = new Knight(new Position(0, 1), Player.WHITE, 0);
        pieces[0][6] = new Knight(new Position(0, 6), Player.WHITE, 1);

        pieces[0][2] = new Bishop(new Position(0, 2), Player.WHITE, 0);
        pieces[0][5] = new Bishop(new Position(0, 5), Player.WHITE, 1);

        pieces[0][3] = new Queen(new Position(0, 3), Player.WHITE, 0);

        pieces[0][4] = new King(new Position(0, 4), Player.WHITE, 0);

        // PEDINE NEGRE
        for (int i = 0; i < COLUMNS; i++) {
            pieces[6][i] = new Pawn(new Position(6, i), Player.BLACK, i);
        }

        pieces[7][0] = new Rook(new Position(7, 0), Player.BLACK, 0);
        pieces[7][7] = new Rook(new Position(7, 7), Player.BLACK, 1);

        pieces[7][1] = new Knight(new Position(7, 1), Player.BLACK, 0);
        pieces[7][6] = new Knight(new Position(7, 6), Player.BLACK, 1);

        pieces[7][2] = new Bishop(new Position(7, 2), Player.BLACK, 0);
        pieces[7][5] = new Bishop(new Position(7, 5), Player.BLACK, 1);

        pieces[7][3] = new Queen(new Position(7, 3), Player.BLACK, 0);

        pieces[7][4] = new King(new Position(7, 4), Player.BLACK, 0);
    }

    public Piece getPiece(Position position) {
        return pieces[position.getRow()][position.getColumns()];
    }
}
