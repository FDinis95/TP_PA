package UI.gui;

import Logica.EscreverFicheiro;
import Logica.InteracaoEsperada;
import Logica.ObservableGame;
import static UI.gui.Constants.DEFEAT;
import static UI.gui.Constants.VICTORY;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class WaitGameOverPane extends BorderPane{

    private ObservableGame modeloObs;
    private ImageView victoryDefeatImage = new ImageView(Imagens.getImagem(DEFEAT));
    
    public WaitGameOverPane(ObservableGame modeloObs) {
        this.modeloObs = modeloObs;
        this.modeloObs.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            
            atualizaVista();

        });
        
        organizaComponentes();
        atualizaVista();
        
    }
    
    private void organizaComponentes(){
        
        organizaCenter();
        
        HBox buttonBox = new HBox();
        Button newGame = new Button("New Game");
        Button exit = new Button("Exit");
        
        
        
        newGame.setOnAction((event) -> {
            modeloObs.newGame();
            
        });
        
        exit.setOnAction((event) -> {
            try {
                EscreverFicheiro logging = new EscreverFicheiro(modeloObs.getJogo());
                
            } catch (IOException ex) {}
            
            System.exit(0);
        });
        
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(20);
        buttonBox.setPrefSize(100, 200);
        buttonBox.getChildren().addAll(newGame, exit);
        
        setBottom(buttonBox);
    }
    
    private void organizaCenter(){
        
        HBox endScreenBox = new HBox();
        
        victoryDefeatImage.setFitHeight(400);
        victoryDefeatImage.setFitWidth(400);
        endScreenBox.setAlignment(Pos.CENTER);
        endScreenBox.getChildren().add(victoryDefeatImage);
        
        setCenter(endScreenBox);
        
    }
    
    private void atualizaVista() {
        InteracaoEsperada ie = modeloObs.getInteracaoEsperada();
        setVisible(ie == InteracaoEsperada.INTERACAO_WAIT_GAMEOVER);
        
        if(modeloObs.getJogo().getVictory()){
            victoryDefeatImage.setImage(Imagens.getImagem(VICTORY));
            
        }else
            victoryDefeatImage.setImage(Imagens.getImagem(DEFEAT));
        
    }

}
