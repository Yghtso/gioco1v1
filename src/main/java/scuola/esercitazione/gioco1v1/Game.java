package scuola.esercitazione.gioco1v1;

public class Game {

    private boolean isPieceSelected = false;
    private Piece selectedPiece = null;
    private ChessBoard board = new ChessBoard();
    private Player player;
    private MoveChecker checker = new MoveChecker(board);
    private boolean yourTurn;

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

    public boolean getYourTurn() {
        return this.yourTurn;
    }

    public Game(Player player) {
        this.player = player;
        this.yourTurn = player == Player.WHITE;

    }

    public void changeTurn() {
        System.out.println("Cambiato il turno in : " + !this.yourTurn);
        this.yourTurn = !this.yourTurn;
    }
}

enum Player {
    WHITE, BLACK,
}
