package UI.gui;

import Logica.InteracaoEsperada;
import Logica.ObservableGame;
import Logica.dados.variations.MilitaryShip;
import static UI.gui.Constants.CAPTAIN_CREW;
import static UI.gui.Constants.CARGO_CREW;
import static UI.gui.Constants.DRONE;
import static UI.gui.Constants.LANDING_CREW;
import static UI.gui.Constants.NAVIGATION_CREW;
import static UI.gui.Constants.NO_DRONE;
import static UI.gui.Constants.REFERENCE_CARD;
import static UI.gui.Constants.SHIELD_CREW;
import static UI.gui.Constants.WEAPON_CREW;
import java.beans.PropertyChangeEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class WaitSpaceStationPane extends BorderPane {

    private ObservableGame modeloObs;
    private String from = " ", to = " ";
    
    public WaitSpaceStationPane(ObservableGame ob){
        this.modeloObs = ob;
        this.modeloObs.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            atualizaVista();

        });

        organizaComponentes();
        atualizaVista();
        
    }
    
    private void organizaComponentes(){
        
        setTop(null);
        setCenter(null);
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
    
    private void organizaTopBar() {

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

        switch (modeloObs.getJogo().getSpaceShip().getCrewMembers().size()) {

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

        if (modeloObs.getJogo().getSpaceShip().getDrone() != null) {
            droneImage = new ImageView(Imagens.getImagem(DRONE));
        } else {
            droneImage = new ImageView(Imagens.getImagem(NO_DRONE));
        }

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

        modeloObs.getJogo().getSpaceShip().addArtifact();

        artifactBox.getChildren().add(artifacts);

        for (int i = 0; i < modeloObs.getArtifactsNumber(); i++) {
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
    
    private void organizaUsaRecursos(){
        
        setCenter(null);
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
        
        referenceBox.getChildren().add(referenceCard);
        
        if(modeloObs.getJogo().getSpaceShip() instanceof MilitaryShip)
            choicesButton.getChildren().addAll(upgradeCargo, upgradeWeapon, tradeResource, reviveCrewMember, healDrone, reviveDrone, exit);
        else
            choicesButton.getChildren().addAll(upgradeCargo, tradeResource, reviveCrewMember, healDrone, reviveDrone, exit);
        
        rightBar.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, 
                null, new BorderWidths(2))));
        rightBar.setAlignment(Pos.CENTER);
        rightBar.getChildren().addAll(referenceBox, choicesButton);
        
        setCenter(rightBar);
        
    }
    
    private void organizaFromTrade() {

        setCenter(null);
        
        VBox tradeBox = new VBox();
        HBox buttonBox = new HBox();
        
        Label fromLabel = new Label("Recurso a escolher");
        fromLabel.setTextFill(Color.BLACK);
        
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

        buttonBox.getChildren().addAll(redFrom, blueFrom, blackFrom, greenFrom);
        tradeBox.setAlignment(Pos.CENTER);
        tradeBox.getChildren().addAll(fromLabel, buttonBox);

        buttonBox.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
                null, new BorderWidths(2))));

        tradeBox.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
                null, new BorderWidths(2))));

        setCenter(tradeBox);

    }
    
    private void organizaToTrade() {

        setCenter(null);
        
        VBox tradeBox = new VBox();
        HBox buttonBox = new HBox();
        
        Label toLabel = new Label("Recurso escolhido para trocar");
        toLabel.setTextFill(Color.BLACK);
        
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
            organizaComponentes();
            
        });

        blueTo.setOnAction((event) -> {
            to = "Azul";
            modeloObs.usaSpaceStation(3, from, to);
            organizaComponentes(); 
            
        });

        blackTo.setOnAction((event) -> {
            to = "Preto";
            modeloObs.usaSpaceStation(3, from, to);
            organizaComponentes(); 
            
        });

        greenTo.setOnAction((event) -> {
            to = "Verde";
            modeloObs.usaSpaceStation(3, from, to);
            organizaComponentes();  
            
        });

        buttonBox.getChildren().addAll(redTo, blueTo, blackTo, greenTo);
        tradeBox.setAlignment(Pos.CENTER);
        tradeBox.getChildren().addAll(toLabel, buttonBox);

        buttonBox.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
                null, new BorderWidths(2))));

        tradeBox.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
                null, new BorderWidths(2))));

        setCenter(tradeBox);

    }
    
    private void atualizaVista() {
        InteracaoEsperada ie = modeloObs.getInteracaoEsperada();
        setVisible(ie == InteracaoEsperada.INTERACAO_WAIT_SPACE_STATION);

    }
    
}
