package Logica.dados.variations;

import Logica.dados.Resource;
import java.io.Serializable;

public class ResourceAzul extends Resource implements Serializable{

    public ResourceAzul() {
        setTipo("Azul");
    }
    
    @Override
    public String toString() {
        return "AZ";
    }
    
}
