package scuola.esercitazione.gioco1v1;


import java.net.InetAddress;
import java.net.UnknownHostException;

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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class UIManager{
    
    static Game game;
    static ServerNetManager servNetMng;
    static ClientNetManager clientNetMng;
    static Thread serverThread;

    static boolean yourStartAccept;
    static boolean otherStartAccept;
 
    //SchermataScacchiera
    @FXML
    private GridPane GridPaneScacchiera;
    @FXML
    private Button StartButton;
    @FXML
    private Button SurrenderButton;

    //Immagini delle pedine della scacchiera
    Image blackPawnImg = new Image(getClass().getResource("/imgs/Pieces/PedoneNero.png").toExternalForm());
    Image blackRookImg = new Image(getClass().getResource("/imgs/Pieces/TorreNero.png").toExternalForm());
    Image blackBishopImg = new Image(getClass().getResource("/imgs/Pieces/AlfiereNero.png").toExternalForm());
    Image blackKnightImg = new Image(getClass().getResource("/imgs/Pieces/CavalloNero.png").toExternalForm());
    Image blackQueenImg = new Image(getClass().getResource("/imgs/Pieces/ReginaNero.png").toExternalForm());
    Image blackKingImg = new Image(getClass().getResource("/imgs/Pieces/ReNero.png").toExternalForm());

    Image whitePawnImg = new Image(getClass().getResource("/imgs/Pieces/Pedone.png").toExternalForm());
    Image whiteRookImg = new Image(getClass().getResource("/imgs/Pieces/Torre.png").toExternalForm());
    Image whiteBishopImg = new Image(getClass().getResource("/imgs/Pieces/Alfiere.png").toExternalForm());
    Image whiteKnightImg = new Image(getClass().getResource("/imgs/Pieces/Cavallo.png").toExternalForm());
    Image whiteQueenImg = new Image(getClass().getResource("/imgs/Pieces/Regina.png").toExternalForm());
    Image whiteKingImg = new Image(getClass().getResource("/imgs/Pieces/Re.png").toExternalForm());

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
    @FXML
    private Button SendButton;


    
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
            
            displayPieces();

            for (Move singlePieceMove : piece.getValidMoves()) {
                Piece destPieceEachMove = game.getBoard().getPiece(singlePieceMove.getPosition());
                Player destPieceOwnerSingleMove = destPieceEachMove == null ? null : destPieceEachMove.getOwner();

                if (!((pieceOwner == Player.WHITE && destPieceOwnerSingleMove == Player.BLACK) || (pieceOwner == Player.BLACK && destPieceOwnerSingleMove == Player.WHITE))) {

                    Pane paneSquare = (Pane) getNodeByRowColumnIndex(8 - singlePieceMove.getPosition().getRow(), singlePieceMove.getPosition().getColumn() - 1, GridPaneScacchiera);
                    ImageView img = (ImageView) paneSquare.getChildren().getFirst();
                    img.setImage(new Image(getClass().getResource("/imgs/Cerchio.png").toExternalForm()));
                }
            }
            return;
        }

        
        if (game.getIsPieceSelected() && clickedOwnedPiece) {

            displayPieces();
            game.setIsPieceSelected(true);
            game.setSelectedPiece(piece);
            
            piece.calculateMoves();
            game.getChecker().checkMoves(piece.getValidMoves());
            
            for (Move singlePieceMove : piece.getValidMoves()) {

                Piece destPieceEachMove = game.getBoard().getPiece(singlePieceMove.getPosition());
                Player destPieceOwnerSingleMove = destPieceEachMove == null ? null : destPieceEachMove.getOwner();

                if (!((pieceOwner == Player.WHITE && destPieceOwnerSingleMove == Player.BLACK) || (pieceOwner == Player.BLACK && destPieceOwnerSingleMove == Player.WHITE))) {
                    Pane paneSquare = (Pane) getNodeByRowColumnIndex(8 - singlePieceMove.getPosition().getRow(), singlePieceMove.getPosition().getColumn() - 1, GridPaneScacchiera);
                    ImageView img = (ImageView) paneSquare.getChildren().getFirst();
                    img.setImage(new Image(getClass().getResource("/imgs/Cerchio.png").toExternalForm()));
                }
            }
            return;
        }

        if (game.getIsPieceSelected() && !clickedOwnedPiece) {

            displayPieces();

            Move move = new Move(new Position(row, column), game.getSelectedPiece(), false, false);
            game.getSelectedPiece().calculateMoves();
            game.getChecker().checkMoves(game.getSelectedPiece().getValidMoves());

            for (Move singleMove : game.getSelectedPiece().getValidMoves()) {
                if (singleMove.equals(move)) {
                    
                    game.getBoard().applyMove(move);
                
                    displayPieces();
                }
            }
            game.setIsPieceSelected(false);
            game.setSelectedPiece(null);
            return;
        }
    }

    private void displayPieces() {

        for (Node node : GridPaneScacchiera.getChildren()) {
            if (node instanceof Pane) {
                Pane square = (Pane) node;
                ImageView img = (ImageView) square.getChildren().getFirst();
                img.setImage(null);
            }
        }

        for (Piece singlePiece : game.getBoard().getPieces()) {
            Pane square = (Pane) getNodeByRowColumnIndex(8 - singlePiece.getPosition().getRow(), singlePiece.getPosition().getColumn() - 1, GridPaneScacchiera);
            ImageView img = (ImageView) square.getChildren().getFirst();
            img.setImage(getImgByPiece(singlePiece));
        }
    }

    private Image getImgByPiece(Piece piece) {
        if (piece instanceof Pawn && piece.getOwner() == Player.WHITE) {
            return whitePawnImg;
        } else if (piece instanceof Rook && piece.getOwner() == Player.WHITE) {
            return whiteRookImg;
        } else if (piece instanceof Bishop && piece.getOwner() == Player.WHITE) {
            return whiteBishopImg;
        } else if (piece instanceof Knight && piece.getOwner() == Player.WHITE) {
            return whiteKnightImg;
        } else if (piece instanceof Queen && piece.getOwner() == Player.WHITE) {
            return whiteQueenImg;
        } else if (piece instanceof King && piece.getOwner() == Player.WHITE) {
            return whiteKingImg;
        } if (piece instanceof Pawn && piece.getOwner() == Player.BLACK) {
            return blackPawnImg;
        } else if (piece instanceof Rook && piece.getOwner() == Player.BLACK) {
            return blackRookImg;
        } else if (piece instanceof Bishop && piece.getOwner() == Player.BLACK) {
            return blackBishopImg;
        } else if (piece instanceof Knight && piece.getOwner() == Player.BLACK) {
            return blackKnightImg;
        } else if (piece instanceof Queen && piece.getOwner() == Player.BLACK) {
            return blackQueenImg;
        } else if (piece instanceof King && piece.getOwner() == Player.BLACK) {
            return blackKingImg;
        }
        return null;
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
    public void ServerButton(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Schermata Server.fxml"));
            Parent root = loader.load();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setResizable(false);
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            GestoreChiusura(currentStage);
            servNetMng = new ServerNetManager(ServerNetManager.PORT);

            Label labelIP = (Label) root.lookup("#LabelIP");
            InetAddress localHost;
            try {
                localHost = InetAddress.getLocalHost();
                labelIP.setText(localHost.getHostAddress());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            UIManager.serverThread = StartThreadListener(currentStage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Thread StartThreadListener(Stage stage) {
        Thread serverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                UIManager.clientNetMng = new ClientNetManager(servNetMng.startListening());

                if (clientNetMng.getUnderlineSocket() != null && clientNetMng.getUnderlineSocket().isConnected()) {
                    UIManager.game = new Game(Player.BLACK);

                    try {
        
                        Stage currentStage = stage;
        
                        ShowSchermataScacchiera(currentStage);

                        syncronizeToStart();
        
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else { clientNetMng = null; }
            }
        });

        serverThread.start();
        return serverThread;
    }

    public void ClientButton(ActionEvent event) {

        UIManager.clientNetMng = new ClientNetManager();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        ShowSchermataClient(currentStage);

    }

    public void syncronizeToStart() {
        
        Thread startReceiverThread = new Thread(new Runnable() {
            @Override
            public void run() {

                Move startMove;

                do { 
                    startMove = UIManager.clientNetMng.read();
                    otherStartAccept = startMove.getStartMatch();

                } while (!startMove.getStartMatch());
                
                if (otherStartAccept && yourStartAccept) {
                    startMainLoop();
                }
            }
        });
        startReceiverThread.start();
    }

    public void startMainLoop() {
        Thread startReceiverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (otherStartAccept) {
                    
                }
            }
        });
    }

    @FXML
    public void LeggiText(ActionEvent event) {

        String Testo = TextIp.getText();
        boolean Connesso = clientNetMng.connect(Testo);

        if(Connesso) {
            game = new Game(Player.WHITE);

            try {

                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                ShowSchermataScacchiera(currentStage);
                
                syncronizeToStart();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    public void GoBack(ActionEvent event) {

        try {

            ChiudiConnessioni();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            ShowSchermataIniziale(currentStage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        
    private void GestoreChiusura(Stage stage) {

        stage.setOnCloseRequest((WindowEvent event) -> {
            ChiudiConnessioni();
            Platform.exit();
            System.exit(0);
        });

    }

    private boolean ChiudiConnessioni() {
        
        boolean serverSocketClosed = false, clientSocketClosed = false;

        if(servNetMng == null) {    serverSocketClosed = true;  }
        else {  serverSocketClosed = servNetMng.close();    }

        if(clientNetMng == null) {    clientSocketClosed = true;  } 
        else {  clientSocketClosed = clientNetMng.close();    }

        return serverSocketClosed && clientSocketClosed;
    }

    @FXML
    public void QuitButton(ActionEvent event) {

        System.exit(0);

    }

    @FXML
    public void arrenditi(ActionEvent event) {
    }

    @FXML
    public void StartGame(ActionEvent event) {

        UIManager.yourStartAccept = true;
        UIManager.clientNetMng.send(new Move(null, null, false, true));

        if (otherStartAccept && yourStartAccept) {
            startMainLoop();
        }
    }

    public void ShowSchermataIniziale(Stage currentStage) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Schermata Iniziale.fxml"));
            Parent root = loader.load();

            currentStage.setResizable(false);
        
            Scene scene = new Scene(root);
            currentStage.setScene(scene);

            GestoreChiusura(currentStage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ShowSchermataServer(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Schermata Server.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setResizable(false);
        
            Scene scene = new Scene(root);
            currentStage.setScene(scene);

            GestoreChiusura(currentStage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ShowSchermataClient(Stage currentStage) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Schermata Client.fxml"));
            Parent root = loader.load();

            currentStage.setResizable(false);
        
            Scene scene = new Scene(root);
            currentStage.setScene(scene);

            GestoreChiusura(currentStage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ShowSchermataScacchiera(Stage currentStage) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Schermata Scacchiera.fxml"));
            Parent root = loader.load();

            currentStage.setResizable(false);
        
            Scene scene = new Scene(root);
            currentStage.setScene(scene);

            GestoreChiusura(currentStage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ShowSchermataConnessione(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Schermata Connessione.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setResizable(false);
        
            Scene scene = new Scene(root);
            currentStage.setScene(scene);

            GestoreChiusura(currentStage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ShowSchermataVittoria(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Schermata Vittoria.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setResizable(false);
        
            Scene scene = new Scene(root);
            currentStage.setScene(scene);

            GestoreChiusura(currentStage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ShowSchermataSconfitta(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Schermata Sconfitta.fxml"));
            Parent root = loader.load();

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setResizable(false);
        
            Scene scene = new Scene(root);
            currentStage.setScene(scene);

            GestoreChiusura(currentStage);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}


