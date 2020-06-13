package Logica.dados;

import Logica.dados.variations.*;
import java.io.Serializable;

public class FabricaAliens implements Serializable{

    public static Alien criaAlien(int tipo){
        switch (tipo) {
            case 1:
                return new AlienVerde();
                
            case 2:
                return new AlienPreto();
                
            case 3:
                return new AlienVermelho();
                
            case 4:
                return new AlienAzul();
                
            default:
                return null;
        }
        
    }
}
