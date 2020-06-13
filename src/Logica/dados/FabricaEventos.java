package Logica.dados;

import Logica.dados.eventos.*;
import java.io.Serializable;

public class FabricaEventos implements Serializable{

    public static Events criaEvento(int ID){
        
        switch (ID) {
            case 1:
                return new CrewDeath();
                
            case 2:
                return new SalvageShip();
                
            case 3:
                return new CargoLoss();
                
            case 4:
                return new FuelLoss();
                
            case 5:
                return new NoEvent();
                
            case 6:
                return new CrewRescue();
                
            default:
                return null;
        }
    }
}
