package UI.gui;

import Logica.ObservableGame;
import Logica.dados.SpaceStation;
import Logica.dados.variations.MiningShip;
import Logica.dados.variations.PlanetAzul;
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
        
        
        modeloObs.setSpaceShip(new MiningShip());
        modeloObs.setPlaneta(new PlanetAzul());
        modeloObs.getJogo().getPlaneta().setSpaceStation(new SpaceStation());
        
        WaitShipSelectionPane selectShip = new WaitShipSelectionPane(modeloObs); 
        
        WaitMovePane move = new WaitMovePane(modeloObs);
        WaitPlanetSectorPane planetSector = new WaitPlanetSectorPane(modeloObs);
        WaitSpaceStationPane spaceStation = new WaitSpaceStationPane(modeloObs);
        
        StackPane center = new StackPane(begin, selectShip, move, planetSector, spaceStation);
        
        this.setCenter(center);
                
    }

}
