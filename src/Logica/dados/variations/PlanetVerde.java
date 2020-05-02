package Logica.dados.variations;

import Logica.dados.FabricaAliens;
import Logica.dados.FabricaResources;
import Logica.dados.Planet;

public class PlanetVerde extends Planet {

    public PlanetVerde() {
        
        addRecursos(FabricaResources.criaResource(3));
        addRecursos(FabricaResources.criaResource(1));
        setTipo("Verde");
    }
}
