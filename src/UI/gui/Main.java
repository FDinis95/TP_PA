package UI.gui;

import Logica.MaquinaEstados;
import Logica.ObservableGame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        MaquinaEstados maquina = new MaquinaEstados();
        ObservableGame ob = new ObservableGame(maquina);

        Scene scene = new Scene(new Root(ob), 1200, 700);
        
        primaryStage.setTitle("PlanetBound");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
