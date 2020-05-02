package Logica.estados;

import Logica.Jogo;


public class WaitBegining extends StateAdapter {

    public WaitBegining(Jogo jogo) {
        super(jogo);
        
    }

    @Override
    public Estado setup() {
        
        //Cria recursos do jogo
        getJogo().initializeSetup();
        
        getJogo().getLog().addLog("Recursos Inicializados\n");
        getJogo().getLog().printLogs();
        getJogo().getLog().clearLog();
        
        return new WaitShipSelection(getJogo());
    }
    
    

    
}
