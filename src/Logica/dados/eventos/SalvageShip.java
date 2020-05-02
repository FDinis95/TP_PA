package Logica.dados.eventos;

import Logica.Jogo;
import Logica.dados.Events;
import Logica.dados.Resource;
import java.util.Random;

public class SalvageShip extends Events {

    public SalvageShip() {
        setID(2);
        
    }

    @Override
    public void applyEvent(Jogo game) {
    
        int quantidade = (int)(Math.random() * 6) + 1;
        Object r = game.getSpaceShip().getCargo().keySet().toArray()[new Random().nextInt(game.getSpaceShip().getCargo().keySet().toArray().length)];
        game.getSpaceShip().addCargo((Resource)r, quantidade);
        
        game.getLog().addLog("\nTiveste sorte e encontraste um navio!\nResource encontrado: " 
                + r + "\nQuantidade Adicionada: " + quantidade);
    }
    
    @Override
    public String toString() {
        return "SalvageShip";
    }
    
}
