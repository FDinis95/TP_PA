package Logica.dados.variations;

import Logica.dados.Resource;
import Logica.dados.SpaceShip;

public class MilitaryShip extends SpaceShip {

    public MilitaryShip() {
        super();
        setFuelStorage(35);
        setShieldCapacity(9);
    }
    
    @Override
    public int getMaxFuel(){
        return 35;
    }
    
    @Override
    public int getMaxShield(){
        return 9;
    }
    
    @Override
    public int getMaxCargo(){
        
        switch (getUpgradeLevel()) {
            case 0:
                return 6;
                
            case 1:
                return 12;
                
            default:
                return 0;
        }
    }
    
    @Override
    public void addCargo(Resource r, int value){
     
        if(getUpgradeLevel() == 0 && getSpecificCargoValue(r) + value >=  6)
            getCargo().put(r, 6);
        
        else if(getUpgradeLevel() == 1 && getSpecificCargoValue(r) + value >=  12)
            getCargo().put(r, 12);
        
        else
            getCargo().put(r, getSpecificCargoValue(r) + value);

    }
    
    @Override
    public void addFuel(){
        if(getFuelStorage() + 1 >= getMaxFuel()){
            setFuelStorage(getMaxFuel());
        }else
            setFuelStorage(getFuelStorage() + 1);
    }
    
    @Override
    public void addUpgradeLevel(){
        if(getUpgradeLevel() + 1 >= 1){
            setUpgradeLevel(1);
        }else
            setUpgradeLevel(getUpgradeLevel() + 1);
            
    }
    
    @Override
    public void addShield(){
        if(getShieldCapacity() + 1 >= 9){
            setShieldCapacity(9);
            
        }else
            setShieldCapacity(getShieldCapacity() + 1);
    }

    @Override
    public String toString() {
        
        String s = "Military{" + "\n\tfuelStorage = " + getFuelStorage() + "\n\tshieldCapacity = " + getShieldCapacity() +
                "\n\tcargo " + getCargo();
        
        s += "\n\tupgradeLevel = " + getUpgradeLevel() +
                "\n\tcrewMembers = " + getCrewMembers() + "\n\tartifact = " + getArtifact() + "\n\tdrone = " + getDrone() + "\n}";
        
        return s;
    }
    
}
