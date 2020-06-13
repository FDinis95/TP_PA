package Logica.dados.eventos;

import Logica.Jogo;
import Logica.dados.Events;
import java.io.Serializable;

public class NoEvent extends Events implements Serializable{

    public NoEvent() {
        setID(5);
    }
    
    @Override
    public void applyEvent(Jogo game){
        //This does nothing
        game.getLog().addLog("Tiveste sorte, nao aconteceu nada!");
    }
    
    @Override
    public String toString() {
        return "NoEvent";
    }
    
}
