package scuola.esercitazione.gioco1v1;

import java.io.IOException;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UIManager {
    
    Game game = new Game();

    @FXML
    private AnchorPane AnchorPaneIniziale;
    @FXML
    private Button PlayButton;
    @FXML
    private Button QuitButton;
    @FXML
    private ImageView Logo;
    @FXML
    private Button ClientButton;
    @FXML
    private Button ServerButton;
    @FXML
    private Button ZanoButton;

    
    // UI RELATIVA ALLA PARTE DEL GAMEPLAY
    @FXML
    public void clickedSquare(MouseEvent event) {
        Pane clickedSquare = (Pane) event.getSource();

        Integer row = GridPane.getRowIndex(clickedSquare) == null ? 8 : 8 - GridPane.getRowIndex(clickedSquare);
        Integer column = GridPane.getColumnIndex(clickedSquare) == null ? 1 : GridPane.getColumnIndex(clickedSquare) + 1;
        Position pos = new Position(row.intValue(), column.intValue());

        Piece piece = game.getBoard().getPiece(pos);
        Player pieceOwner = piece == null ? null : piece.getOwner();
        boolean clickedOwnedPiece = pieceOwner == game.getPlayer();

        if (!game.getIsPieceSelected() && clickedOwnedPiece) {
            game.setIsPieceSelected(true);
            game.setSelectedPiece(piece);
            // TODO: far vedere sulla scacchiera le mosse possibili
            return;
        }

        
        if (game.getIsPieceSelected() && clickedOwnedPiece) {
            // TODO: CHECK DELL ARROCCO DA FARE
            game.setIsPieceSelected(true);
            game.setSelectedPiece(piece);
            // TODO: far vedere sulla scacchiera le mosse possibili
            return;
        }

        if (game.getIsPieceSelected() && !clickedOwnedPiece) {

            Move move = new Move(new Position(row, column), game.getSelectedPiece(), false);
            game.getSelectedPiece().calculateMoves();
            game.getChecker().checkMoves(game.getSelectedPiece().getValidMoves());

            for (Move singleMove : game.getSelectedPiece().getValidMoves()) {
                if (singleMove.equals(move)) {
                    System.out.println("Mossa valida");
                }
            }
            game.setIsPieceSelected(false);
            game.setSelectedPiece(null);
            return;
        }
    }

    // UI DELLA PARTE DI MENU
    @FXML
    public void PlayButton(ActionEvent event) {

        Animazione();

    }
    @FXML
    public void Animazione(){

        TranslateTransition AnimazioneLogo= new TranslateTransition(Duration.seconds(1), Logo);

        double centerXLogo = (Logo.getScene().getWidth() - Logo.getFitWidth()) / 2;
        double centerXButton = (Logo.getScene().getWidth() -PlayButton.getWidth()) / 2;
        double centerYButton = (Logo.getScene().getHeight()-PlayButton.getHeight()) / 2;

        AnimazioneLogo.setToX(centerXLogo - Logo.getLayoutX());
        AnimazioneLogo.setToY(-(Logo.getLayoutY() - 50));

        TranslateTransition AnimazioneBottone1= new TranslateTransition(Duration.seconds(1), PlayButton);

        AnimazioneBottone1.setToX(centerXButton - PlayButton.getLayoutX()- centerXButton/2);
        AnimazioneBottone1.setToY((centerYButton) - PlayButton.getLayoutY()+100);

        TranslateTransition AnimazioneBottone2= new TranslateTransition(Duration.seconds(1),QuitButton);

        AnimazioneBottone2.setToX(centerXButton - PlayButton.getLayoutX()+ centerXButton/2);
        AnimazioneBottone2.setToY(centerYButton - PlayButton.getLayoutY());

        ParallelTransition parallelTransition = new ParallelTransition(AnimazioneLogo, AnimazioneBottone1, AnimazioneBottone2);

        parallelTransition.play();

        parallelTransition.setOnFinished(event -> {

            Bounds playButtonBounds = PlayButton.localToScene(PlayButton.getBoundsInLocal());
            Bounds quitButtonBounds = QuitButton.localToScene(QuitButton.getBoundsInLocal());

            ServerButton.setLayoutX(playButtonBounds.getMinX());
            ServerButton.setLayoutY(playButtonBounds.getMinY());

            ClientButton.setLayoutX(quitButtonBounds.getMinX());
            ClientButton.setLayoutY(quitButtonBounds.getMinY());

            PlayButton.setVisible(false);
            QuitButton.setVisible(false);
            ServerButton.setVisible(true);
            ClientButton.setVisible(true);

        });


    }
    
    @FXML
    public void ServerButton(ActionEvent event) throws Exception{

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Schermata Scacchiera.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace(); 
        }

    }
        
        

    @FXML
    void QuitButton(ActionEvent event) {

        System.exit(0);

    }

    @FXML
    public void arrenditi(ActionEvent event) {
        System.out.println("Ti sei arreso");
    }
}
