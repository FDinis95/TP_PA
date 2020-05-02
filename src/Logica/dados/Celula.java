package Logica.dados;


public abstract class Celula {
    
    public abstract boolean putEntity(Alien a, int x, int y);  
    public abstract boolean putEntity(Resource c, int x, int y);   
    public abstract boolean putEntity(Drone d, int x, int y);
            
}
