package Logica.dados.eventos;

import Logica.Jogo;
import Logica.dados.Events;

public class FuelLoss extends Events {

    public FuelLoss() {
        setID(4);
    }

    @Override
    public void applyEvent(Jogo game) {
    
        game.getSpaceShip().subFuel(1);
        game.getLog().addLog("Houve um vazamento de combustivel. -1 fuel");
    }
    
    @Override
    public String toString() {
        return "FuelLoss";
    }
    
}
