package Logica.estados;

import Logica.InteracaoEsperada;
import Logica.Jogo;
import java.io.Serializable;

public class WaitGameOver extends StateAdapter implements Serializable{
    
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
