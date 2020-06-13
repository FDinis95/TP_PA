package UI.gui;

import Logica.InteracaoEsperada;
import Logica.ObservableGame;
import Logica.dados.variations.MilitaryShip;
import static UI.gui.Constants.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class WaitEventPane extends BorderPane {

    private ObservableGame modeloObs;
    private Button rollDice = new Button("Lançar o dado");
    private Button rollDiceManually = new Button("Lançar o dado manualmente");
    private Button continua = new Button("Continua");
    private Label spaceShipType = new Label();
    private int ID = 0;
    private TextField IDText = new TextField();
    
    
    //SPACESHIP INFO STUFF
    private Label fuel = new Label();
    private Label weapon = new Label();
    private Label shield = new Label();
    private Label drone = new Label();
    private Label artifacts = new Label();
    private ImageView droneImage = new ImageView(Imagens.getImagem(DRONE));
    private ArrayList<ImageView> artifactImage = new ArrayList<>();
    
    //CREWMEMBERS IMAGES
    private ImageView captainCrew = new ImageView(Imagens.getImagem(CAPTAIN_CREW));
    private ImageView navigationCrew = new ImageView(Imagens.getImagem(NAVIGATION_CREW));
    private ImageView landingCrew = new ImageView(Imagens.getImagem(LANDING_CREW));
    private ImageView shieldCrew = new ImageView(Imagens.getImagem(SHIELD_CREW));
    private ImageView weaponCrew = new ImageView(Imagens.getImagem(WEAPON_CREW));
    private ImageView cargoCrew = new ImageView(Imagens.getImagem(CARGO_CREW));
    
    //CARGO INFO STUFF
    private Label redCargoValue = new Label();
    private Label blueCargoValue = new Label();
    private Label blackCargoValue = new Label();
    private Label greenCargoValue = new Label();
    
    private ProgressBar redCargoProgress = new ProgressBar(0);
    private ProgressBar blueCargoProgress = new ProgressBar(0);
    private ProgressBar blackCargoProgress = new ProgressBar(0);
    private ProgressBar greenCargoProgress = new ProgressBar(0);
    
    private ImageView dice = new ImageView(Imagens.getImagem(DIE_ANIMATION));
    
    public WaitEventPane(ObservableGame modeloObs) {
        this.modeloObs = modeloObs;
        this.modeloObs.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            atualizaVista();

        });
        
        organizaComponentes();
        atualizaVista();
    }
    
    private void organizaComponentes(){
        
        organizaTopBar();
        organizaLeftBar();
        
        VBox centerPane = new VBox();
        HBox choiceBox = new HBox();
        
        rollDice.setOnAction((event) -> {
            
            modeloObs.rollD6();
            updateDiceImage();
            rollDice.setDisable(true);
            continua.setDisable(false);
            
        });
        
        rollDiceManually.setOnAction((event) -> {
            organizaRespostaUtilizador();
            
            
            
        });
        
        continua.setOnAction((event) -> {
            
            if(ID == 0){
                modeloObs.continua();
                
            }else{
                modeloObs.rollD6(ID);
                rollDice.setDisable(true);
                rollDiceManually.setDisable(true);
                ID = 0;
            }      
        });

        continua.setDisable(true);

        choiceBox.setAlignment(Pos.CENTER);
        choiceBox.getChildren().addAll(rollDice, rollDiceManually);
        
        centerPane.setAlignment(Pos.CENTER);
        centerPane.setSpacing(20);
        centerPane.setPrefSize(100, 200);

        centerPane.getChildren().addAll(dice, choiceBox, continua);


        setCenter(centerPane);
        
    }
    
    private void organizaRespostaUtilizador(){
        setCenter(null);
        
        HBox choicesBox = new HBox();
        VBox IDBox = new VBox();
        
        TextArea rollExplanation = new TextArea("Escolha um dos possiveis ID's da imagem ao lado");
        rollExplanation.setPrefSize(200, 100);
                       
        IDText.setPadding(new Insets(10));
        
        IDText.setOnAction((event) -> {
            ID = Integer.parseInt(IDText.getText());
            organizaComponentes();
            rollDice.setDisable(true);
            rollDiceManually.setDisable(true);
            continua.setDisable(false);
            
        });
        
        IDBox.getChildren().addAll(rollExplanation, IDText);
        
        choicesBox.setAlignment(Pos.CENTER);
        choicesBox.getChildren().add(IDBox);
        setCenter(choicesBox);
    }
    
    private void updateDiceImage() {
        
        switch (modeloObs.getJogo().getEvento().getID()) {
            
            case 1:
                dice.setImage(Imagens.getImagem(DIE1));
                break;
                
            case 2:
                dice.setImage(Imagens.getImagem(DIE2));
                break;
                
            case 3:
                dice.setImage(Imagens.getImagem(DIE3));
                break;
                
            case 4:
                dice.setImage(Imagens.getImagem(DIE4));
                break;
                
            case 5:
                dice.setImage(Imagens.getImagem(DIE5));
                break;
                
            case 6:
                dice.setImage(Imagens.getImagem(DIE6));
                break;
                
            default:
                dice.setImage(null); // should never occur
                break;
        }
    }
    
    private void organizaTopBar(){
        
        HBox topBar = new HBox();    
        
        //Spaceship Stuff
        VBox spaceShip = new VBox();
        HBox droneBox = new HBox();
        HBox artifactBox = new HBox();
        
        
        
        //Crew Member Stuff
        VBox crewBox = new VBox(15);
        crewBox.setAlignment(Pos.TOP_CENTER);
        
        HBox crewMemberBox = new HBox();
        Label crewLabel = new Label("Crew Members");
        crewLabel.setFont(Font.font(25));
        crewLabel.setTextFill(Color.WHITE);

        captainCrew.setFitHeight(50);
        captainCrew.setFitWidth(50);
        
        navigationCrew.setFitHeight(50);
        navigationCrew.setFitWidth(50);
        
        landingCrew.setFitHeight(50);
        landingCrew.setFitWidth(50);

        shieldCrew.setFitHeight(50);
        shieldCrew.setFitWidth(50);

        weaponCrew.setFitHeight(50);
        weaponCrew.setFitWidth(50);

        cargoCrew.setFitHeight(50);
        cargoCrew.setFitWidth(50);
                
        crewMemberBox.getChildren().addAll(captainCrew, navigationCrew, landingCrew, shieldCrew, weaponCrew, cargoCrew);

        crewBox.setPadding(new Insets(15, 0, 5, 0));
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
        
        
        Label cargoLabel = new Label("Cargo");
        cargoLabel.setFont(Font.font(25));
        cargoLabel.setTextFill(Color.WHITE);
        cargoLabel.setTranslateX(50);
        
        //RED CARGO STUFF
        Label redCargoText = new Label("Red Cargo ");
        redCargoText.setTextFill(Color.WHITE);
        redCargoText.setFont(Font.font(20));
        
        redCargoValue.setText("\t" + modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Vermelho")) 
                + " / " + modeloObs.getJogo().getSpaceShip().getMaxCargo());
        redCargoValue.setTextFill(Color.BLACK);
        
        redCargoProgress.setStyle("-fx-accent: red");
        
        redGroup.getChildren().addAll(redCargoProgress, redCargoValue);
        redCargoBox.setAlignment(Pos.CENTER_LEFT);
        
        
        //BLUE CARGO STUFF
        Label blueCargoText = new Label("Blue Cargo ");
        blueCargoText.setTextFill(Color.WHITE);
        blueCargoText.setFont(Font.font(20));
        
        blueCargoValue.setText("\t" + modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Azul")) 
                + " / " + modeloObs.getJogo().getSpaceShip().getMaxCargo());
       
        
        blueCargoValue.setTextFill(Color.BLACK);
        blueCargoProgress.setStyle("-fx-accent: blue");
        
        blueGroup.getChildren().addAll(blueCargoProgress, blueCargoValue);
        blueCargoBox.setAlignment(Pos.CENTER_LEFT);
        
        //BLACK CARGO STUFF
        Label blackCargoText = new Label("Black Cargo ");
        blackCargoText.setTextFill(Color.WHITE);
        blackCargoText.setFont(Font.font(20));
        
        blackCargoValue.setText("\t" + modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Preto")) 
                + " / " + modeloObs.getJogo().getSpaceShip().getMaxCargo());
        blackCargoValue.setTextFill(Color.GREEN);  
        blackCargoProgress.setStyle("-fx-accent: black");
        
        blackGroup.getChildren().addAll(blackCargoProgress, blackCargoValue);
        blackCargoBox.setAlignment(Pos.CENTER_LEFT);
        
        //GREEN CARGO STUFF
        Label greenCargoText = new Label("Green Cargo ");
        greenCargoText.setTextFill(Color.WHITE);
        greenCargoText.setFont(Font.font(20));
        
        greenCargoValue.setText("\t" + modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Verde")) 
                + " / " + modeloObs.getJogo().getSpaceShip().getMaxCargo());
        greenCargoValue.setTextFill(Color.BLACK);
        greenCargoProgress.setStyle("-fx-accent: green");
        
        greenGroup.getChildren().addAll(greenCargoProgress, greenCargoValue);
        greenCargoBox.setAlignment(Pos.CENTER_LEFT);

        //Putting Everything in order
        redCargoBox.getChildren().addAll(redCargoText, redGroup);
        blueCargoBox.getChildren().addAll(blueCargoText, blueGroup);
        blackCargoBox.getChildren().addAll(blackCargoText, blackGroup);
        greenCargoBox.getChildren().addAll(greenCargoText, greenGroup);
                  
        cargoBox.setPadding(new Insets(15, 0, 5, 10));
        cargoBox.getChildren().addAll(cargoLabel, redCargoBox, blueCargoBox, blackCargoBox, greenCargoBox);
        
             
        //SpaceShip Info Stuff
        fuel.setText("Fuel: " + modeloObs.getFuelStorage() + " / " + modeloObs.getMaxFuelStorage());
        weapon.setText("Weapon: " + modeloObs.getWeaponSystem() + " / " + modeloObs.getMaxWeaponSystem());
        shield.setText("Shield: " + modeloObs.getShieldSystem()+ " / " + modeloObs.getMaxShieldSystem());
        drone.setText("Drone: ");
        artifacts.setText("Artifacts: ");
        
        if(modeloObs.getJogo().getSpaceShip().getDrone() != null){
            droneImage.setImage(Imagens.getImagem(DRONE));
        }else
            droneImage.setImage(Imagens.getImagem(NO_DRONE));

        
        fuel.setFont(Font.font(20));
        fuel.setTextFill(Color.WHITE);
        fuel.setPadding(new Insets(10, 0, 5, 0));
        
        weapon.setFont(Font.font(20));
        weapon.setTextFill(Color.WHITE);
        weapon.setPadding(new Insets(10, 0, 5, 0));
        
        shield.setFont(Font.font(20));
        shield.setTextFill(Color.WHITE);
        shield.setPadding(new Insets(10, 0, 5, 0));
        
        drone.setFont(Font.font(20));
        drone.setTextFill(Color.WHITE);
        drone.setPadding(new Insets(10, 0, 5, 0));
        
        artifacts.setFont(Font.font(20));
        artifacts.setTextFill(Color.WHITE);
        artifacts.setPadding(new Insets(10, 0, 5, 0));
                
        artifactBox.getChildren().add(artifacts);
        
        for(int i = 0; i < 5; i++){
            
            ImageView temp = new ImageView(Imagens.getImagem(ARTIFACT));
            temp.setFitHeight(35);
            temp.setFitWidth(35);
            temp.setVisible(false);
            
            artifactImage.add(temp);
            artifactBox.getChildren().add(artifactImage.get(i));
        }

        droneBox.getChildren().addAll(drone, droneImage);
        
        if(modeloObs.getJogo().getSpaceShip() instanceof MilitaryShip){
            spaceShipType.setText("Military Ship");
            spaceShipType.setFont(Font.font(25));
            spaceShipType.setTextFill(Color.WHITE);
            spaceShipType.setTranslateX(50);
            
        }else{
            spaceShipType.setText("MiningShip");
            spaceShipType.setFont(Font.font(25));
            spaceShipType.setTextFill(Color.WHITE);
            spaceShipType.setTranslateX(50);
            
        }
        
        spaceShip.setPadding(new Insets(10, 0, 5, 10));
        spaceShip.getChildren().addAll(spaceShipType, fuel, weapon, shield, droneBox, artifactBox);

        topBar.setSpacing(100);

        topBar.getChildren().addAll(spaceShip, crewBox, cargoBox);

        setTop(topBar);

    }
    
    private void organizaLeftBar(){
        
        HBox eventBox = new HBox();
        ImageView eventCard = new ImageView(Imagens.getImagem(EVENTS));
        eventCard.setFitWidth(300);
        eventCard.setFitHeight(300);
        
        
        eventBox.getChildren().add(eventCard);
        
        setLeft(eventBox);
    }
    
    private void atualizaVista() {
        InteracaoEsperada ie = modeloObs.getInteracaoEsperada();
        setVisible(ie == InteracaoEsperada.INTERACAO_WAIT_EVENT);
        
        //SPACESHIP INFO STUFF
        fuel.setText("Fuel: " + modeloObs.getFuelStorage() + " / " + modeloObs.getMaxFuelStorage());
        weapon.setText("Weapon: " + modeloObs.getWeaponSystem() + " / " + modeloObs.getMaxWeaponSystem());
        shield.setText("Shield: " + modeloObs.getShieldSystem() + " / " + modeloObs.getMaxShieldSystem());
        
        //CARGO INFO STUFF

        //RED CARGO STUFF
        redCargoValue.setText("\t" + modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Vermelho")) 
                                + " / " + modeloObs.getJogo().getSpaceShip().getMaxCargo());

        redCargoProgress.setProgress((Math.floor((modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Vermelho"))
                                        * 100) / modeloObs.getJogo().getSpaceShip().getMaxCargo()) / 100));
                
        //BLUE CARGO STUFF
        blueCargoValue.setText("\t" + modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Azul")) 
                                + " / " + modeloObs.getJogo().getSpaceShip().getMaxCargo());

        blueCargoProgress.setProgress((Math.floor((modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Azul"))
                                        * 100) / modeloObs.getJogo().getSpaceShip().getMaxCargo()) / 100));
                
        //BLACK CARGO STUFF
        blackCargoValue.setText("\t" + modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Preto")) 
                                + " / " + modeloObs.getJogo().getSpaceShip().getMaxCargo());        

        blackCargoProgress.setProgress((Math.floor((modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Preto"))
                                        * 100) / modeloObs.getJogo().getSpaceShip().getMaxCargo()) / 100));

        //GREEN CARGO STUFF
        greenCargoValue.setText("\t" + modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Verde")) 
                                + " / " + modeloObs.getJogo().getSpaceShip().getMaxCargo());

        greenCargoProgress.setProgress((Math.floor((modeloObs.getJogo().getSpaceShip().getSpecificCargoValue(modeloObs.getJogo().getSpaceShip().getSpecificCargo("Verde"))
                                        * 100) / modeloObs.getJogo().getSpaceShip().getMaxCargo()) / 100));
        
        //CREWMEMBERS STUFF
        switch(modeloObs.getJogo().getSpaceShip().getCrewMembers().size()){
            
            case 1:
                captainCrew.setVisible(true);
                navigationCrew.setVisible(false);
                landingCrew.setVisible(false);
                shieldCrew.setVisible(false);
                weaponCrew.setVisible(false);
                cargoCrew.setVisible(false);
                
                break;
                
            case 2:
                captainCrew.setVisible(true);
                navigationCrew.setVisible(true);
                landingCrew.setVisible(false);
                shieldCrew.setVisible(false);
                weaponCrew.setVisible(false);
                cargoCrew.setVisible(false);
                
                break;
                
            case 3:
                captainCrew.setVisible(true);
                navigationCrew.setVisible(true);
                landingCrew.setVisible(true);
                shieldCrew.setVisible(false);
                weaponCrew.setVisible(false);
                cargoCrew.setVisible(false);
                
                break;
                
            case 4:
                captainCrew.setVisible(true);
                navigationCrew.setVisible(true);
                landingCrew.setVisible(true);
                shieldCrew.setVisible(true);
                weaponCrew.setVisible(false);
                cargoCrew.setVisible(false);
                
                break;
                
            case 5:
                captainCrew.setVisible(true);
                navigationCrew.setVisible(true);
                landingCrew.setVisible(true);
                shieldCrew.setVisible(true);
                weaponCrew.setVisible(true);
                cargoCrew.setVisible(false);
                
                break;
                
            case 6:
                captainCrew.setVisible(true);
                navigationCrew.setVisible(true);
                landingCrew.setVisible(true);
                shieldCrew.setVisible(true);
                weaponCrew.setVisible(true);
                cargoCrew.setVisible(true);
                
                break;
        }        
        
        //DRONE INFO STUFF
        if(modeloObs.getJogo().getSpaceShip().getDrone() != null){
            droneImage.setImage(Imagens.getImagem(DRONE));
        }else
            droneImage.setImage(Imagens.getImagem(NO_DRONE));
        
        
        //ARTIFACTS STUFF
        for(int i = 0; i < modeloObs.getArtifactsNumber(); i++){
            artifactImage.get(i).setVisible(true);
        }

        dice.setImage(Imagens.getImagem(DIE_ANIMATION));
                
        rollDice.setDisable(false);
        rollDiceManually.setDisable(false);
        continua.setDisable(true);
    }
    
}
