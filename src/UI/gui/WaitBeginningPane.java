package UI.gui;

import Logica.InteracaoEsperada;
import Logica.ObservableGame;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;


public class WaitBeginningPane extends BorderPane {
    
    private ObservableGame modeloObs;
    
    public WaitBeginningPane(ObservableGame ob){
        
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
               
        //HANDLES
        Button start = new Button("Start Game");
        Button exit = new Button("Exit Game");
        ImageView spaceShip = new ImageView(Imagens.getImagem(Constants.ROCKET_SHIP));
        HBox textBox = new HBox(10);
        HBox image = new HBox();
        
        //ImageView Foguete
        spaceShip.setFitWidth(400);
        spaceShip.setFitHeight(400);

        
        //HBox da opção
        textBox.setAlignment(Pos.TOP_CENTER);
        textBox.setSpacing(20);
        textBox.setPrefSize(100, 200);
        textBox.getChildren().addAll(start, exit);
        
        //HBox da imagem
        image.setPrefSize(200, 150);
        image.setAlignment(Pos.BOTTOM_CENTER);
        image.getChildren().add(spaceShip);
        
        setBottom(textBox);
        setCenter(image);
        
        
        //Handle do botão Start
        start.setOnAction((ActionEvent event) -> {
//            moveRocket(spaceShip); //VOLTAR A METER DEPOIS
            
            Timer time = new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {
                  modeloObs.setup();
                  
                }
            }, 0/*3700*/);
            
        });
        
        //Handle do botão Exit
        exit.setOnAction((ActionEvent event) -> {
            System.exit(0);
        });
        
    }
    
    private void atualizaVista(){
        InteracaoEsperada ie = modeloObs.getInteracaoEsperada();
        setVisible(ie == InteracaoEsperada.INTERACAO_WAIT_BEGINNING);
        
    }
    
    private void moveRocket(ImageView rocket){
        
        TranslateTransition moveRocketY = new TranslateTransition();
        
        //Criação do som do foguete
        MediaPlayer media = new MediaPlayer(Sounds.getSom(Constants.ROCKET_LAUNCH));
        
        //Translação do foguete a subir (Tecnicamente ainda existe no plano, mas esta escondido pela janela)
        moveRocketY.setByY(-500);
        moveRocketY.setRate(0.5);
        moveRocketY.setDuration(Duration.seconds(2));
        moveRocketY.setNode(rocket);
        
        
        //Play do som e da translação
        media.play();
        moveRocketY.play();
        
    }
    
    
}
