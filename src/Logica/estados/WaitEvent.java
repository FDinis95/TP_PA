package Logica.estados;

import Logica.Jogo;
import Logica.dados.FabricaEventos;


public class WaitEvent extends StateAdapter {

    public WaitEvent(Jogo jogo) {
        super(jogo);
    }

    @Override
    public Estado rollD6(){
        
        int valor = (int)(Math.random() * 6) + 1;
        
        getJogo().setEvento(FabricaEventos.criaEvento(valor));
        getJogo().getEvento().applyEvent(getJogo());
        
        getJogo().getLog().addLog("Evento: " + getJogo().getEvento());
        getJogo().getLog().addLog("\n" + getJogo().getSpaceShip());
        
        if((getJogo().getSpaceShip().getFuelStorage() == 0) || 
                (getJogo().getSpaceShip().getCrewMembers().isEmpty())){
            //Retorna para o WaitGameOver
            getJogo().setGameOver();
            getJogo().getLog().addLog("\nPerdeste!");
                
            getJogo().getLog().printLogs();
            getJogo().getLog().clearLog();
            
            return new WaitGameOver(getJogo());
            
        }else{
            //Retorna para o WaitMove
            getJogo().setWasPlanet(false);
            getJogo().getLog().printLogs();
            getJogo().getLog().clearLog();
            
            return new WaitMove(getJogo()); 
        }
    }
}
