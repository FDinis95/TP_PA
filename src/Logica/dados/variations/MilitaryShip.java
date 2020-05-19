package Logica.dados.variations;

import Logica.dados.Resource;
import Logica.dados.SpaceShip;

public class MilitaryShip extends SpaceShip {

    
    
    public MilitaryShip() {
        super();
        setFuelStorage(35);
        setShieldCapacity(9);
        setWeaponSystem(18);
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
    public int getMaxWeapon(){
        return 18;
        
    }

    @Override
    public void addWeaponUpgradeLimit(){
        if((getWeaponUpgradeLimit() + 1) >= 2){
            setWeaponUpgradeLimit(2);
            
        }else
            setWeaponUpgradeLimit(getWeaponUpgradeLimit() + 1);
        
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
     
        if(getUpgradeLevel() == 0 && (getSpecificCargoValue(getSpecificCargo(r.getTipo())) + value) >=  6)
            getCargo().replace(getSpecificCargo(r.getTipo()), 6);
        
        else if(getUpgradeLevel() == 1 && (getSpecificCargoValue(getSpecificCargo(r.getTipo())) + value) >=  12)
            getCargo().replace(getSpecificCargo(r.getTipo()), 12);
        
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
    public void addWeapon(){
        if(getWeaponUpgradeLimit() == 1){
            if(getWeaponSystem() >= 9){
                setWeaponSystem(9);
                
            }else
                setWeaponSystem(getWeaponSystem() + 1);
            
        }else
            if(getWeaponSystem() >= 18){
                setWeaponSystem(18);
                
            }else
                setWeaponSystem(getWeaponSystem() + 1);
        
    }

    @Override
    public String toString() {
        
        String s = "Military{" + "\n\tfuelStorage = " + getFuelStorage() + "\n\tshieldCapacity = " + getShieldCapacity() + 
                "\n\tweaponSystem = " + getWeaponSystem() + "\n\tcargo " + getCargo();
        
        s += "\n\tupgradeLevel = " + getUpgradeLevel() +
                "\n\tcrewMembers = " + getCrewMembers() + "\n\tartifact = " + getArtifact() + "\n\tdrone = " + getDrone() + "\n}";
        
        return s;
    }
    
}
