package UI.gui;

import Logica.ObservableGame;
import static UI.gui.Constants.BACKGROUND;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class Root extends BorderPane{

    private ObservableGame modeloObs;
    
    public Root(ObservableGame ob) {
        this.modeloObs = ob;
    
        organizaComponentes();
    }
    
    private void organizaComponentes(){
        
        setBackground(new Background(new BackgroundImage(Imagens.getImagem(BACKGROUND),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        
        WaitBeginningPane begin = new WaitBeginningPane(modeloObs);
        WaitShipSelectionPane selectShip = new WaitShipSelectionPane(modeloObs);
        
        StackPane center = new StackPane(begin, selectShip);
        
        this.setCenter(center);
                
    }

}
