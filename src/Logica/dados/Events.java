package Logica.dados;

import Logica.Jogo;
import java.io.Serializable;

public abstract class Events implements Serializable{
    
    private int ID;
    public void applyEvent(Jogo game){  }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
  
}
