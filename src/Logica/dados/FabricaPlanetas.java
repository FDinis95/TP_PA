package Logica.dados;

import Logica.dados.variations.*;

public class FabricaPlanetas {

    public static Planet criaPlaneta(int tipo){
        
        switch (tipo) {
            case 1:
                return new PlanetVerde();
                
            case 2:
                return new PlanetPreto();
                
            case 3:
                return new PlanetVermelho();
                
            case 4:
                return new PlanetAzul();
                
            default:
                return null;
        }
        
    }
}
