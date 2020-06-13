package Logica.dados.variations;

import Logica.dados.FabricaResources;
import Logica.dados.Planet;
import java.io.Serializable;

public class PlanetVerde extends Planet implements Serializable{

    public PlanetVerde() {
        
        addRecursos(FabricaResources.criaResource(3));
        addRecursos(FabricaResources.criaResource(1));
        setTipo("Verde");
    }
}
