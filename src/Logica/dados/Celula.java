package Logica.dados;

import java.io.Serializable;


public abstract class Celula implements Serializable{
    
    public abstract boolean putEntity(Alien a, int x, int y);  
    public abstract boolean putEntity(Resource c, int x, int y);   
    public abstract boolean putEntity(Drone d, int x, int y);
            
}
