package Logica.dados.eventos;

import Logica.Jogo;
import Logica.dados.Events;
import Logica.dados.Resource;
import java.io.Serializable;
import java.util.Random;

public class CargoLoss extends Events implements Serializable{

    public CargoLoss() {
        setID(3);
    }

    @Override
    public void applyEvent(Jogo game) {
    
        int quantidade = (int)(Math.random() * 3) + 1;
        Object r = game.getSpaceShip().getCargo().keySet().toArray()[new Random().nextInt(game.getSpaceShip().getCargo().keySet().toArray().length)];
        game.getSpaceShip().subCargo((Resource)r, quantidade);

        game.getLog().addLog("Tiveste azar, perdeste recursos...\nResource perdido: " 
                + r + "\nQuantidade retirada: " + quantidade);

    }

    @Override
    public String toString() {
        return "CargoLoss";
    }
    
}
