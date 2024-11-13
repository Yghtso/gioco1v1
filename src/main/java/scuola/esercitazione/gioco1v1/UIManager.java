package scuola.esercitazione.gioco1v1;

import java.net.ServerSocket;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.scene.Node;


public class UIManager {
    
    Game game = new Game(Player.WHITE);
    static ServerNetManager Server;
    static ClientNetManager Client;

    //SchermataScacchiera
    @FXML
    private GridPane GridPaneScacchiera;

    //ScermataIniziale
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

    //SchermataServer
    @FXML
    private AnchorPane AnchorPaneServer;
    @FXML
    private Label LabelIP;
    @FXML
    private Label LabelTitoloIPServer;
    @FXML
    private Button ServerBackButton;

    //SchermataClient
    @FXML
    private AnchorPane AnchorPaneClient;
    @FXML
    private Button ClientBackButton;
    @FXML
    private Label LabelTitoloIPClient;
    @FXML
    private TextField TextIp;


    
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

        if (!game.getYourTurn()) {
            return;
        }

        if (!game.getIsPieceSelected() && clickedOwnedPiece) {
            game.setIsPieceSelected(true);
            game.setSelectedPiece(piece);

            piece.calculateMoves();
            game.getChecker().checkMoves(piece.getValidMoves());
            
            for (Move singlePieceMove : piece.getValidMoves()) {
                Pane paneSquare = (Pane) getNodeByRowColumnIndex(8 - singlePieceMove.getPosition().getRow(), singlePieceMove.getPosition().getColumn() - 1, GridPaneScacchiera);
                ImageView img = (ImageView) paneSquare.getChildren().getFirst();
                img.setImage(new Image(getClass().getResource("/imgs/Cerchio.png").toExternalForm()));
            }
            return;
        }

        
        if (game.getIsPieceSelected() && clickedOwnedPiece) {
            for (Move singlePieceMove : game.getSelectedPiece().getValidMoves()) {
                Pane paneSquare = (Pane) getNodeByRowColumnIndex(8 - singlePieceMove.getPosition().getRow(), singlePieceMove.getPosition().getColumn() - 1, GridPaneScacchiera);
                ImageView img = (ImageView) paneSquare.getChildren().getFirst();
                img.setImage(null);
            }

            // TODO: CHECK DELL ARROCCO DA FARE
            game.setIsPieceSelected(true);
            game.setSelectedPiece(piece);
            
            piece.calculateMoves();
            game.getChecker().checkMoves(piece.getValidMoves());
            
            for (Move singlePieceMove : piece.getValidMoves()) {
                Pane paneSquare = (Pane) getNodeByRowColumnIndex(8 - singlePieceMove.getPosition().getRow(), singlePieceMove.getPosition().getColumn() - 1, GridPaneScacchiera);
                ImageView img = (ImageView) paneSquare.getChildren().getFirst();
                img.setImage(new Image(getClass().getResource("/imgs/Cerchio.png").toExternalForm()));
            }
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

    private static Node getNodeByRowColumnIndex(int row, int column, GridPane gridPane) {
        for (Node node : gridPane.getChildren()) {
            Integer nodeRow = GridPane.getRowIndex(node);
            Integer nodeColumn = GridPane.getColumnIndex(node);

            if (nodeRow == null) nodeRow = 0;
            if (nodeColumn == null) nodeColumn = 0;

            if (nodeRow == row && nodeColumn == column) {
                return node;
            }
        }
        return null;
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
        AnimazioneBottone1.setToY((centerYButton) - PlayButton.getLayoutY()+150);

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
    public void ServerButton(ActionEvent event) throws Exception {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Schermata Server.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setResizable(false);
        
            Scene scene = new Scene(root);
            currentStage.setScene(scene);

            Server= new ServerNetManager(ServerNetManager.PORT);

            AggiornaLabel();

            GestoreChiusura(currentStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void AggiornaLabel(){

        LabelIP.setText("OIOIOIOIOIOI");

    }

    public void ClientButton(ActionEvent event) throws Exception{

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Schermata Client.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setResizable(false);

            Scene scene = new Scene(root);
            currentStage.setScene(scene);

            Client= new ClientNetManager();

            GestoreChiusura(currentStage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void GoBack(ActionEvent event) {

        try {

            System.out.println(Server);
            ChiudiConnessioni();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Schermata Iniziale.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setResizable(false);
        
            Scene scene = new Scene(root);
            currentStage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        
    void GestoreChiusura(Stage stage){

        stage.setOnCloseRequest((WindowEvent event) -> {
            System.out.println(Server);
            ChiudiConnessioni();
            Platform.exit();  
            System.exit(0);
        });

    }

    void ChiudiConnessioni(){

        boolean ServerClosed, ClientClosed;

        if(Server instanceof ServerNetManager){
            System.out.println("Li odio i negri");
            ServerClosed= Server.close();
        }
        if(Client instanceof ClientNetManager){
            System.out.println("Li odio i froci");
            ClientClosed= Client.close();
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
