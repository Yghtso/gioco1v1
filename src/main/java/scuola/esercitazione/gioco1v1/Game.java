package scuola.esercitazione.gioco1v1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Game {

    private boolean pieceSelected = false;
    private Piece selectedPiece = null;
    private ChessBoard board = new ChessBoard();
    private Player player;
    private MoveChecker checker = new MoveChecker(board);

    @FXML
    public void selectPiece(MouseEvent event) {
        Pane clickedBox = (Pane) event.getSource();

        Integer row = GridPane.getRowIndex(clickedBox) == null ? 1 : GridPane.getRowIndex(clickedBox) + 1;
        Integer column = GridPane.getColumnIndex(clickedBox) == null ? 1 : GridPane.getRowIndex(clickedBox) + 1;
        Position pos = new Position(row.intValue(), column.intValue());
        
        // PEZZO NELLA POSIZIONE CHE HO CLICCATO
        Piece piece = board.getPiece(pos);
        
        // STO FORSE PER ESEGUIRE UNA MOSSA
        if (pieceSelected) {
            Move move = new Move(pos, selectedPiece);
            checker.checkMoves();

            for (Move singleMove : selectedPiece.getValidMoves()) {
                if (move.equals(singleMove)) {
                    System.out.println("Mossa valida");
                    board.applyMove(move);
                    return;
                }
            }
        }

        // STO SELEZIONANDO PER LA PRIMA VOLTA UNA PEDINA
        else {

        }
    }
    
}
