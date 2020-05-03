package Logica;

import Logica.dados.*;
import java.util.ArrayList;

public class Jogo {

    private boolean over; //Verifica o fim do jogo
    private boolean wasPlanet; //Verifica se foi um planeta ou evento
    private boolean fightOver; //Verifica se a luta contra um alien acabou
    private boolean upgradeLimit; //Limitar o upgrade do Cargo
    private int initialDroneX;
    private int initialDroneY;
    
    private Dado dice;
    private Log log;
    private Reference referencia;
    private Events evento;
    
    //SpaceStation engloba:
    // - SpaceShip na Estação
    // - Referencia para saber as trocas
    private SpaceStation spaceStation;
    
    //Planet engloba:
    // - Terreno
    // - Recursos do Planeta
    // - Tipo de Planeta
    // - SpaceShip no Planeta
    private Planet planeta;
    
    //SpaceShip engloba:
    // - CargoHold
    // - FuelSystem
    // - CrewMembers
    // - Drone
    // - Artifacts
    private SpaceShip spaceShip;

    public Jogo() {}
    
    public void initializeSetup(){
        
        dice = new Dado();
        log = new Log();
        over = false;
        fightOver = false;
        wasPlanet = false; //Inicialmente é sempre um planete
        upgradeLimit = false;
        referencia = new Reference();
        spaceStation = null;
        
        
        initialDroneX = 0;
        initialDroneY = 0;
        
    }

    public boolean getUpgradeLimit() {
        return upgradeLimit;
    }

    public void setUpgradeLimit(boolean upgradeLimit) {
        this.upgradeLimit = upgradeLimit;
    }
    
    public boolean getFightOver() {
        return fightOver;
    }

    public void setFightOver(boolean fightOver) {
        this.fightOver = fightOver;
    }

    public int getInitialDroneX() {
        return initialDroneX;
    }

    public void setInitialDroneX(int initialDroneX) {
        this.initialDroneX = initialDroneX;
    }

    public int getInitialDroneY() {
        return initialDroneY;
    }

    public void setInitialDroneY(int initialDroneY) {
        this.initialDroneY = initialDroneY;
    }
    
    public Log getLog() {
        return log;
    }
    
    public ArrayList<String> getFullLog(){
        return log.getFullLog();
        
    }
    
    public boolean getWasPlanet() {
        return wasPlanet;
    }

    public void setWasPlanet(boolean wasPlanet) {
        this.wasPlanet = wasPlanet;
    }

    public Events getEvento() {
        return evento;
    }

    public void setEvento(Events evento) {
        this.evento = evento;
    }
   
    public SpaceShip getSpaceShip() {
        return spaceShip;
        
    }

    public Planet getPlaneta() {
        return planeta;
        
    }

    public void setPlaneta(Planet planeta) {
        this.planeta = planeta;
        
    }

    public Dado getDice() {
        return dice;
    }
    
    public int getDiceRoll(){
        return dice.getValue();
        
    }
    
    public void setSpaceShip(SpaceShip ss){
        this.spaceShip = ss;
        
    }

    public void setSpaceStation(SpaceStation spaceStation) {
        this.spaceStation = spaceStation;
    }
   
    public void createReferenceCard(){
        System.out.println("Criei uma carta de Referencia!"); //DEBUG
        
    }
    
    public boolean getGameOver(){
        return over;
        
    }
    
    public void setGameOver(){
        over = true;
        
    }
    
}
