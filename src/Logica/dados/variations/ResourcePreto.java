package Logica.dados.variations;

import Logica.dados.Resource;
import java.io.Serializable;

public class ResourcePreto extends Resource implements Serializable{

    public ResourcePreto() {
        setTipo("Preto");
    }
    
    @Override
    public String toString() {
        return "PR";
    }
}
