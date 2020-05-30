package Logica.estados;

import Logica.InteracaoEsperada;
import Logica.Jogo;

public class WaitGameOver extends StateAdapter {
    
    public WaitGameOver(Jogo jogo) {
        super(jogo);
    }

    @Override
    public Estado newGame() {
        return new WaitBegining(getJogo());
        
    }
    
    @Override
    public InteracaoEsperada getInteracaoEsperada() {
        return InteracaoEsperada.INTERACAO_WAIT_GAMEOVER;
        
    }
    
    
}
