package Logica.estados;

import Logica.Jogo;

public class WaitGameOver extends StateAdapter {
    
    public WaitGameOver(Jogo jogo) {
        super(jogo);
    }

    @Override
    public Estado newGame() {
        return new WaitBegining(getJogo());
        
    }
    
    
    
    
}
