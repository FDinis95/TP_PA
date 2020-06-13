package Logica.dados.eventos;

import Logica.Jogo;
import Logica.dados.Events;
import java.io.Serializable;

public class FuelLoss extends Events implements Serializable{

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
