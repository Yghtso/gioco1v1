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
    private AnchorPane AnchorPaneIniziale;
    @FXML
    private Button PlayButton;
    @FXML
    private Button QuitButton;
    @FXML
    private Button ZanoButton;
    @FXML
    private ImageView Logo;

    @FXML
    public void selectPiece(MouseEvent event) {
        Pane clickedBox = (Pane) event.getSource();

        Integer row = GridPane.getRowIndex(clickedBox) == null ? 8 : 8 - GridPane.getRowIndex(clickedBox);
        Integer column = GridPane.getColumnIndex(clickedBox) == null ? 1 : GridPane.getColumnIndex(clickedBox) + 1;
        Position pos = new Position(row.intValue(), column.intValue());

        Piece piece = board.getPiece(pos);
        Player pieceOwner = piece == null ? null : piece.getOwner();
        boolean clickedOwnedPiece = pieceOwner == this.player;

        // STO SELEZIONANDO UNA PEDINA PER LA PRIMA VOLTA E SELEZIONO UNA PEDINA NON MIA
        if (!isPieceSelected && clickedOwnedPiece) {

            this.isPieceSelected = true;
            this.selectedPiece = piece;
            // TODO: far vedere sulla scacchiera le mosse possibili
            return;
        }

        if (isPieceSelected && clickedOwnedPiece) {

            // TODO: CHECK DELL ARROCCO DA FARE
            this.isPieceSelected = true;
            this.selectedPiece = piece;
            // TODO: far vedere sulla scacchiera le mosse possibili
            return;
        }

        if (isPieceSelected && !clickedOwnedPiece) {

            Move move = new Move(new Position(row, column), selectedPiece, false);
            selectedPiece.calculateMoves();

            for (Move singlMove : selectedPiece.getValidMoves()) {
                System.out.println("Mossa possibile : " + singlMove.getPosition().getRow() + ", " + singlMove.getPosition().getColumn());
            }
            System.out.println("Mosse valide :");
            checker.checkMoves(selectedPiece.getValidMoves());
            for (Move singlMove : selectedPiece.getValidMoves()) {
                System.out.println("Mossa possibile : " + singlMove.getPosition().getRow() + ", " + singlMove.getPosition().getColumn());
            }

            for (Move singleMove : selectedPiece.getValidMoves()) {
                if (singleMove.equals(move)) {
                    System.out.println("Mossa valida");
                }
            }
            this.isPieceSelected = false;
            this.selectedPiece = null;
            return;
        }
    }

    @FXML
    public void arrenditi(ActionEvent event) {
        System.out.println("Ti sei arreso");
    }
    @FXML
    public void PlayButton(ActionEvent event) {

        //PlayButton.setVisible(false);
        //QuitButton.setVisible(false);
        //ZanoButton.setVisible(true);

        Animazione();

    }

    public void Animazione(){

        TranslateTransition AnimazioneLogo= new TranslateTransition(Duration.seconds(1), Logo);

        double centerX = (Logo.getScene().getWidth() - Logo.getFitWidth()) / 2;
        double centerY = (Logo.getScene().getHeight() - Logo.getFitHeight()) / 2;

        AnimazioneLogo.setToX(centerX - Logo.getLayoutX());
        AnimazioneLogo.setToY(-(Logo.getLayoutY() - 50)); 

        TranslateTransition AnimazioneBottone1= new TranslateTransition(Duration.seconds(1), PlayButton);

        AnimazioneBottone1.setToX(centerX+200);
        AnimazioneBottone1.setToY(-(centerY));

        TranslateTransition AnimazioneBottone2= new TranslateTransition(Duration.seconds(1),QuitButton);

        AnimazioneBottone2.setToX(centerX-350);
        AnimazioneBottone2.setToY(-(centerY));

        ParallelTransition parallelTransition = new ParallelTransition(AnimazioneLogo, AnimazioneBottone1, AnimazioneBottone2);

        parallelTransition.play();

    }

    @FXML
    void QuitButton(ActionEvent event) {


    }

}

enum Player {
    WHITE, BLACK,
}
