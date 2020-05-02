package Logica.dados.variations;

import Logica.dados.Resource;

public class ResourceVermelho extends Resource{

    public ResourceVermelho() {
        setTipo("Vermelho");
    }

    @Override
    public String toString() {
        return "VM";
    }
}
