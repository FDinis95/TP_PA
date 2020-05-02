package Logica.dados;

import Logica.dados.variations.*;


public class FabricaSpaceShip {
    
    public static SpaceShip criaSpaceShip(int i){
        
        if(i == 1){
            return new MilitaryShip();
            
        }else if(i == 2){
            return new MiningShip();
            
        }else
            return null;
        
    }

}
