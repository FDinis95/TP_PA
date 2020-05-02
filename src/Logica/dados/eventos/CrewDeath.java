package Logica.dados.eventos;

import Logica.Jogo;
import Logica.dados.Events;

public class CrewDeath extends Events {

    public CrewDeath() {
        setID(1);
        
    }

    @Override
    public void applyEvent(Jogo game) {
        
        game.getLog().addLog("Infelizmente perdeste um tripulante...");
        game.getSpaceShip().getCrewMembers().remove(game.getSpaceShip().getCrewMembers().size() - 1);
        
    }

    @Override
    public String toString() {
        return "CrewDeath";
    }
    
}
