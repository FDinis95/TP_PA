package Logica.dados;

public abstract class Resource {
   
    private String tipo;
    private int posX, posY;

    public Resource() {
        this.posX = 0;
        this.posY = 0;
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

    public String getTipo() {
        return tipo;
        
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
        
    }
}
