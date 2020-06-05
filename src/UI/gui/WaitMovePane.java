package UI.gui;

import Logica.InteracaoEsperada;
import Logica.ObservableGame;
import Logica.dados.variations.MilitaryShip;
import static UI.gui.Constants.*;
import java.beans.PropertyChangeEvent;
import javafx.animation.PathTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;


public class WaitMovePane extends BorderPane{

    private ObservableGame modeloObs;
    private int count = 1;
    
    private ImageView spaceShip;
    private Circle initialCircle;
    private Circle planetaOrEvento;
    private Group mapa;
    private Path caminho;
    private PathTransition pathTransition;
    
    public WaitMovePane(ObservableGame ob){
        this.modeloObs = ob;
        this.modeloObs.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            atualizaVista();
            
        });
        
        mapa = new Group();
        
        organizaComponentes();
        atualizaVista();
        
    }
    
    private void organizaComponentes(){

        organizaTopBar();
//        organizaMapa();
        
        HBox buttonPane = new HBox();
        Button movimento = new Button("Movimento");
        
        movimento.setOnAction((event) -> {
            modeloObs.move();

        });
         
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(20);
        buttonPane.setPrefSize(100, 200);

        buttonPane.getChildren().addAll(movimento);
        
        setBottom(buttonPane);
      
    }
    
    private void organizaTopBar(){
        
        setTop(null);
        HBox topBar = new HBox();    
        
        //Spaceship Stuff
        VBox spaceShip = new VBox();
        HBox droneBox = new HBox();
        HBox artifactBox = new HBox();
        ImageView droneImage;
        ImageView artifactImage;
        
        //Crew Member Stuff
        VBox crewBox = new VBox(30);
        crewBox.setAlignment(Pos.TOP_CENTER);
        
        HBox crewMemberBox = new HBox();
        Label crewLabel = new Label("Crew Members");
        crewLabel.setFont(Font.font(25));
        crewLabel.setTextFill(Color.WHITE);
        
        ImageView captainCrew;
        ImageView navigationCrew;
        ImageView landingCrew;
        ImageView shieldCrew;
        ImageView weaponCrew;
        ImageView cargoCrew;
        
        switch(modeloObs.getJogo().getSpaceShip().getCrewMembers().size()){
            
            case 1:
                captainCrew = new ImageView(Imagens.getImagem(CAPTAIN_CREW));
                captainCrew.setFitHeight(50);
                captainCrew.setFitWidth(50);
                
                crewMemberBox.getChildren().add(captainCrew);
                break;
                
            case 2:
                captainCrew = new ImageView(Imagens.getImagem(CAPTAIN_CREW));
                captainCrew.setFitHeight(50);
                captainCrew.setFitWidth(50);
                
                navigationCrew = new ImageView(Imagens.getImagem(NAVIGATION_CREW));
                navigationCrew.setFitHeight(50);
                navigationCrew.setFitWidth(50);
                
                crewMemberBox.getChildren().addAll(captainCrew, navigationCrew);
                
                break;
                
            case 3:
                captainCrew = new ImageView(Imagens.getImagem(CAPTAIN_CREW));
                captainCrew.setFitHeight(50);
                captainCrew.setFitWidth(50);
                
                navigationCrew = new ImageView(Imagens.getImagem(NAVIGATION_CREW));
                navigationCrew.setFitHeight(50);
                navigationCrew.setFitWidth(50);
                
                landingCrew = new ImageView(Imagens.getImagem(LANDING_CREW));
                landingCrew.setFitHeight(50);
                landingCrew.setFitWidth(50);
                
                crewMemberBox.getChildren().addAll(captainCrew, navigationCrew, landingCrew);
                
                break;
                
            case 4:
                captainCrew = new ImageView(Imagens.getImagem(CAPTAIN_CREW));
                captainCrew.setFitHeight(50);
                captainCrew.setFitWidth(50);
                
                navigationCrew = new ImageView(Imagens.getImagem(NAVIGATION_CREW));
                navigationCrew.setFitHeight(50);
                navigationCrew.setFitWidth(50);
                
                landingCrew = new ImageView(Imagens.getImagem(LANDING_CREW));
                landingCrew.setFitHeight(50);
                landingCrew.setFitWidth(50);
                
                shieldCrew = new ImageView(Imagens.getImagem(SHIELD_CREW));
                shieldCrew.setFitHeight(50);
                shieldCrew.setFitWidth(50);
                
                crewMemberBox.getChildren().addAll(captainCrew, navigationCrew, landingCrew, shieldCrew);
                
                break;
                
            case 5:
                captainCrew = new ImageView(Imagens.getImagem(CAPTAIN_CREW));
                captainCrew.setFitHeight(50);
                captainCrew.setFitWidth(50);
                
                navigationCrew = new ImageView(Imagens.getImagem(NAVIGATION_CREW));
                navigationCrew.setFitHeight(50);
                navigationCrew.setFitWidth(50);
                
                landingCrew = new ImageView(Imagens.getImagem(LANDING_CREW));
                landingCrew.setFitHeight(50);
                landingCrew.setFitWidth(50);
                
                shieldCrew = new ImageView(Imagens.getImagem(SHIELD_CREW));
                shieldCrew.setFitHeight(50);
                shieldCrew.setFitWidth(50);
                
                weaponCrew = new ImageView(Imagens.getImagem(WEAPON_CREW));
                weaponCrew.setFitHeight(50);
                weaponCrew.setFitWidth(50);
                
                crewMemberBox.getChildren().addAll(captainCrew, navigationCrew, landingCrew, shieldCrew, weaponCrew);
                
                break;
                
            case 6:
                captainCrew = new ImageView(Imagens.getImagem(CAPTAIN_CREW));
                captainCrew.setFitHeight(50);
                captainCrew.setFitWidth(50);
                
                navigationCrew = new ImageView(Imagens.getImagem(NAVIGATION_CREW));
                navigationCrew.setFitHeight(50);
                navigationCrew.setFitWidth(50);
                
                landingCrew = new ImageView(Imagens.getImagem(LANDING_CREW));
                landingCrew.setFitHeight(50);
                landingCrew.setFitWidth(50);
                
                shieldCrew = new ImageView(Imagens.getImagem(SHIELD_CREW));
                shieldCrew.setFitHeight(50);
                shieldCrew.setFitWidth(50);
                
                weaponCrew = new ImageView(Imagens.getImagem(WEAPON_CREW));
                weaponCrew.setFitHeight(50);
                weaponCrew.setFitWidth(50);
                
                cargoCrew = new ImageView(Imagens.getImagem(CARGO_CREW));
                cargoCrew.setFitHeight(50);
                cargoCrew.setFitWidth(50);
                
                crewMemberBox.getChildren().addAll(captainCrew, navigationCrew, landingCrew, shieldCrew, weaponCrew, cargoCrew);
                
                break;
                 
        }
        
        crewBox.getChildren().addAll(crewLabel, crewMemberBox);
                
        //Cargo Stuff
        VBox cargoBox = new VBox(15);
        HBox redCargoBox = new HBox();
        HBox blueCargoBox = new HBox();
        HBox blackCargoBox = new HBox();
        HBox greenCargoBox = new HBox();
        
        Group redGroup = new Group();
        Group blueGroup = new Group();
        Group blackGroup = new Group();
        Group greenGroup = new Group();
        
        modeloObs.getJogo().getSpaceShip().addCargo(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Vermelho"), 6);
        modeloObs.getJogo().getSpaceShip().addCargo(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Azul"), 6);
        modeloObs.getJogo().getSpaceShip().addCargo(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Preto"), 6);
        modeloObs.getJogo().getSpaceShip().addCargo(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Verde"), 6);
        
                
        //RED CARGO STUFF
        Label redCargoText = new Label("Red Cargo ");
        redCargoText.setTextFill(Color.WHITE);
        redCargoText.setFont(Font.font(20));
        
        Label redCargoValue = new Label("\t" + modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Vermelho")) 
                + " / " + modeloObs.getJogo().getSpaceShip().getMaxCargo());
        redCargoValue.setTextFill(Color.BLACK);

        ProgressBar redCargoProgress = new ProgressBar();
        redCargoProgress.setProgress((Math.floor((modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Vermelho"))
                * 100) / modeloObs.getJogo().getSpaceShip().getMaxCargo()) / 100));
        
        redCargoProgress.setStyle("-fx-accent: red");
        
        redGroup.getChildren().addAll(redCargoProgress, redCargoValue);
        redCargoBox.setAlignment(Pos.CENTER);
        
        //BLUE CARGO STUFF
        Label blueCargoText = new Label("Blue Cargo ");
        blueCargoText.setTextFill(Color.WHITE);
        blueCargoText.setFont(Font.font(20));
        
        Label blueCargoValue = new Label("\t" + modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Azul")) 
                + " / " + modeloObs.getJogo().getSpaceShip().getMaxCargo());
        blueCargoValue.setTextFill(Color.BLACK);

        ProgressBar blueCargoProgress = new ProgressBar();
        blueCargoProgress.setProgress((Math.floor((modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Azul"))
                * 100) / modeloObs.getJogo().getSpaceShip().getMaxCargo()) / 100));
        
        blueCargoProgress.setStyle("-fx-accent: blue");
        
        blueGroup.getChildren().addAll(blueCargoProgress, blueCargoValue);
        blueCargoBox.setAlignment(Pos.CENTER);
        
        //BLACK CARGO STUFF
        Label blackCargoText = new Label("Black Cargo ");
        blackCargoText.setTextFill(Color.WHITE);
        blackCargoText.setFont(Font.font(20));
        
        Label blackCargoValue = new Label("\t" + modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Preto")) 
                + " / " + modeloObs.getJogo().getSpaceShip().getMaxCargo());
        blackCargoValue.setTextFill(Color.GREEN);
        

        ProgressBar blackCargoProgress = new ProgressBar();
        blackCargoProgress.setProgress((Math.floor((modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Preto"))
                * 100) / modeloObs.getJogo().getSpaceShip().getMaxCargo()) / 100));
        
        blackCargoProgress.setStyle("-fx-accent: black");
        
        blackGroup.getChildren().addAll(blackCargoProgress, blackCargoValue);
        blackCargoBox.setAlignment(Pos.CENTER);
        
        //GREEN CARGO STUFF
        Label greenCargoText = new Label("Green Cargo ");
        greenCargoText.setTextFill(Color.WHITE);
        greenCargoText.setFont(Font.font(20));
        
        Label greenCargoValue = new Label("\t" + modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Verde")) 
                + " / " + modeloObs.getJogo().getSpaceShip().getMaxCargo());
        greenCargoValue.setTextFill(Color.BLACK);

        ProgressBar greenCargoProgress = new ProgressBar();
        greenCargoProgress.setProgress((Math.floor((modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Verde"))
                * 100) / modeloObs.getJogo().getSpaceShip().getMaxCargo()) / 100));
        
        greenCargoProgress.setStyle("-fx-accent: green");
        
        greenGroup.getChildren().addAll(greenCargoProgress, greenCargoValue);
        greenCargoBox.setAlignment(Pos.CENTER);

        //Putting Everything in order
        redCargoBox.getChildren().addAll(redCargoText, redGroup);
        blueCargoBox.getChildren().addAll(blueCargoText, blueGroup);
        blackCargoBox.getChildren().addAll(blackCargoText, blackGroup);
        greenCargoBox.getChildren().addAll(greenCargoText, greenGroup);
                        
        cargoBox.getChildren().addAll(redCargoBox, blueCargoBox, blackCargoBox, greenCargoBox);
        
        Label fuel = new Label("Fuel: " + modeloObs.getFuelStorage() + " / " + modeloObs.getMaxFuelStorage());
        Label weapon = new Label("Weapon: " + modeloObs.getWeaponSystem() + " / " + modeloObs.getMaxWeaponSystem());
        Label shield = new Label("Shield: " + modeloObs.getShieldSystem() + " / " + modeloObs.getMaxShieldSystem());
        Label drone = new Label("Drone: ");
        Label artifacts = new Label("Artifacts: ");
        
        if(modeloObs.getJogo().getSpaceShip().getDrone() != null){
            droneImage = new ImageView(Imagens.getImagem(DRONE));
        }else
            droneImage = new ImageView(Imagens.getImagem(NO_DRONE));

        
        fuel.setFont(Font.font(20));
        fuel.setTextFill(Color.WHITE);
        fuel.setPadding(new Insets(5, 0, 5, 10));
        
        weapon.setFont(Font.font(20));
        weapon.setTextFill(Color.WHITE);
        weapon.setPadding(new Insets(5, 0, 5, 10));
        
        shield.setFont(Font.font(20));
        shield.setTextFill(Color.WHITE);
        shield.setPadding(new Insets(5, 0, 5, 10));
        
        drone.setFont(Font.font(20));
        drone.setTextFill(Color.WHITE);
        drone.setPadding(new Insets(5, 0, 5, 10));
        
        artifacts.setFont(Font.font(20));
        artifacts.setTextFill(Color.WHITE);
        artifacts.setPadding(new Insets(5, 0, 5, 10));
        
//        modeloObs.getJogo().getSpaceShip().addArtifact();
        
        artifactBox.getChildren().add(artifacts);
        
        for(int i = 0; i < modeloObs.getArtifactsNumber(); i++){
            artifactImage = new ImageView(Imagens.getImagem(Constants.ARTIFACT));
            artifactImage.setFitHeight(35);
            artifactImage.setFitWidth(35);
            artifactBox.getChildren().add(artifactImage);
        }

        droneBox.getChildren().addAll(drone, droneImage);
        
        spaceShip.getChildren().addAll(fuel, weapon, shield, droneBox, artifactBox);        
      
        topBar.setSpacing(100);
        
        topBar.getChildren().addAll(spaceShip, crewBox, cargoBox);
        
        setTop(topBar);
        
    }
        
    private void drawCircle(){
        
        if (modeloObs.getJogo().getWasPlanet()) {
            planetaOrEvento = new Circle(25);
            planetaOrEvento.setOpacity(0.5);
            planetaOrEvento.setVisible(false);

        } else {
            if (modeloObs.getJogo().getPlaneta().getSpaceStation() != null) {
                planetaOrEvento = new Circle(50);
                planetaOrEvento.setVisible(false);
                planetaOrEvento.setFill(Color.TRANSPARENT);
                planetaOrEvento.setStroke(Color.RED);
                planetaOrEvento.setStrokeWidth(2);

            } else {
                planetaOrEvento = new Circle(50);
                planetaOrEvento.setVisible(false);
                planetaOrEvento.setFill(Color.TRANSPARENT);
                planetaOrEvento.setStroke(Color.WHITE);
                planetaOrEvento.setStrokeWidth(2);

            }
        }
        
        switch (count) {
            case 1: //Primeira vez que passa
                caminho = new Path();
                pathTransition = new PathTransition();

                caminho.getElements().add(new MoveTo(initialCircle.getLayoutX(), initialCircle.getLayoutY()));
                caminho.getElements().add(new LineTo(initialCircle.getLayoutX() + 150, 0));
//                caminho.getElements().add(new LineTo(initialCircle.getLayoutX() + 200, 0));
//                caminho.getElements().add(new HLineTo(200));
                caminho.setStroke(Color.WHITE);
                caminho.setStrokeWidth(2);

                pathTransition.setDuration(Duration.seconds(2));
                pathTransition.setPath(caminho);
                pathTransition.setNode(spaceShip);
                pathTransition.setOrientation(PathTransition.OrientationType.NONE);
                pathTransition.play();

                pathTransition.setOnFinished((event) -> {

                    planetaOrEvento.setTranslateX(150);
                    planetaOrEvento.setVisible(true);
                });

                mapa.getChildren().setAll(initialCircle, caminho, spaceShip, planetaOrEvento);
                count++;
                break;
                
            case 2:
                Path novoCaminho = new Path();
                PathTransition novoPathTransition = new PathTransition();

                novoCaminho.getElements().add(new MoveTo(planetaOrEvento.getLayoutX() + 150, planetaOrEvento.getLayoutY()));
                novoCaminho.getElements().add(new LineTo(planetaOrEvento.getLayoutX() + 200, 0));
                novoCaminho.setStroke(Color.WHITE);
                novoCaminho.setStrokeWidth(2);

                novoPathTransition.setDuration(Duration.seconds(2));
                novoPathTransition.setPath(novoCaminho);
                novoPathTransition.setNode(spaceShip);
                novoPathTransition.setOrientation(PathTransition.OrientationType.NONE);
                novoPathTransition.play();

                novoPathTransition.setOnFinished((event) -> {

                    planetaOrEvento.setTranslateX(300);
                    planetaOrEvento.setVisible(true);
                });

                mapa.getChildren().set(2, planetaOrEvento);
                count++;
                break; 
        }
    }
    
    private void moveSpaceShip(ImageView spaceShip){
        
//        TranslateTransition moveSpaceShipX = new TranslateTransition();
//        moveSpaceShipX.setByX(200);
//        moveSpaceShipX.setRate(0.5);
//        moveSpaceShipX.setDuration(Duration.seconds(2));
//        moveSpaceShipX.setNode(spaceShip);
//        
//        moveSpaceShipX.play();
        
    }
    
    private void atualizaVista(){
        InteracaoEsperada ie = modeloObs.getInteracaoEsperada();
        setVisible(ie == InteracaoEsperada.INTERACAO_WAIT_MOVE);
        
    }
    
}
