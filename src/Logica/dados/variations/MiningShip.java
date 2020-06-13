package Logica.dados.variations;

import Logica.dados.Resource;
import Logica.dados.SpaceShip;
import java.io.Serializable;

public class MiningShip extends SpaceShip implements Serializable{

    public MiningShip() {
        super();
        setFuelStorage(53);
        setShieldCapacity(18);
        setWeaponSystem(9);
        
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
    public int getMaxWeapon(){
        return 9;
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
     
        if(getUpgradeLevel() == 0 && (getSpecificCargoValue(getSpecificCargo(r.getTipo())) + value) >=  6)
            getCargo().replace(getSpecificCargo(r.getTipo()), 6);
        
        else if(getUpgradeLevel() == 1 && (getSpecificCargoValue(getSpecificCargo(r.getTipo())) + value) >=  12)
            getCargo().replace(getSpecificCargo(r.getTipo()), 12);
        
        else if(getUpgradeLevel() == 2 && (getSpecificCargoValue(getSpecificCargo(r.getTipo())) + value) >=  18)
            getCargo().replace(getSpecificCargo(r.getTipo()), 18);
        
        else if(getUpgradeLevel() == 3 && (getSpecificCargoValue(getSpecificCargo(r.getTipo())) + value) >=  24)
            getCargo().replace(getSpecificCargo(r.getTipo()), 24);
        
        else
            getCargo().replace(getSpecificCargo(r.getTipo()), (getSpecificCargoValue(getSpecificCargo(r.getTipo())) + value));
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
    public void addWeapon() {
        if(getWeaponSystem() >= 9) {
            setWeaponSystem(9);

        }else
            setWeaponSystem(getWeaponSystem() + 1);

    }
    
    @Override
    public String toString() {
        
        String s = "Mining{" + "\n\tfuelStorage = " + getFuelStorage() + "\n\tshieldCapacity = " + getShieldCapacity() +
                "\n\tweaponSystem = " + getWeaponSystem() + "\n\tcargo " + getCargo();
        
        s += "\n\tupgradeLevel = " + getUpgradeLevel() +
                "\n\tcrewMembers = " + getCrewMembers() + "\n\tartifact = " + getArtifact() + "\n\tdrone = " + getDrone() + "\n}";
        
        return s;
    }

}
