package Logica;

import Logica.estados.*;

public class MaquinaEstados {

    private Jogo jogo;
    private Estado estado;

    public MaquinaEstados() {
        
        this.jogo = new Jogo();
        this.estado = new WaitBegining(jogo);
        
    }
    
    public Jogo getJogo(){
        return jogo;
    }
    
    public Estado getEstado(){
        return estado;
        
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
        
    }
    
    public void alienAttack(){
        setEstado(getEstado().alienAttack());
        
    }

    public void moveToResource(int moveX, int moveY){
        setEstado(getEstado().moveToResource(moveX, moveY));
        
    }
    
    public void hasResource(){
        setEstado(getEstado().hasResource());
        
    }
    
    public void land(){
        setEstado(getEstado().land());
        
    }
    
    public void useRecursos(int valor, String from, String to){
        setEstado(getEstado().useRecursos(valor, from, to));
        
    }
    
    public void dock(){
        setEstado(getEstado().dock());
        
    }
    
    public void unDock(){
        setEstado(getEstado().unDock());
        
    }
    
    public void useSpaceStation(int valor, String from, String to){
        setEstado(getEstado().useSpaceStation(valor, from, to));
        
    }
    
    public void nextTurn(){
        setEstado(getEstado().nextTurn());
        
    }
    
    public void move(){
        setEstado(getEstado().move());
        
    }
    
    public void move(int move, int sector, int planet){
        setEstado(getEstado().move(move, sector, planet));
        
    }
    
    public void rollD6(){
        setEstado(getEstado().rollD6());
        
    }
    
    public void rollD6(int id){
        setEstado(getEstado().rollD6(id));
        
    }
    
    public void setup(){
        setEstado(getEstado().setup());
        
    }
    
    public void newGame(){
        setEstado(getEstado().newGame());
        
    }
    
    public void start(int valor){
        setEstado(getEstado().selectShip(valor));
        
    }
    
}
