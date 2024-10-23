package scuola.esercitazione.gioco1v1;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Game {

    private boolean isPieceSelected = false;
    private Piece selectedPiece = null;
    private ChessBoard board = new ChessBoard();
    private Player player = Player.WHITE;
    private MoveChecker checker = new MoveChecker(board);

    @FXML
    public void selectPiece(MouseEvent event) {        
    }

    public void setIsPieceSelected (boolean isPieceSelected) {
        this.isPieceSelected = isPieceSelected;
    }

    public void setSelectedPiece (Piece selectedPiece) {
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
