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

            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/fxml/Schermata Server.fxml"));
            Parent root1 = loader1.load();
            Scene scene1= new Scene(root1);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
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
