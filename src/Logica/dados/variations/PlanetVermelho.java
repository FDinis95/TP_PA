package Logica.dados.variations;

import Logica.dados.FabricaResources;
import Logica.dados.Planet;
import java.io.Serializable;


public class PlanetVermelho extends Planet implements Serializable{

    public PlanetVermelho() {
        
        addRecursos(FabricaResources.criaResource(3));
        addRecursos(FabricaResources.criaResource(4));
        setTipo("Vermelho");
    }
}
