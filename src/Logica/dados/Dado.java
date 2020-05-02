package Logica.dados;

import java.util.ArrayList;


public class Dado {

    private int diceValue;

    public Dado() {}

    public int getValue(){
        return diceValue;
    }
    public void rollDice() {
        
        diceValue = (int)(Math.random()*6) + 1;
    
    }    
    
    @Override
    public String toString() {
        return "" + diceValue;
    }
    
    
       
}
