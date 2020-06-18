package UI.gui;

import Logica.InteracaoEsperada;
import Logica.ObservableGame;
import Logica.dados.Alien;
import Logica.dados.Drone;
import Logica.dados.Resource;
import static UI.gui.Constants.*;
import java.beans.PropertyChangeEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class WaitLandingPane extends BorderPane{
    
    private ObservableGame modeloObs;
    private Button returnHome = new Button("Return home");
    private int count = 0;
    
    //Alien Info
    private Label alienPosX = new Label();
    private Label alienPosY = new Label();
    private Label vidaAlienValue = new Label();
    private ProgressBar vidaAlienBar = new ProgressBar(0);
    private ImageView alienTypeImage = new ImageView();
    
    //Drone Info
    private Label dronePosX = new Label();
    private Label dronePosY = new Label();
    private Label vidaDroneValue = new Label();
    private ProgressBar vidaDroneBar = new ProgressBar(1);
    private ImageView droneRecursoImage = new ImageView(Imagens.getImagem(NO_RESOURCE));
    
    //Resource Info
    private Label recursoPosX = new Label();
    private Label recursoPosY = new Label();
    private Label tipoRecurso = new Label();
    private ImageView recursoImage = new ImageView();
    
    //Planet Info  
    private Label planetType = new Label();
    private Label planetResources = new Label();
    private HBox planetTypeBox = new HBox();
    private HBox recursosPlanetaBox = new HBox();
    private ImageView planetImage = new ImageView();
    
    
    //ImagesViews   
    private ImageView verdeR = new ImageView(Imagens.getImagem(GREEN_RESOURCE));
    private ImageView azulR = new ImageView(Imagens.getImagem(BLUE_RESOURCE));
    private ImageView pretoR = new ImageView(Imagens.getImagem(BLACK_RESOURCE));
    private ImageView vermelhoR = new ImageView(Imagens.getImagem(RED_RESOURCE));
    private ImageView artifact = new ImageView(Imagens.getImagem(ARTIFACT_RESOURCE));
        
    //MAPA STUFF
    private GridPane mapa = new GridPane();
    private ImageView[][] tileImage = new ImageView[6][6];
    
    public WaitLandingPane(ObservableGame ob){
        this.modeloObs = ob;
        this.modeloObs.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            
            atualizaVista();

        });
        
        organizaComponentes();
        atualizaVista();
        
    }
    
    private void organizaComponentes(){
        
        organizaTopBar();
        organizaMapa();
        
        HBox buttonBox = new HBox();
        
        Button cima = new Button("Cima");
        Button baixo = new Button("Baixo");
        Button esquerda = new Button("Esquerda");
        Button direita = new Button("Direita");
                
        cima.setOnAction((event) -> {
            modeloObs.moveToResource(0, -1);
            
//            if(!modeloObs.getTerreno().checkFight()){
//                
//                organizaLuta();
//            }
        });
        
        baixo.setOnAction((event) -> {
            modeloObs.moveToResource(0, 1);
            
//            if(!modeloObs.getTerreno().checkFight()){
//                
//                organizaLuta();
//            }
            
        });
        
        esquerda.setOnAction((event) -> {
            modeloObs.moveToResource(-1, 0);
            
//            if(!modeloObs.getTerreno().checkFight()){
//                
//                organizaLuta();
//            }
        });
        
        direita.setOnAction((event) -> {
            modeloObs.moveToResource(1, 0);
            
//            if(!modeloObs.getTerreno().checkFight()){
//                
//                organizaLuta();
//            }
        });
        
        returnHome.setOnAction((event) -> {
            modeloObs.hasResource();
            
        });
        
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(20);
        buttonBox.setPrefSize(100, 200);
        
        buttonBox.getChildren().addAll(cima, baixo, esquerda, direita, returnHome);
        setBottom(buttonBox);
        
    }

    private void organizaTopBar(){
        
        HBox topBar = new HBox();
        
        //Planeta Info Stuff
        VBox planetBox = new VBox(15);
        Label planetLabel = new Label("Planeta");
        
        planetType.setText("Tipo: ");
        planetResources.setText("Resources: ");
        
        planetLabel.setTextFill(Color.WHITE);
        planetLabel.setFont(Font.font(20));
        
        planetType.setTextFill(Color.WHITE);
        planetType.setFont(Font.font(20));
        planetResources.setTextFill(Color.WHITE);
        planetResources.setFont(Font.font(20));
        
               
        switch(modeloObs.getPlaneta().getTipo()){
            
            case "Verde":
                
                planetImage.setImage(Imagens.getImagem(GREEN_RESOURCE));
                recursosPlanetaBox.getChildren().addAll(planetResources, vermelhoR, verdeR);
                count++;
                break;
                
            case "Preto":

                planetImage.setImage(Imagens.getImagem(BLACK_RESOURCE));
                recursosPlanetaBox.getChildren().addAll(planetResources, pretoR, azulR);
                count++;
                break;   
                
            case "Vermelho":

                planetImage.setImage(Imagens.getImagem(RED_RESOURCE));
                recursosPlanetaBox.getChildren().addAll(planetResources, vermelhoR, azulR);
                count++;
                break;
                
            case "Azul":

                planetImage.setImage(Imagens.getImagem(BLUE_RESOURCE));
                recursosPlanetaBox.getChildren().addAll(planetResources, pretoR, verdeR, azulR, artifact);
                count++;
                break;
 
        }
        
        planetTypeBox.getChildren().addAll(planetType, planetImage);
        
        planetBox.setAlignment(Pos.CENTER);
        planetBox.setPadding(new Insets(10, 0, 5, 50));
        planetBox.getChildren().addAll(planetLabel, planetTypeBox, recursosPlanetaBox);
        
        //Drone Info Stuff
        VBox droneBox = new VBox(15);
        Group droneVida = new Group();
        HBox dronePosXBox = new HBox();
        HBox dronePosYBox = new HBox();
        HBox droneVidaBox = new HBox();
        HBox droneRecursoBox = new HBox();
        Label droneLabel = new Label("Drone");
        
        droneLabel.setTextFill(Color.WHITE);
        droneLabel.setFont(Font.font(20));       
        dronePosX.setText("Pos X: " + modeloObs.getTerreno().getDrone().getPosX());
        dronePosY.setText("Pos Y: " + modeloObs.getTerreno().getDrone().getPosY());
        Label vidaDrone = new Label("Vida: ");
        Label droneRecurso = new Label("Recurso: ");

        vidaDrone.setTextFill(Color.WHITE);
        vidaDrone.setFont(Font.font(20));
        vidaDroneValue.setText("\t" +  + modeloObs.getTerreno().getDrone().getVida() + " / "
                        + modeloObs.getTerreno().getDrone().getMaxVida());
        
        vidaDroneValue.setTextFill(Color.BLACK);
        vidaDroneBar.setStyle("-fx-accent: red");
        
        droneVida.getChildren().addAll(vidaDroneBar, vidaDroneValue);
        droneVidaBox.getChildren().addAll(vidaDrone, droneVida);

        dronePosX.setTextFill(Color.WHITE);
        dronePosX.setFont(Font.font(20));
        dronePosY.setTextFill(Color.WHITE);
        dronePosY.setFont(Font.font(20));
        
        droneRecurso.setTextFill(Color.WHITE);
        droneRecurso.setFont(Font.font(20));
        
        dronePosXBox.getChildren().add(dronePosX);
        dronePosYBox.getChildren().add(dronePosY);
        
        if (modeloObs.getTerreno().getDrone().getRes() == null)
            droneRecursoImage.setImage(Imagens.getImagem(NO_RESOURCE));

        else {
            switch (modeloObs.getTerreno().getDrone().getRes().getTipo()) {

                case "Verde":
                    droneRecursoImage.setImage(Imagens.getImagem(GREEN_RESOURCE));
                    break;

                case "Preto":
                    droneRecursoImage.setImage(Imagens.getImagem(BLACK_RESOURCE));
                    break;

                case "Vermelho":
                    droneRecursoImage.setImage(Imagens.getImagem(RED_RESOURCE));
                    break;

                case "Azul":
                    droneRecursoImage.setImage(Imagens.getImagem(BLUE_RESOURCE));
                    break;

                default:
                    droneRecursoImage.setImage(Imagens.getImagem(NO_RESOURCE));
                    break;
            }
        }

        droneRecursoBox.getChildren().addAll(droneRecurso, droneRecursoImage);
        
        droneBox.setAlignment(Pos.CENTER);
        droneBox.setPadding(new Insets(10, 0, 5, 50));
        droneBox.getChildren().addAll(droneLabel, dronePosXBox, dronePosYBox, droneVidaBox, droneRecursoBox);
        
        //Alien Info Stuff
        VBox alienBox = new VBox(15);
        Group alienVida = new Group();
        HBox alienPosXBox = new HBox();
        HBox alienPosYBox = new HBox();
        HBox alienVidaBox = new HBox();
        HBox alienTypeBox = new HBox();
        Label alienLabel = new Label("Alien");
        
        alienLabel.setTextFill(Color.WHITE);
        alienLabel.setFont(Font.font(20));
        alienLabel.setTranslateX(50);
        
        alienPosX.setText("Pos X: " + modeloObs.getTerreno().getAlienAttacker().getPosX());
        alienPosY.setText("Pos Y: " + modeloObs.getTerreno().getAlienAttacker().getPosY());
        Label vidaAlien = new Label("Vida: " );
        Label tipoAlien = new Label("Tipo: ");
        
        vidaAlien.setTextFill(Color.WHITE);
        vidaAlien.setFont(Font.font(20));
        tipoAlien.setTextFill(Color.WHITE);
        tipoAlien.setFont(Font.font(20));
        vidaAlienValue.setText("\t" + modeloObs.getTerreno().getAlienAttacker().getVida() + " / " + "1");
        vidaAlienValue.setTextFill(Color.BLACK);
        vidaAlienBar.setStyle("-fx-accent: red");
        
        alienVida.getChildren().addAll(vidaAlienBar, vidaAlienValue);
        alienVidaBox.getChildren().addAll(vidaAlien, alienVida);
        
        alienPosX.setTextFill(Color.WHITE);
        alienPosX.setFont(Font.font(20));
        alienPosY.setTextFill(Color.WHITE);
        alienPosY.setFont(Font.font(20));
        
        alienPosXBox.getChildren().add(alienPosX);
        alienPosYBox.getChildren().add(alienPosY);
        
        switch(modeloObs.getTerreno().getAlienAttacker().getTipo()){
            
            case "Verde":
                
                alienTypeImage.setImage(Imagens.getImagem(GREEN_RESOURCE));
                break;
                
            case "Preto":
                
                alienTypeImage.setImage(Imagens.getImagem(BLACK_RESOURCE));
                break;
                
            case "Vermelho":
                
                alienTypeImage.setImage(Imagens.getImagem(RED_RESOURCE));
                break;
                
            case "Azul":
                
                alienTypeImage.setImage(Imagens.getImagem(BLUE_RESOURCE));
                break;
            
        }
        
        alienTypeBox.getChildren().addAll(tipoAlien, alienTypeImage);
        
        alienBox.setPadding(new Insets(10, 0, 5, 50));
        alienBox.getChildren().addAll(alienLabel, alienPosXBox, alienPosYBox, alienVidaBox, alienTypeBox);
        
        //Recurso Info Stuff
        
        VBox recursoBox = new VBox(15);
        HBox recursoPosXBox = new HBox();
        HBox recursoPosYBox = new HBox();
        HBox recursoTypeBox = new HBox();
        Label recursoLabel = new Label("Recurso");
        
        recursoLabel.setTextFill(Color.WHITE);
        recursoLabel.setFont(Font.font(20));
        recursoLabel.setTranslateX(50);
        
        recursoPosX.setText("Pos X: " + modeloObs.getTerreno().getResource().getPosX());
        recursoPosY.setText("Pos Y: " + modeloObs.getTerreno().getResource().getPosY());
        tipoRecurso.setText("Tipo: ");
        
        recursoPosX.setTextFill(Color.WHITE);
        recursoPosX.setFont(Font.font(20));
        recursoPosY.setTextFill(Color.WHITE);
        recursoPosY.setFont(Font.font(20));
        tipoRecurso.setTextFill(Color.WHITE);
        tipoRecurso.setFont(Font.font(20));
        
        recursoPosXBox.getChildren().add(recursoPosX);
        recursoPosYBox.getChildren().add(recursoPosY);
        
        switch(modeloObs.getJogo().getPlaneta().getTipo()){
            
            case "Verde":
                
                recursoImage.setImage(Imagens.getImagem(GREEN_RESOURCE));
                break;
                
            case "Preto":

                recursoImage.setImage(Imagens.getImagem(BLACK_RESOURCE));
                break;   
                
            case "Vermelho":

                recursoImage.setImage(Imagens.getImagem(RED_RESOURCE));
                break;
                
            case "Azul":

                recursoImage.setImage(Imagens.getImagem(BLUE_RESOURCE));
                break;
 
        }
        
        recursoTypeBox.getChildren().addAll(tipoRecurso, recursoImage);
        
        recursoBox.setPadding(new Insets(10, 0, 5, 50));
        recursoBox.getChildren().addAll(recursoLabel, recursoPosXBox, recursoPosYBox, recursoTypeBox);
        
        
        topBar.setSpacing(70);
        topBar.getChildren().addAll(planetBox, droneBox, alienBox, recursoBox);
        
        setTop(topBar);
        
    }
    
    private void organizaMapa(){
        
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
 
                tileImage[i][j] = new ImageView(Imagens.getImagem(DESERT_TILE));              
                mapa.add(tileImage[i][j], i, j);
            }
        }
        
        mapa.setAlignment(Pos.CENTER);
        setCenter(mapa);
        
    }
    
    private void organizaLuta(){
            
        setCenter(null);

    }
    
    private void atualizaVista() {
        InteracaoEsperada ie = modeloObs.getInteracaoEsperada();
        setVisible(ie == InteracaoEsperada.INTERACAO_WAIT_LANDING);
        
        //DEBUG
        System.out.println(count);
        //PLANETA INFO STUFF
        if (count < 6) {
            switch (modeloObs.getPlaneta().getTipo()) {

                case "Verde":

                    planetImage.setImage(Imagens.getImagem(GREEN_RESOURCE));
                    recursosPlanetaBox.getChildren().setAll(vermelhoR, verdeR);
                    count++;

                    break;

                case "Preto":

                    planetImage.setImage(Imagens.getImagem(BLACK_RESOURCE));
                    recursosPlanetaBox.getChildren().setAll(planetResources, pretoR, azulR);
                    count++;

                    break;

                case "Vermelho":

                    planetImage.setImage(Imagens.getImagem(RED_RESOURCE));
                    recursosPlanetaBox.getChildren().setAll(planetResources, vermelhoR, azulR);
                    count++;

                    break;

                case "Azul":

                    planetImage.setImage(Imagens.getImagem(BLUE_RESOURCE));
                    recursosPlanetaBox.getChildren().setAll(planetResources, pretoR, verdeR, azulR, artifact);
                    count++;

                    break;
            }
        } else {}
        
        planetTypeBox.getChildren().set(1, planetImage);
                
        //DRONE INFO STUFF
        if (modeloObs.getTerreno().getDrone().getRes() == null){
            droneRecursoImage.setImage(Imagens.getImagem(NO_RESOURCE));
            recursoImage.setVisible(true);
            
        }else {
            switch (modeloObs.getTerreno().getDrone().getRes().getTipo()) {

                case "Verde":
                    droneRecursoImage.setImage(Imagens.getImagem(GREEN_RESOURCE));
                    recursoImage.setVisible(false);
                    recursosPlanetaBox.getChildren().remove(verdeR);
                    break;

                case "Preto":
                    droneRecursoImage.setImage(Imagens.getImagem(BLACK_RESOURCE));
                    recursoImage.setVisible(false);
                    recursosPlanetaBox.getChildren().remove(pretoR);
                    break;

                case "Vermelho":
                    droneRecursoImage.setImage(Imagens.getImagem(RED_RESOURCE));
                    recursoImage.setVisible(false);
                    recursosPlanetaBox.getChildren().remove(vermelhoR);
                    break;

                case "Azul":
                    droneRecursoImage.setImage(Imagens.getImagem(BLUE_RESOURCE));
                    recursoImage.setVisible(false);
                    recursosPlanetaBox.getChildren().remove(azulR);
                    break;

                default:
                    droneRecursoImage.setImage(Imagens.getImagem(NO_RESOURCE));
                    recursoImage.setVisible(false);
                    recursosPlanetaBox.getChildren().remove(artifact);
                    break;
            }
        }
        
        dronePosX.setText("Pos X: " + modeloObs.getTerreno().getDrone().getPosX());
        dronePosY.setText("Pos Y: " + modeloObs.getTerreno().getDrone().getPosY());
        vidaDroneValue.setText("\t" +  + modeloObs.getTerreno().getDrone().getVida() + " / "
                        + modeloObs.getTerreno().getDrone().getMaxVida());
        
        vidaDroneBar.setProgress((Math.floor((modeloObs.getTerreno().getDrone().getVida() * 100)
                                                / modeloObs.getTerreno().getDrone().getMaxVida()) / 100));
        
        if((modeloObs.getTerreno().getDrone().getPosX() == modeloObs.getJogo().getInitialDroneX())
                        && (modeloObs.getTerreno().getDrone().getPosY() == modeloObs.getJogo().getInitialDroneY())
                        && modeloObs.getTerreno().getDrone().getRes() != null){
            
            returnHome.setDisable(false);
            
        }else
            returnHome.setDisable(true);
        
        //ALIEN INFO STUFF
        
        switch(modeloObs.getTerreno().getAlienAttacker().getTipo()){
            
            case "Verde":
                
                alienTypeImage.setImage(Imagens.getImagem(GREEN_RESOURCE));
                break;
                
            case "Preto":
                
                alienTypeImage.setImage(Imagens.getImagem(BLACK_RESOURCE));
                break;
                
            case "Vermelho":
                
                alienTypeImage.setImage(Imagens.getImagem(RED_RESOURCE));
                break;
                
            case "Azul":
                
                alienTypeImage.setImage(Imagens.getImagem(BLUE_RESOURCE));
                break;
            
        }
        
        alienPosX.setText("Pos X: " + modeloObs.getTerreno().getAlienAttacker().getPosX());
        alienPosY.setText("Pos Y: " + modeloObs.getTerreno().getAlienAttacker().getPosY());
        
        vidaAlienBar.setProgress((Math.floor((modeloObs.getTerreno().getAlienAttacker().getVida() * 100)
                                                / 1) / 100));
        
        //RECURSO INFO STUFF
        switch(modeloObs.getJogo().getPlaneta().getTerreno().getResource().getTipo()){
            
            case "Verde":
                
                recursoImage.setImage(Imagens.getImagem(GREEN_RESOURCE));
                break;
                
            case "Preto":

                recursoImage.setImage(Imagens.getImagem(BLACK_RESOURCE));
                break;   
                
            case "Vermelho":

                recursoImage.setImage(Imagens.getImagem(RED_RESOURCE));
                break;
                
            case "Azul":

                recursoImage.setImage(Imagens.getImagem(BLUE_RESOURCE));
                break;
 
        }
        
        recursoPosX.setText("Pos X: " + modeloObs.getTerreno().getResource().getPosX());
        recursoPosY.setText("Pos Y: " + modeloObs.getTerreno().getResource().getPosY());
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                
                if(modeloObs.getTerreno().getEntityInPosition(i, j) instanceof Alien){
                    tileImage[i][j].setImage(Imagens.getImagem(ALIEN));
                                        
                }else if(modeloObs.getTerreno().getEntityInPosition(i, j) instanceof Drone){
                    
                    tileImage[i][j].setImage(Imagens.getImagem(DRONE));
                    
                }else if(i == modeloObs.getJogo().getInitialDroneX() && j == modeloObs.getJogo().getInitialDroneY()){
                    
                    tileImage[i][j].setImage(Imagens.getImagem(INITIAL));
                    
                }else if(modeloObs.getTerreno().getEntityInPosition(i, j) instanceof Resource){
                    
                    tileImage[i][j].setImage(recursoImage.getImage());
                    
                }else{
                    tileImage[i][j].setImage(Imagens.getImagem(DESERT_TILE));
                    
                }
            }
        }       
        
        if (modeloObs.getTerreno().checkFight()) {
            //FAZER UM BOTAO!
            modeloObs.alienAttack();

            if (modeloObs.getTerreno().getDrone().getVida() == 0) {
                Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
                dialogoResultado.setHeaderText("Alien Fight");
                dialogoResultado.setContentText("O drone morreu!");
                dialogoResultado.showAndWait();

            } else {
                Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
                dialogoResultado.setHeaderText("Alien Fight");
                dialogoResultado.setContentText("O alien morreu!");
                dialogoResultado.showAndWait();

            }

        }
        
    }
    
    
}
