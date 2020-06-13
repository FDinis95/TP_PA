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
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class WaitSpaceStationPane extends BorderPane {

    private ObservableGame modeloObs;
    private String from = " ", to = " ";
    private Label spaceShipType = new Label();
    private int count = 0;
    
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
    Label redCargoValue = new Label();
    Label blueCargoValue = new Label();
    Label blackCargoValue = new Label();
    Label greenCargoValue = new Label();
    
    ProgressBar redCargoProgress = new ProgressBar(0);
    ProgressBar blueCargoProgress = new ProgressBar(0);
    ProgressBar blackCargoProgress = new ProgressBar(0);
    ProgressBar greenCargoProgress = new ProgressBar(0);
    
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
    
    
    
    public WaitSpaceStationPane(ObservableGame ob){
        this.modeloObs = ob;
        this.modeloObs.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            atualizaVista();

        });

        setTop(null);
        setCenter(null);
        organizaComponentes();
        atualizaVista();
        
    }
    
    private void organizaComponentes(){

        organizaTopBar();

        HBox buttonPane = new HBox();

        Button usarRecursos = new Button("Usar Recursos");
        Button sair = new Button("Sair da Space Station");
      
        usarRecursos.setOnAction((event) -> {
                       
            organizaUsaRecursos();
        
        });

        sair.setOnAction((event) -> {
            modeloObs.unDock();

        });

        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(20);
        buttonPane.setPrefSize(100, 200);
        
        buttonPane.getChildren().addAll(usarRecursos, sair);

        setBottom(buttonPane);
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
        
        //PLANETA INFO STUFF
        VBox planetBox = new VBox(15);
        Label planetLabel = new Label("Planeta");
        
        planetType.setText("Tipo: ");
        planetResources.setText("Resources: ");
        
        planetLabel.setTextFill(Color.WHITE);
        planetLabel.setFont(Font.font(25));
        planetLabel.setTranslateX(50);
        
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
        
//        planetBox.setAlignment(Pos.CENTER);
        planetBox.setPadding(new Insets(10, 0, 5, 10));
        planetBox.getChildren().addAll(planetLabel, planetTypeBox, recursosPlanetaBox);
        
        topBar.setSpacing(70);
        topBar.getChildren().addAll(spaceShip, crewBox, cargoBox, planetBox);
        
        setTop(topBar);
        
    }
    
    private void organizaUsaRecursos(){
        
        VBox rightBar = new VBox();
        HBox referenceBox = new HBox();
        HBox choicesButton = new HBox();
        Button upgradeCargo = new Button("Upgrade Cargo");
        Button upgradeWeapon = new Button("Upgrade Weapon");
        Button tradeResource = new Button("Trade");
        Button reviveCrewMember = new Button("Revive CrewMember");
        Button healDrone = new Button("Heal Drone");
        ImageView temp = new ImageView(Imagens.getImagem(DRONE));
        Button reviveDrone = new Button("Revive Drone");
        Button exit = new Button("Exit");
        
        temp.setFitHeight(25);
        temp.setFitWidth(25);
        healDrone.setGraphic(temp);
        healDrone.setTooltip(new Tooltip("Trocar recursos por 1 de vida do Drone!"));
        
        ImageView referenceCard = new ImageView(Imagens.getImagem(REFERENCE_CARD));
        referenceCard.setFitWidth(300);
        referenceCard.setFitHeight(300);
        
        choicesButton.setSpacing(15);
        choicesButton.setPrefSize(100, 200);
        choicesButton.setAlignment(Pos.CENTER);
        
        upgradeCargo.setOnAction((event) -> {
            modeloObs.usaSpaceStation(1, from, to);
            
        });
        
        upgradeWeapon.setOnAction((event) -> {
            modeloObs.usaSpaceStation(2, from, to);
            
        });
        
        tradeResource.setOnAction((event) -> {
            organizaFromTrade();
                        
        });
        
        reviveCrewMember.setOnAction((event) -> {
            modeloObs.usaSpaceStation(4, from, to);
           
        });
        
        healDrone.setOnAction((event) -> {
            modeloObs.usaSpaceStation(5, from, to);
            
        });
        
        reviveDrone.setOnAction((event) -> {
            modeloObs.usaSpaceStation(6, from, to);
            
        });
        
        exit.setOnAction((event) -> {
            organizaComponentes();
        });
        referenceBox.setAlignment(Pos.CENTER);
        referenceBox.getChildren().add(referenceCard);
        
        if(modeloObs.getJogo().getSpaceShip() instanceof MilitaryShip)
            choicesButton.getChildren().addAll(upgradeCargo, upgradeWeapon, tradeResource, reviveCrewMember, healDrone, reviveDrone, exit);
        else
            choicesButton.getChildren().addAll(upgradeCargo, tradeResource, reviveCrewMember, healDrone, reviveDrone, exit);
        
        
        rightBar.setAlignment(Pos.CENTER);
        rightBar.getChildren().addAll(referenceBox, choicesButton);
        
        setCenter(rightBar);
        
    }
    
    private void organizaFromTrade() {

        setCenter(null);
        
        VBox tradeBox = new VBox();
        HBox buttonBox = new HBox();
        
        Label fromLabel = new Label("Recurso a escolher");
        fromLabel.setFont(Font.font(25));
        fromLabel.setTextFill(Color.WHITE);
        
        Button redFrom = new Button("Red Resource");
        Button blueFrom = new Button("Blue Resource");
        Button blackFrom = new Button("Black Resource");
        Button greenFrom = new Button("Green Resource");
                
        redFrom.setStyle("-fx-background-color: red");
        blueFrom.setStyle("-fx-background-color: blue");
        blackFrom.setStyle("-fx-background-color: black");
        greenFrom.setStyle("-fx-background-color: green");

        setCenter(null);

        redFrom.setOnAction((event) -> {
            from = "Vermelho";
            organizaToTrade();

        });

        blueFrom.setOnAction((event) -> {
            from = "Azul";
            organizaToTrade();

        });

        blackFrom.setOnAction((event) -> {
            from = "Preto";
            organizaToTrade();

        });

        greenFrom.setOnAction((event) -> {
            from = "Verde";
            organizaToTrade();

        });

        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(redFrom, blueFrom, blackFrom, greenFrom);
        tradeBox.setAlignment(Pos.CENTER);
        tradeBox.getChildren().addAll(fromLabel, buttonBox);

        setCenter(tradeBox);

    }
    
    private void organizaToTrade() {

        setCenter(null);
        
        VBox tradeBox = new VBox();
        HBox buttonBox = new HBox();
        
        Label toLabel = new Label("Recurso escolhido para trocar");
        toLabel.setFont(Font.font(25));
        toLabel.setTextFill(Color.WHITE);
        
        Button redTo = new Button("Red Resource");
        Button blueTo = new Button("Blue Resource");
        Button blackTo = new Button("Black Resource");
        Button greenTo = new Button("Green Resource");
                
        redTo.setStyle("-fx-background-color: red");
        blueTo.setStyle("-fx-background-color: blue");
        blackTo.setStyle("-fx-background-color: black");
        greenTo.setStyle("-fx-background-color: green");

        switch(from){
            case "Vermelho":
                redTo.setDisable(true);
                break;
                
            case "Azul":
                blueTo.setDisable(true);
                break;
                
            case "Preto":
                blackTo.setDisable(true);
                break;
                
            case "Verde":
                greenTo.setDisable(true);
                break;
        }

        redTo.setOnAction((event) -> {
            to = "Vermelho";
            modeloObs.usaSpaceStation(3, from, to);
            organizaUsaRecursos();
        });

        blueTo.setOnAction((event) -> {
            to = "Azul";
            modeloObs.usaSpaceStation(3, from, to);
            organizaUsaRecursos();
        });

        blackTo.setOnAction((event) -> {
            to = "Preto";
            modeloObs.usaSpaceStation(3, from, to);
            organizaUsaRecursos();
        });

        greenTo.setOnAction((event) -> {
            to = "Verde";
            modeloObs.usaSpaceStation(3, from, to);
            organizaUsaRecursos();
        });

        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(redTo, blueTo, blackTo, greenTo);
        tradeBox.setAlignment(Pos.CENTER);
        tradeBox.getChildren().addAll(toLabel, buttonBox);

        setCenter(tradeBox);

    }
    
    private void atualizaVista() {
        InteracaoEsperada ie = modeloObs.getInteracaoEsperada();
        setVisible(ie == InteracaoEsperada.INTERACAO_WAIT_SPACE_STATION);
        
        if (count < 6) {
            switch (modeloObs.getPlaneta().getTipo()) {

                case "Verde":

                    planetImage.setImage(Imagens.getImagem(GREEN_RESOURCE));
                    recursosPlanetaBox.getChildren().setAll(planetResources, vermelhoR, verdeR);
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
        
        //PLANET RESOURCES INFO STUFF
        if (count >= 6) {
            if (modeloObs.getJogo().getSpaceShip().getDrone().getRes() != null) {

                switch (modeloObs.getJogo().getSpaceShip().getDrone().getRes().getTipo()) {

                    case "Verde":

                        recursosPlanetaBox.getChildren().remove(verdeR);
                        break;

                    case "Preto":

                        recursosPlanetaBox.getChildren().remove(pretoR);
                        break;

                    case "Vermelho":

                        recursosPlanetaBox.getChildren().remove(vermelhoR);
                        break;

                    case "Azul":

                        recursosPlanetaBox.getChildren().remove(azulR);
                        break;

                    default:
                        break;

                }

            }
        }
        
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

    }
    
}
