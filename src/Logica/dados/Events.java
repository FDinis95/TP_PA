package Logica.dados;

import Logica.Jogo;

public abstract class Events {
    
    private int ID;
    public void applyEvent(Jogo game){  }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
  
}
