package Logica.dados.variations;

import Logica.dados.Resource;
import java.io.Serializable;

public class ResourceRoxo extends Resource implements Serializable{

    public ResourceRoxo() {
        setTipo("Roxo");
    }
    
    @Override
    public String toString() {
        return "R";
    }
}
