package Logica.dados.variations;

import Logica.dados.FabricaAliens;
import Logica.dados.FabricaResources;
import Logica.dados.Planet;


public class PlanetPreto extends Planet{

    public PlanetPreto() {
        
        addRecursos(FabricaResources.criaResource(2));
        addRecursos(FabricaResources.criaResource(4));
        setTipo("Preto");
    }    
}
