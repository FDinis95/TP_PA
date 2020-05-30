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
    
    public InteracaoEsperada getInteracaoEsperada(){
        return estado.getInteracaoEsperada();
        
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
        
    }
    
    public void alienAttack(){
        setEstado(estado.alienAttack());
        
    }

    public void moveToResource(int moveX, int moveY){
        setEstado(estado.moveToResource(moveX, moveY));
        
    }
    
    public void hasResource(){
        setEstado(estado.hasResource());
        
    }
    
    public void land(){
        setEstado(estado.land());
        
    }
    
    public void useRecursos(int valor, String from, String to){
        setEstado(estado.useRecursos(valor, from, to));
        
    }
    
    public void dock(){
        setEstado(estado.dock());
        
    }
    
    public void unDock(){
        setEstado(estado.unDock());
        
    }
    
    public void useSpaceStation(int valor, String from, String to){
        setEstado(estado.useSpaceStation(valor, from, to));
        
    }
    
    public void nextTurn(){
        setEstado(estado.nextTurn());
        
    }
    
    public void move(){
        setEstado(estado.move());
        
    }
    
    public void move(int move, int sector, int planet){
        setEstado(estado.move(move, sector, planet));
        
    }
    
    public void rollD6(){
        setEstado(estado.rollD6());
        
    }
    
    public void rollD6(int id){
        setEstado(estado.rollD6(id));
        
    }
    
    public void setup(){
        setEstado(estado.setup());
        
    }
    
    public void newGame(){
        setEstado(estado.newGame());
        
    }
    
    public void start(int valor){
        setEstado(estado.selectShip(valor));
        
    }
    
}
