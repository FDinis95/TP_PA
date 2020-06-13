package UI.gui;

import Logica.FileUtility;
import Logica.Jogo;
import Logica.MaquinaEstados;
import Logica.ObservableGame;
import static UI.gui.Constants.BACKGROUND;
import java.io.File;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;


public class Root extends VBox{

    private ObservableGame modeloObs;
    
    public Root(ObservableGame ob) {
        this.modeloObs = ob;
    
        MenuBar menu = new MenuBar();
        getChildren().add(menu);
        
        Menu menuFile = new Menu("_Game");
        MenuItem gravar = new MenuItem("Save");
        MenuItem load = new MenuItem("Load");
        MenuItem sair = new MenuItem("Exit");
        
        menuFile.getItems().addAll(gravar, load, sair);
        
        gravar.setOnAction((event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./"));
            File selectedFile = fileChooser.showSaveDialog(null);
            
            if(selectedFile != null){
                try{
                    FileUtility.saveGameToFile(selectedFile, modeloObs.getMaquina());
                    
                }catch(IOException ex){
                    System.out.println(ex);
                    Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
                    dialogoResultado.setHeaderText("Save");
                    dialogoResultado.setContentText("Operation failed: " + ex);
                    dialogoResultado.showAndWait();
                    
                }
                
            }else
                System.out.println("Operacao Cancelada!"); //DEBUG
            
            
        });
        
        load.setOnAction((event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new File("./"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {           
                try{
                    MaquinaEstados jogo = 
                            (MaquinaEstados)FileUtility.retrieveGameFromFile(selectedFile);
                    if(jogo != null){
                        
                     modeloObs.setMaquina(jogo);
                     
                    }
                }catch(IOException | ClassNotFoundException ex){
                    System.out.println(ex);
                    Alert dialogoResultado = new Alert(Alert.AlertType.INFORMATION);
                    dialogoResultado.setHeaderText("Load");
                    dialogoResultado.setContentText("Operation failed: " + ex);
                    dialogoResultado.showAndWait();
                }
          
            } else {
                System.out.println("Operation canceled ");
            }
        
        });
        
        sair.setOnAction((event) -> {
            System.exit(0);
        
        });
        
        menu.getMenus().add(menuFile);
        organizaComponentes();
    }
    
    private void organizaComponentes(){
        
        this.setBackground((new Background(new BackgroundImage(Imagens.getImagem(BACKGROUND),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT))));
        
        WaitBeginningPane begin = new WaitBeginningPane(modeloObs);
                
        WaitShipSelectionPane selectShip = new WaitShipSelectionPane(modeloObs); 
        
        WaitMovePane move = new WaitMovePane(modeloObs);
        
        WaitPlanetSectorPane planetSector = new WaitPlanetSectorPane(modeloObs);
        
        WaitSpaceStationPane spaceStation = new WaitSpaceStationPane(modeloObs);
        
        WaitEventPane event = new WaitEventPane(modeloObs);
        
        WaitLandingPane land = new WaitLandingPane(modeloObs);
        
        WaitGameOverPane over = new WaitGameOverPane(modeloObs);
        
        StackPane center = new StackPane(begin, selectShip, move, planetSector, spaceStation, event, land, over);
        
        getChildren().add(center);
                
    }

}
