package Logica.dados;

public abstract class Alien {
    
    private String tipo; //[preto, azul, verde, vermelho]
    private int vida;
    private int attack;
    private int posX, posY;
    
    public Alien() {
        
        this.vida = 1;
        this.attack = 1;
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
    
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getAttack() {
        return attack;
    }

    @Override
    public String toString() {
        
        return "A";
    }
    
    

}
