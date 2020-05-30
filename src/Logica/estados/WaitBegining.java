package Logica.estados;

import Logica.InteracaoEsperada;
import Logica.Jogo;


public class WaitBegining extends StateAdapter {

    public WaitBegining(Jogo jogo) {
        super(jogo);
        
    }

    @Override
    public Estado setup() {
        
        //Cria recursos do jogo
        getJogo().initializeSetup();
        
        getJogo().getLog().addLog("----- Inicio do Log -----\n\nRecursos Inicializados\n");
        getJogo().getLog().clearLog();
        
        return new WaitShipSelection(getJogo());
    }

    @Override
    public InteracaoEsperada getInteracaoEsperada() {
        return InteracaoEsperada.INTERACAO_WAIT_BEGINNING;
    }
    
    

    
}
