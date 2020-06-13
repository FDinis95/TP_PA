package Logica;

import Logica.dados.Planet;
import Logica.dados.Terreno;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class ObservableGame{

    private MaquinaEstados maquina;
    private PropertyChangeSupport propertyChangeSupport;

    public ObservableGame(MaquinaEstados machine) {
        this.maquina = machine;
        propertyChangeSupport = new PropertyChangeSupport(machine);
        
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
        
    }
    
    public MaquinaEstados getMaquina(){
        return maquina;
    }
    
    public void setMaquina(MaquinaEstados m){
        this.maquina = m;
        
        propertyChangeSupport.firePropertyChange(null, null, null);        
    }
    
    public void setup(){
        this.maquina.setup();
        
        propertyChangeSupport.firePropertyChange(null, null, null);
    }
    
    public void selectShip(int valor){
        this.maquina.selectShip(valor);
        
        
        propertyChangeSupport.firePropertyChange(null, null, null);
    }
    
    public void move(){
        this.maquina.move();
        
        
        propertyChangeSupport.firePropertyChange(null, null, null);
    }
    
    public void move(int m, int s, int p){
        this.maquina.move(m, s, p);
        
        
        propertyChangeSupport.firePropertyChange(null, null, null);
    }
    
    public void land(){
        this.maquina.land();
        
        propertyChangeSupport.firePropertyChange(null, null, null);
    }
    
    public void dock(){
        this.maquina.dock();
        
        propertyChangeSupport.firePropertyChange(null, null, null);
    }
    
    public void unDock(){
        this.maquina.unDock();
        
        propertyChangeSupport.firePropertyChange(null, null, null);
    }
    
    public void usaRecursos(int v, String from, String to){
        this.maquina.useRecursos(v, from, to);
        
        propertyChangeSupport.firePropertyChange(null, null, null);
    }
    
    public void usaSpaceStation(int v, String from, String to){
        this.maquina.useSpaceStation(v, from, to);
        
        propertyChangeSupport.firePropertyChange(null, null, null);        
    }
    
    public void nextTurn(){
        this.maquina.nextTurn();
        
        propertyChangeSupport.firePropertyChange(null, null, null);
    }
    
    public void rollD6(){
        this.maquina.rollD6();
        
        propertyChangeSupport.firePropertyChange(null, null, null);
    }
    
    public void rollD6(int id){
        this.maquina.rollD6(id);
        
        propertyChangeSupport.firePropertyChange(null, null, null);
    }
    
    public void continua(){
        this.maquina.continua();
        
        propertyChangeSupport.firePropertyChange(null, null, null);
    }

    public void moveToResource(int moveX, int moveY){
        this.maquina.moveToResource(moveX, moveY);
        
        propertyChangeSupport.firePropertyChange(null, null, null);
    }
    
    public void alienAttack(){
        this.maquina.alienAttack();
        
        propertyChangeSupport.firePropertyChange(null, null, null);
    }
    
    public void hasResource(){
        this.maquina.hasResource();
        
        propertyChangeSupport.firePropertyChange(null, null, null);        
    }
    
    public void newGame(){
        this.maquina.newGame();
        
        propertyChangeSupport.firePropertyChange(null, null, null);        
    }
    
    //Funções de Consulta (Será que é para meter todas???)
    public int getFuelStorage() {
        return this.maquina.getJogo().getSpaceShip().getFuelStorage();
    }
    
    public int getMaxFuelStorage(){
        return this.maquina.getJogo().getSpaceShip().getMaxFuel();
        
    }
    
    public int getWeaponSystem(){
        return this.maquina.getJogo().getSpaceShip().getWeaponSystem();
        
    }
    
    public int getMaxWeaponSystem(){
        return this.maquina.getJogo().getSpaceShip().getMaxWeapon();
        
    }
    
    public int getShieldSystem(){
        return this.maquina.getJogo().getSpaceShip().getShieldCapacity();
        
    }
    
    public int getMaxShieldSystem(){
        return this.maquina.getJogo().getSpaceShip().getMaxShield();
        
    }
    
    public int getArtifactsNumber(){
        return this.maquina.getJogo().getSpaceShip().getArtifact();
        
    }
    
    public int getVisitas(){
        return this.maquina.getJogo().getSpaceShip().getNumberVisits();
        
    }
    
    public Jogo getJogo(){
        return this.maquina.getJogo();
        
    }
    
    public Planet getPlaneta(){
        return this.maquina.getJogo().getPlaneta();
        
    }
    
    public Terreno getTerreno(){
        return this.maquina.getJogo().getPlaneta().getTerreno();
        
    }
    
    public InteracaoEsperada getInteracaoEsperada(){
        return maquina.getInteracaoEsperada();
        
    }
    
    
}
