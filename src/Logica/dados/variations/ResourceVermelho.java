package Logica.dados.variations;

import Logica.dados.Resource;
import java.io.Serializable;

public class ResourceVermelho extends Resource implements Serializable{

    public ResourceVermelho() {
        setTipo("Vermelho");
    }

    @Override
    public String toString() {
        return "VM";
    }
}
