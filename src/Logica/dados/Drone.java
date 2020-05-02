package Logica.dados;


public class Drone {

    private int posX, posY;
    private int vida;
    private Resource res;
    
    public Drone() {
        this.res = null;
        this.posX = 0;
        this.posY = 0;
        this.vida = 6;
    }

    public int getVida() {
        return vida;
    }
    
    public void setVida(int v){
        this.vida = v;
        
    }
    
    public void addVida(){
        if(getVida() + 1 > getMaxVida())
            this.vida = 6;
        
        else
           this.vida++; 
    }
    
    public void subVida(){
        if(getVida() - 1 < 0)
            this.vida = 0;
        else
            this.vida--;
    }
    
    public int getMaxVida(){
        return 6;
        
    }
    
    public boolean hasResource(){
        return res != null;
    }
    
    public int getPosX() {
        return posX;
    }

    public void setPosX(int x) {
        this.posX = x;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int y) {
        this.posY = y;
    }

    public Resource getRes() {
        return res;
    }

    public void setRes(Resource res) {
        this.res = res;
    }

    @Override
    public String toString() {
        return "D";
    }

    
}
