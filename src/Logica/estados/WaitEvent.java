package Logica.estados;

import Logica.InteracaoEsperada;
import Logica.Jogo;
import Logica.dados.FabricaEventos;
import java.io.Serializable;


public class WaitEvent extends StateAdapter implements Serializable{

    public WaitEvent(Jogo jogo) {
        super(jogo);
        
    }

    @Override
    public Estado rollD6(){
        
        int valor = (int)(Math.random() * 6) + 1;
        
        getJogo().getLog().addLog("\n----- INICIO EVENTO -----\n");
        
        getJogo().setEvento(FabricaEventos.criaEvento(valor));
        getJogo().getEvento().applyEvent(getJogo());
        getJogo().getEvento().setID(valor);
        
        getJogo().getLog().addLog("Evento gerado aleatoriamente: " + getJogo().getEvento());
        getJogo().getLog().addLog("\n" + getJogo().getSpaceShip());
        
        if((getJogo().getSpaceShip().getFuelStorage() == 0) || 
                (getJogo().getSpaceShip().getCrewMembers().isEmpty())){
            
            //Retorna para o WaitGameOver
            getJogo().setGameOver();
            getJogo().getLog().addLog("\nInfelizmente ficaste sem Capitao ou sem Fuel! Perdeste!");
                
            getJogo().getLog().clearLog();
            
            getJogo().getLog().addLog("\n\n----- FIM EVENTO (GAMEOVER) -----\n\n");
            
            return new WaitGameOver(getJogo());
            
        }else{
            //Retorna para o WaitMove
            getJogo().setWasPlanet(false);
            getJogo().getLog().addLog("\n----- FIM EVENTO -----\n");
            getJogo().getLog().clearLog();
            
            return new WaitEvent(getJogo()); 
        }
    }
    
    public Estado continua(){
        
        return new WaitMove(getJogo());
        
    }
    
    //Feito manualmente
    @Override
    public Estado rollD6(int id){
        
        getJogo().setEvento(FabricaEventos.criaEvento(id));
        getJogo().getEvento().applyEvent(getJogo());
        
        getJogo().getLog().addLog("Evento gerado pelo utilizador: " + getJogo().getEvento());
        getJogo().getLog().addLog("\n" + getJogo().getSpaceShip());
        
        if((getJogo().getSpaceShip().getFuelStorage() == 0) || (getJogo().getSpaceShip().getCrewMembers().isEmpty())){
            
            //Retorna para o WaitGameOver
            getJogo().setGameOver();
            getJogo().getLog().addLog("\nInfelizmente ficaste sem Capitao ou sem Fuel! Perdeste!");

                
            getJogo().getLog().addLog("\n\n----- FIM EVENTO (GAMEOVER) -----\n\n");
            getJogo().getLog().clearLog();
            
            
            return new WaitGameOver(getJogo());
            
        }else{
            //Retorna para o WaitMove
            getJogo().setWasPlanet(false);
            getJogo().getLog().addLog("\n----- FIM EVENTO -----\n");
            getJogo().getLog().clearLog();
            
            return new WaitMove(getJogo()); 
        }
        
    }

    @Override
    public InteracaoEsperada getInteracaoEsperada() {
        return InteracaoEsperada.INTERACAO_WAIT_EVENT;
        
    }
}
