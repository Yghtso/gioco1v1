package scuola.esercitazione.gioco1v1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gioco1v1 extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Schermata Iniziale.fxml"));
            Parent root = loader.load();

            Scene Startscene = new Scene(root);
            primaryStage.setScene(Startscene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Scacchi");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
