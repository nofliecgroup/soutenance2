package fr.greta.soutenance2application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The type Vinyl application.
 */
public class VinylApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VinylApplication.class.getResource("vinyl-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 960, 691);
        stage.setTitle("Scraping Vinyl Application!");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch();
    }
}