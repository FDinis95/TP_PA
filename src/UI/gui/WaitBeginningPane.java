package UI.gui;

import Logica.InteracaoEsperada;
import Logica.ObservableGame;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
    private ImageView spaceShip = new ImageView(Imagens.getImagem(Constants.ROCKET_SHIP));
    
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

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(3), (ActionEvent event1) -> {
                        
                        modeloObs.setup();
                    }));
            timeline.playFromStart();
            moveRocket(spaceShip);

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
        moveRocketY.setByY(-700);
        moveRocketY.setRate(0.7);
        moveRocketY.setDuration(Duration.seconds(2));
        moveRocketY.setNode(rocket);
        
        
        //Play do som e da translação
        media.play();
        moveRocketY.play();
        
    }
    
    
}
