package Logica.dados.variations;

import Logica.dados.FabricaAliens;
import Logica.dados.FabricaResources;
import Logica.dados.Planet;


public class PlanetVermelho extends Planet {

    public PlanetVermelho() {
        
        addRecursos(FabricaResources.criaResource(3));
        addRecursos(FabricaResources.criaResource(4));
        setTipo("Vermelho");
    }
}
