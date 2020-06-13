package Logica.dados;

import Logica.dados.variations.*;
import java.io.Serializable;


public class FabricaSpaceShip implements Serializable{
    
    public static SpaceShip criaSpaceShip(int i){
        
        if(i == 1){
            return new MilitaryShip();
            
        }else if(i == 2){
            return new MiningShip();
            
        }else
            return null;
        
    }

}
