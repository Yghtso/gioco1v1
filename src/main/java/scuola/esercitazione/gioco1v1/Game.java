package scuola.esercitazione.gioco1v1;

public class Game {

    private boolean isPieceSelected = false;
    private Piece selectedPiece = null;
    private ChessBoard board = new ChessBoard();
    private Player player = Player.WHITE;
    private MoveChecker checker = new MoveChecker(board);

    public void setIsPieceSelected(boolean isPieceSelected) {
        this.isPieceSelected = isPieceSelected;
    }

    public void setSelectedPiece(Piece selectedPiece) {
        this.selectedPiece = selectedPiece;
    }

    public ChessBoard getBoard() {
        return this.board;
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean getIsPieceSelected() {
        return this.isPieceSelected;
    }

    public Piece getSelectedPiece() {
        return this.selectedPiece;
    }

    public MoveChecker getChecker() {
        return this.checker;
    }
}

enum Player {
    WHITE, BLACK,
}
