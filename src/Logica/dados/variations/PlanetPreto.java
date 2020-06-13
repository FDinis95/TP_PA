package Logica.dados.variations;

import Logica.dados.FabricaResources;
import Logica.dados.Planet;
import java.io.Serializable;


public class PlanetPreto extends Planet implements Serializable{

    public PlanetPreto() {
        
        addRecursos(FabricaResources.criaResource(2));
        addRecursos(FabricaResources.criaResource(4));
        setTipo("Preto");
    }    
}
