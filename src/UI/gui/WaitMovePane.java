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


public class WaitMovePane extends BorderPane{

    private ObservableGame modeloObs;
    private Label spaceShipType = new Label();
    private int moveType = 0, sectorType = 0, planetType = 0;
    private Button movimento = new Button("Movimento");
    private Button movimentoManual = new Button("Movimento Manual");
    private Button continuar = new Button("Continuar");
    private TextField moveText = new TextField();
    private TextField sectorText = new TextField();
    private TextField planetText = new TextField();
    
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
    
    public WaitMovePane(ObservableGame ob){
        this.modeloObs = ob;
        this.modeloObs.addPropertyChangeListener((PropertyChangeEvent evt) -> {
            atualizaVista();
            
        });
        
        setTop(null);
        organizaComponentes();
        atualizaVista();
        
    }
    
    private void organizaComponentes(){

        setCenter(null);
        organizaTopBar();
        
        HBox buttonPane = new HBox();
        
        movimento.setOnAction((event) -> {
            modeloObs.move();

        });
        
        movimentoManual.setOnAction((event) -> {
            organizaRespostaUtilizador();
            
        });
        
        continuar.setOnAction((event) -> {
            
            moveType = Integer.parseInt(moveText.getText());
            sectorType = Integer.parseInt(sectorText.getText());
            planetType = Integer.parseInt(planetText.getText());
            
            movimento.setDisable(false);
            movimentoManual.setDisable(false);
            continuar.setVisible(false);
            modeloObs.move(moveType, sectorType, planetType);
        });
        
        continuar.setVisible(false);
        
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setSpacing(20);
        buttonPane.setPrefSize(100, 200);

        buttonPane.getChildren().addAll(movimento, movimentoManual, continuar);
        
        setBottom(buttonPane);
      
    }
    
    private void organizaRespostaUtilizador(){
        setCenter(null);
        
        HBox choicesBox = new HBox();
        VBox moveBox = new VBox();
        VBox sectorBox = new VBox();
        VBox planetBox = new VBox();
               
        TextArea moveExplanation = new TextArea("Definir o Tipo de Movimento\n1 - Normal\n2 - Buraco Negro");
        TextArea sectorExplanation = new TextArea("Definir o Tipo de Sector\n1 - Branco (Sem SpaceShip)\n2 - Vermelho (Com SpaceShip)");
        TextArea planetExplanation = new TextArea("Definir o Tipo de Planeta\n1 - Planeta Verde\n2 - Planeta Preto\n3 - Planeta Vermelho\n4 - Planeta Azul");
        
        moveExplanation.setPrefSize(300, 300);
        sectorExplanation.setPrefSize(300, 300);
        planetExplanation.setPrefSize(300, 300);
                       
        moveText.setPadding(new Insets(10));
        sectorText.setPadding(new Insets(10));
        planetText.setPadding(new Insets(10));

        movimento.setDisable(true);
        movimentoManual.setDisable(true);
        
        planetText.setOnAction((event) -> {
            organizaComponentes();
            continuar.setVisible(true);
            
        });
        
        moveBox.getChildren().addAll(moveExplanation, moveText);
        sectorBox.getChildren().addAll(sectorExplanation, sectorText);
        planetBox.getChildren().addAll(planetExplanation, planetText);
        
        choicesBox.setAlignment(Pos.CENTER);
        choicesBox.getChildren().addAll(moveBox, sectorBox, planetBox);
        setCenter(choicesBox);
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
               
        topBar.setSpacing(70);
        topBar.getChildren().addAll(spaceShip, crewBox, cargoBox);
        
        setTop(topBar);
        
    }
            
    private void atualizaVista(){
        InteracaoEsperada ie = modeloObs.getInteracaoEsperada();
        setVisible(ie == InteracaoEsperada.INTERACAO_WAIT_MOVE);
                
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
        
        if(modeloObs.getJogo().getWasPlanet()){
            movimentoManual.setDisable(true);
        
        }else
            movimentoManual.setDisable(false);
    }
    
}
