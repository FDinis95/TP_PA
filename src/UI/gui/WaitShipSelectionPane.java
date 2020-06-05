package UI.gui;

import Logica.InteracaoEsperada;
import Logica.ObservableGame;
import static UI.gui.Constants.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class WaitShipSelectionPane extends BorderPane {

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
        

        VBox miningSide = new VBox();
        VBox militarySide = new VBox();
        
        Button miningOption = new Button("Mining Ship");
        Button militaryOption = new Button("Military Ship");
        
        militaryOption.setOnAction((event) -> {
            modeloObs.selectShip(1);
            
            
        });
        
        miningOption.setOnAction((event) -> {
            modeloObs.selectShip(2);
            
        });

        ImageView miningShip = new ImageView(Imagens.getImagem(MINING_SHIP));
        ImageView militaryShip = new ImageView(Imagens.getImagem(MILITARY_SHIP));
        
        //Move Buttons a bit lower
        miningOption.setTranslateY(30);
        militaryOption.setTranslateY(30);
        
        //Set Images Size
        miningShip.setFitWidth(400);
        miningShip.setFitHeight(400);
        militaryShip.setFitWidth(400);
        militaryShip.setFitHeight(400);
        
        //Set Images Positions
        miningSide.setAlignment(Pos.CENTER);
        miningSide.setPadding(new Insets(100));
        
        militarySide.setAlignment(Pos.CENTER);
        militarySide.setPadding(new Insets(100));
        
        militarySide.getChildren().addAll(militaryShip, militaryOption);
        miningSide.getChildren().addAll(miningShip, miningOption);
        
        setLeft(militarySide);
        setRight(miningSide);
        
        
        
    }
    
    private void atualizaVista(){
        InteracaoEsperada ie = modeloObs.getInteracaoEsperada();
        setVisible(ie == InteracaoEsperada.INTERACAO_WAIT_SHIP_SELECTION);
        
    }
    
}
