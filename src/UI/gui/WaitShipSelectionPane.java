package UI.gui;

import Logica.InteracaoEsperada;
import Logica.ObservableGame;
import static UI.gui.Constants.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;


public class WaitShipSelectionPane extends HBox {

    private ObservableGame modeloObs;
    
    public WaitShipSelectionPane(ObservableGame ob){
        
        this.modeloObs = ob;
        this.modeloObs.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                atualizaVista();
                
            }
        });
        
        organizaComponentes();
        atualizaVista();
        
    }
    
    private void organizaComponentes(){
        
        setBackground(new Background(
                new BackgroundImage(Imagens.getImagem(BACKGROUND),
                         BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
        
        ImageView miningShip = new ImageView(Imagens.getImagem(MINING_SHIP));
        ImageView militaryShip = new ImageView(Imagens.getImagem(MILITARY_SHIP));
        
        getChildren().addAll(miningShip, militaryShip);
        
    }
    
    private void atualizaVista(){
        InteracaoEsperada ie = modeloObs.getInteracaoEsperada();
        setVisible(ie == InteracaoEsperada.INTERACAO_WAIT_SHIP_SELECTION);
        
    }
    
}
