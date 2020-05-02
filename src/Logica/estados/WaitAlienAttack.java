package Logica.estados;

import Logica.Jogo;


public class WaitAlienAttack extends StateAdapter{
    
    public WaitAlienAttack(Jogo jogo) {
        super(jogo);
    }
    
    @Override
    public Estado alienDestroyed(){
        
        return new WaitLanding(getJogo());
    }
    
    @Override
    public Estado alienDestroysDrone(){
        
        
        
        return new WaitPlanetSector(getJogo());
    }
    

}
