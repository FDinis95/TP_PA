package Logica.estados;

import Logica.Jogo;

public class StateAdapter implements Estado {
    
    private Jogo jogo;

    public StateAdapter(Jogo jogo) {
        this.jogo = jogo;
        
    }
    
    public Jogo getJogo(){
        return jogo;
        
    }
    
    @Override
    public Estado setup() {
        return this;
    }
    
    @Override
    public Estado selectShip(int valor) {
        return this;
        
    }
    
    @Override
    public Estado move(){
        return this;
        
    }
    
    @Override
    public Estado move(int move, int sector, int planet){
        return this;
    }

    @Override
    public Estado nextTurn() {
        return this;
        
    }

    @Override
    public Estado dock() {
        return this;
        
    }

    @Override
    public Estado unDock() {
        return this;
        
    }
    
    @Override
    public Estado useSpaceStation(int valor, String from, String to){
        return this;
        
    }

    @Override
    public Estado land() {
        return this;
        
    }
    
    @Override
    public Estado useRecursos(int valor, String from, String to){
        return this;
        
    }

    @Override
    public Estado hasResource() {
        return this;
        
    }

    @Override
    public Estado alienAttack() {
        return this;
        
    }

    @Override
    public Estado moveToResource(int moveX, int moveY) {
        return this;
        
    }

    @Override
    public Estado alienDestroysDrone() {
        return this;
        
    }

    @Override
    public Estado alienDestroyed() {
        return this;
        
    }
    
    @Override
    public Estado rollD6() {
        return this;
        
    }
    
    @Override
    public Estado rollD6(int id) {
        return this;
        
    }

    @Override
    public Estado newGame() {
        return this;
        
    }

}
