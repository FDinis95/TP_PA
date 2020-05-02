package Logica.dados.variations;

import Logica.dados.*;
import Logica.dados.Planet;


public class PlanetAzul extends Planet {
    
    public PlanetAzul() {
        
        addRecursos(FabricaResources.criaResource(2));
        addRecursos(FabricaResources.criaResource(1));
        addRecursos(FabricaResources.criaResource(4));
        addRecursos(FabricaResources.criaResource(5));
        setTipo("Azul");
    }
}
