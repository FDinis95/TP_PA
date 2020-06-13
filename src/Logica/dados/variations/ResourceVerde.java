package Logica.dados.variations;

import Logica.dados.Resource;
import java.io.Serializable;

public class ResourceVerde extends Resource implements Serializable{

    public ResourceVerde() {
        setTipo("Verde");
        
    } 
    
    @Override
    public String toString() {
        return "VD";
    }
}
