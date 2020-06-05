package Logica;

import Logica.dados.Planet;
import Logica.dados.SpaceShip;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class ObservableGame {

    private MaquinaEstados maquina;
    private PropertyChangeSupport propertyChangeSupport;

    public ObservableGame(MaquinaEstados machine) {
        this.maquina = machine;
        propertyChangeSupport = new PropertyChangeSupport(machine);
        
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
        
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
    
    
    //TALVEZ??
    public void setSpaceShip(SpaceShip sp){
        this.maquina.getJogo().setSpaceShip(sp);
        
        propertyChangeSupport.firePropertyChange(null, null, null);
    }
    
    public void setPlaneta(Planet p){
        this.maquina.getJogo().setPlaneta(p);
        
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
    
    public Jogo getJogo(){
        return this.maquina.getJogo();
        
    }
    
    public InteracaoEsperada getInteracaoEsperada(){
        return maquina.getInteracaoEsperada();
        
    }
    
    
}
