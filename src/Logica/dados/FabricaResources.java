package Logica.dados;

import Logica.dados.variations.*;


public class FabricaResources {

    public static Resource criaResource(int tipo){
        
        switch (tipo) {
            case 1:
                return new ResourceVerde();
                
            case 2:
                return new ResourcePreto();
                
            case 3:
                return new ResourceVermelho();
                
            case 4:
                return new ResourceAzul();
                
            case 5:
                return new ResourceRoxo();
                
            default:
                return null;
        }
    }
    
}
