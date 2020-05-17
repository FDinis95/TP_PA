package UI.gui;

import Logica.MaquinaEstados;
import Logica.ObservableGame;
import static UI.gui.Constants.BACKGROUND;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Root extends Application {
    
    @Override
    public void start(Stage primaryStage) {

        MaquinaEstados maquina = new MaquinaEstados();
        ObservableGame ob = new ObservableGame(maquina);

        StackPane root = new StackPane();
        root.setBackground(new Background(
                new BackgroundImage(
                        new Image(getClass().getResourceAsStream(BACKGROUND)),
                         BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        Scene scene = new Scene(root, 1200, 750);
        primaryStage.setTitle("PlanetBound");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
