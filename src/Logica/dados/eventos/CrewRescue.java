package Logica.dados.eventos;

import Logica.Jogo;
import Logica.dados.Events;
import java.io.Serializable;

public class CrewRescue extends Events implements Serializable{

    public CrewRescue() {
        setID(6);
    }
    
    @Override
    public void applyEvent(Jogo game) {
        
        switch (game.getSpaceShip().getCrewMembers().size()) {
            case 1:
                game.getSpaceShip().addCrewMember("Navigation");
                game.getLog().addLog("Encontraste um Navigation Officer!");
                break;
                
            case 2:
                game.getSpaceShip().addCrewMember("Landing");
                game.getLog().addLog("Encontraste um Landing Officer!");
                break;
                
            case 3:
                game.getSpaceShip().addCrewMember("Shields");
                game.getLog().addLog("Encontraste um Shield Officer!");
                break;
            
            case 4:
                game.getSpaceShip().addCrewMember("Weapons");
                game.getLog().addLog("Encontraste um Weapons Officer!");
                
                break;
                
            case 5:                
                game.getSpaceShip().addCrewMember("Cargo");
                game.getLog().addLog("Encontraste um Cargo Officer!");
                break;
                
            default:
                game.getLog().addLog("Felizmente tens todos os tripulantes!");
                break;
        }
    }
    
    @Override
    public String toString() {
        return "CrewRescue";
    }
}
