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
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;

    private Move simulatedMove;
    private boolean simulationMode;

    public ChessBoard() {
        this.pieces = new Piece[ROWS][COLUMNS];
        this.ids = new boolean[LAST_ID];
        this.whitePieces = new ArrayList<>();
        this.blackPieces = new ArrayList<>();
        this.simulatedMove = null;
        this.simulationMode = false;

        // PEDINE BIANCHE

        // PEDONI BIANCHI
        for (int i = 1; i <= COLUMNS; i++) {
            Pawn whitePawn = new Pawn(new Position(Pawn.WHITE_PAWNS_STARTING_ROW, i),
                    Player.WHITE,
                    getFirstAvailableId());

            pieces[Pawn.WHITE_PAWNS_STARTING_ROW - 1][i - 1] = whitePawn;
            whitePieces.add(whitePawn);
        }

        // PRIMA TORRE BIANCA
        Rook whiteRookLeft = new Rook(
                new Position(Rook.WHITE_ROOKS_STARTING_ROW, Rook.WHITE_LEFTROOK_STARTING_COLUMN), Player.WHITE,
                getFirstAvailableId());

        pieces[Rook.WHITE_ROOKS_STARTING_ROW - 1][Rook.WHITE_LEFTROOK_STARTING_COLUMN
                - 1] = whiteRookLeft;
        whitePieces.add(whiteRookLeft);

        // SECONDA TORRE BIANCA
        Rook whiteRookRight = new Rook(
                new Position(Rook.WHITE_ROOKS_STARTING_ROW, Rook.WHITE_RIGHTROOK_STARTING_COLUMN), Player.WHITE,
                getFirstAvailableId());
        pieces[Rook.WHITE_ROOKS_STARTING_ROW - 1][Rook.WHITE_RIGHTROOK_STARTING_COLUMN
                - 1] = whiteRookRight;
        whitePieces.add(whiteRookRight);

        // PRIMO CAVALLO BIANCO
        Knight whiteKnightLeft = new Knight(
                new Position(Knight.WHITE_KNIGHTS_STARTING_ROW, Knight.WHITE_LEFTKNIGHT_STARTING_COLUMN),
                Player.WHITE,
                getFirstAvailableId());
        pieces[Knight.WHITE_KNIGHTS_STARTING_ROW - 1][Knight.WHITE_LEFTKNIGHT_STARTING_COLUMN
                - 1] = whiteKnightLeft;
        whitePieces.add(whiteKnightLeft);

        // SECONDO CAVALLO BIANCO
        Knight whiteKnightRight = new Knight(
                new Position(Knight.WHITE_KNIGHTS_STARTING_ROW, Knight.WHITE_RIGHTKNIGHT_STARTING_COLUMN),
                Player.WHITE,
                getFirstAvailableId());
        pieces[Knight.WHITE_KNIGHTS_STARTING_ROW - 1][Knight.WHITE_RIGHTKNIGHT_STARTING_COLUMN
                - 1] = whiteKnightRight;
        whitePieces.add(whiteKnightRight);

        // PRIMO ALFIERE BIANCO
        Bishop whiteBishopLeft = new Bishop(
                new Position(Bishop.WHITE_BISHOPS_STARTING_ROW, Bishop.WHITE_LEFTBISHOP_STARTING_COLUMN),
                Player.WHITE,
                getFirstAvailableId());
        pieces[Bishop.WHITE_BISHOPS_STARTING_ROW - 1][Bishop.WHITE_LEFTBISHOP_STARTING_COLUMN
                - 1] = whiteBishopLeft;
        whitePieces.add(whiteBishopLeft);

        // SECONDO ALFIERE BIANCO
        Bishop whiteBishopRight = new Bishop(
                new Position(Bishop.WHITE_BISHOPS_STARTING_ROW, Bishop.WHITE_RIGHTBISHOP_STARTING_COLUMN),
                Player.WHITE,
                getFirstAvailableId());
        pieces[Bishop.WHITE_BISHOPS_STARTING_ROW - 1][Bishop.WHITE_RIGHTBISHOP_STARTING_COLUMN
                - 1] = whiteBishopRight;
        whitePieces.add(whiteBishopRight);

        // REGINA BIANCA
        Queen whiteQueen = new Queen(
                new Position(Queen.WHITE_QUEEN_STARTING_ROW, Queen.WHITE_QUEEN_STARTING_COLUMN),
                Player.WHITE,
                getFirstAvailableId());
        pieces[Queen.WHITE_QUEEN_STARTING_ROW - 1][Queen.WHITE_QUEEN_STARTING_COLUMN - 1] = whiteQueen;
        whitePieces.add(whiteQueen);

        // RE BIANCO
        King whiteKing = new King(
                new Position(King.WHITE_KING_STARTING_ROW, King.WHITE_KING_STARTING_COLUMN),
                Player.WHITE,
                getFirstAvailableId());
        pieces[King.WHITE_KING_STARTING_ROW - 1][King.WHITE_KING_STARTING_COLUMN - 1] = whiteKing;

        // PEDINE NERE

        // PEDONI NERI
        for (int i = 1; i <= COLUMNS; i++) {
            Pawn blackPawn = new Pawn(new Position(Pawn.BLACK_PAWNS_STARTING_ROW, i),
                    Player.BLACK,
                    getFirstAvailableId());
            pieces[Pawn.WHITE_PAWNS_STARTING_ROW - 1][i - 1] = blackPawn;
            whitePieces.add(blackPawn);
        }

        // PRIMA TORRE NERA
        Rook blackRookLeft = new Rook(
                new Position(Rook.BLACK_ROOKS_STARTING_ROW, Rook.BLACK_LEFTROOK_STARTING_COLUMN),
                Player.BLACK,
                getFirstAvailableId());
        pieces[Rook.BLACK_LEFTROOK_STARTING_COLUMN - 1][Rook.BLACK_LEFTROOK_STARTING_COLUMN
                - 1] = blackRookLeft;
        whitePieces.add(blackRookLeft);

        // SECONDA TORRE NERA
        Rook blackRookRight = new Rook(
                new Position(Rook.BLACK_ROOKS_STARTING_ROW, Rook.BLACK_RIGHTROOK_STARTING_COLUMN),
                Player.BLACK,
                getFirstAvailableId());
        pieces[Rook.BLACK_RIGHTROOK_STARTING_COLUMN - 1][Rook.BLACK_RIGHTROOK_STARTING_COLUMN
                - 1] = blackRookRight;
        whitePieces.add(blackRookRight);

        // PRIMO CAVALLO NERO
        Knight blackKnightLeft = new Knight(
                new Position(Knight.BLACK_KNIGHTS_STARTING_ROW, Knight.BLACK_LEFTKNIGHT_STARTING_COLUMN),
                Player.BLACK,
                getFirstAvailableId());
        pieces[Knight.BLACK_LEFTKNIGHT_STARTING_COLUMN - 1][Knight.BLACK_LEFTKNIGHT_STARTING_COLUMN
                - 1] = blackKnightLeft;
        whitePieces.add(blackKnightLeft);

        // SECONDO CAVALLO NERO
        Knight blackKnightRight = new Knight(
                new Position(Knight.BLACK_KNIGHTS_STARTING_ROW, Knight.BLACK_RIGHTKNIGHT_STARTING_COLUMN),
                Player.BLACK,
                getFirstAvailableId());
        pieces[Knight.BLACK_RIGHTKNIGHT_STARTING_COLUMN - 1][Knight.BLACK_RIGHTKNIGHT_STARTING_COLUMN
                - 1] = blackKnightRight;
        whitePieces.add(blackKnightRight);

        // PRIMO ALFIERE NERO
        Bishop blackBishopLeft = new Bishop(
                new Position(Bishop.BLACK_BISHOPS_STARTING_ROW, Bishop.BLACK_LEFTBISHOP_STARTING_COLUMN),
                Player.BLACK,
                getFirstAvailableId());
        pieces[Bishop.BLACK_BISHOPS_STARTING_ROW - 1][Bishop.BLACK_LEFTBISHOP_STARTING_COLUMN
                - 1] = blackBishopLeft;
        whitePieces.add(blackBishopLeft);

        // SECONDO ALFIERE NERO
        Bishop blackBishopRight = new Bishop(
                new Position(Bishop.BLACK_BISHOPS_STARTING_ROW, Bishop.BLACK_RIGHTBISHOP_STARTING_COLUMN),
                Player.BLACK,
                getFirstAvailableId());
        pieces[Bishop.BLACK_BISHOPS_STARTING_ROW - 1][Bishop.BLACK_RIGHTBISHOP_STARTING_COLUMN
                - 1] = blackBishopRight;
        whitePieces.add(blackBishopRight);

        // REGINA NERA
        Bishop blackQueen = new Bishop(
                new Position(Queen.BLACK_QUEEN_STARTING_ROW, Queen.BLACK_QUEEN_STARTING_COLUMN),
                Player.BLACK,
                getFirstAvailableId());
        pieces[Queen.BLACK_QUEEN_STARTING_ROW - 1][Queen.BLACK_QUEEN_STARTING_COLUMN
                - 1] = blackQueen;
        whitePieces.add(blackQueen);

        // RE NERO
        King blackKing = new King(
                new Position(King.BLACK_KING_STARTING_ROW, King.BLACK_KING_STARTING_COLUMN),
                Player.BLACK,
                getFirstAvailableId());
        pieces[King.BLACK_KING_STARTING_ROW - 1][King.BLACK_KING_STARTING_COLUMN - 1] = blackKing;

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
        if (position == null) {
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

        if (this.simulationMode && this.simulatedMove == null) {
            move.getPiece().setPosition(move.getPosition().clone());
            this.simulatedMove = move;

        } else if (!this.simulationMode && this.simulatedMove != null) {
            move.getPiece().moveTo(move.getPosition());

        } else if (!this.simulationMode) {
            move.getPiece().moveTo(move.getPosition());

        } else if (this.simulationMode && this.simulatedMove != null) {
            System.out.println("Error , the board is in simulation mode but youre trying to apply another move");
            return;
        }
        this.pieces[move.getPosition().getRow() - 1][move.getPosition().getColumn() - 1] = move.getPiece();
    }

    public void revertSimulatedMove(Move move) {
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
        return returnedPieces;
    }

    public ArrayList<Piece> getWhitePieces() {
        return this.whitePieces;
    }

    public ArrayList<Piece> getBlackPieces() {
        return this.blackPieces;
    }
}
