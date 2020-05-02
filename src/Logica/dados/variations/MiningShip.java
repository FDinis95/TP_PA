package Logica.dados.variations;

import Logica.dados.Resource;
import Logica.dados.SpaceShip;

public class MiningShip extends SpaceShip {

    public MiningShip() {
        super();
        setFuelStorage(53);
        setShieldCapacity(18);
        
    }
    
    @Override
    public int getMaxFuel(){
        return 53;
    }
    
    @Override
    public int getMaxShield(){
        return 18;
    }
    
    @Override
    public int getMaxCargo(){
        
        switch (getUpgradeLevel()) {
            case 0:
                return 6;
                
            case 1:
                return 12;
                
            case 2:
                return 18;
                
            case 3:
                return 24;
                
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
        
        else if(getUpgradeLevel() == 2 && getSpecificCargoValue(r) + value >=  18)
            getCargo().put(r, 18);
        
        else if(getUpgradeLevel() == 3 && getSpecificCargoValue(r) + value >=  24)
            getCargo().put(r, 24);
        
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
        if(getUpgradeLevel() + 1  >= 3){
            setUpgradeLevel(3);
        }else
            setUpgradeLevel(getUpgradeLevel() + 1);
            
    }
    
    @Override
    public void addShield(){
        if(getShieldCapacity() + 1 >= getMaxShield()){
            setShieldCapacity(getMaxShield());
            
        }else
            setShieldCapacity(getShieldCapacity() + 1);
    }
    
    @Override
    public String toString() {
        
        String s = "Mining{" + "\n\tfuelStorage = " + getFuelStorage() + "\n\tshieldCapacity = " + getShieldCapacity() +
                "\n\tcargo " + getCargo();
        
        s += "\n\tupgradeLevel = " + getUpgradeLevel() +
                "\n\tcrewMembers = " + getCrewMembers() + "\n\tartifact = " + getArtifact() + "\n\tdrone = " + getDrone() + "\n}";
        
        return s;
    }

}
