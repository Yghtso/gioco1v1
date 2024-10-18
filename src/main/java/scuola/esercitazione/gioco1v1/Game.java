package scuola.esercitazione.gioco1v1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Game {

    private boolean isPieceSelected = false;
    private Piece selectedPiece = null;
    private ChessBoard board = new ChessBoard();
    private Player player = Player.WHITE;
    private MoveChecker checker = new MoveChecker(board);

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
            System.out.println("Selezionata per la prima volta una pedina");
            System.out.println("Pedina selezionata : " + piece);
            return;
        }

        if (isPieceSelected && clickedOwnedPiece) {
            // TODO: CHECK DELL ARROCCO DA FARE
            this.isPieceSelected = true;
            this.selectedPiece = piece;
            // TODO: far vedere sulla scacchiera le mosse possibili
            System.out.println("Riselezionata una nuova pedina");
            System.out.println("Pedina selezionata : " + piece);
            return;
        }

        if (isPieceSelected && !clickedOwnedPiece) {
            System.out.println("Selezionata una casella con una pedina non tua, check della mossa . . .");
            System.out.println(selectedPiece);

            Move move = new Move(new Position(row, column), selectedPiece, false);

            checker.checkMoves(selectedPiece);
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

}

enum Player {
    WHITE, BLACK,
}
