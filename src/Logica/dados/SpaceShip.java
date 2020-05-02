package Logica.dados;

import Logica.dados.variations.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SpaceShip {
    
    private int fuelStorage;
    private int shieldCapacity;
//    private int weaponSystem;
    
    //Considerar e talvez mudar mesmo para LinkedHashMap<Resource, int> cargoHold
    //para preservar a ordem dos recursos
    private LinkedHashMap<Resource, Integer> cargo;
    
    private int upgradeLevel;
    private ArrayList<String> crewMembers; 
    private int artifact;
    private Drone drone;
    private int numberVisits;

    public SpaceShip() {
        
        this.upgradeLevel = 0;
        this.numberVisits = 0;
        this.drone = new Drone();
        this.artifact = 0;
        this.crewMembers = new ArrayList<String>() {
            {
                add("Captain");
                add("Navigation");
                add("Landing");
                add("Shields");
//                add("Weapons");
                add("Cargo");
            }
        };
        this.cargo = new LinkedHashMap<Resource, Integer>(){
            {
                put(new ResourceAzul(), 5);
                put(new ResourcePreto(), 5);
                put(new ResourceVerde(), 5);
                put(new ResourceVermelho(), 5);
            }
        };
        
    }
    
    public int getNumberVisits() {
        return numberVisits;
    }
    
    public void addVisit(){
        this.numberVisits++;
    }
    
    public void resetVisits(){
        this.numberVisits = 0;
    }
    
    public LinkedHashMap<Resource, Integer> getCargo() {
        return cargo;
        
    }
    
    public void addCargo(Resource r, int value){}
    
    public void subCargo(Resource r, int value){
        if(cargo.containsKey(r)){
            if((cargo.get(r) - value) <= 0)
                cargo.put(r, 0);
            else
                cargo.put(r, cargo.get(r) - value);
        }
    }
    
    public int getMaxCargo(){
        return 0;
    }
    
    public Resource getSpecificCargo(String nome){
        for(Resource s : cargo.keySet()){
            if(s.getTipo().equals(nome)){
                return s;
            }
        }
        return null;
    }
    
    public int getSpecificCargoValue(Resource r){
        
        if(cargo.containsKey(r)){
            return cargo.get(r);
        }
        
        return 0;
        
    }
    
    public int getFuelStorage() {
        return fuelStorage;
    }
    
    public int getMaxFuel(){
        return 0;
    }

    public void setFuelStorage(int fuelStorage) {
        this.fuelStorage = fuelStorage;
    }
    
    public void addFuel(){}
    
    public void subFuel(int valor){
        if((fuelStorage - valor) <= 0){
            fuelStorage = 0;
        }else
            fuelStorage -= valor;
    }

    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    public void setUpgradeLevel(int upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
    }

    public void addUpgradeLevel(){}
    
    public void subUpgradeLevel(){
        if(upgradeLevel - 1 <= 0){
            upgradeLevel = 0;
        }else
            upgradeLevel--;        
    }
    
    public ArrayList<String> getCrewMembers() {
        return crewMembers;
    }

    public void setCrewMembers(ArrayList<String> crewMembers) {
        this.crewMembers = crewMembers;
    }
    
    public int pesquisaCrewMember(String nome){
        
        for(String s : crewMembers){
            if(s.equals(nome))
                return 1;
        }
        
        return 0;    
    }
    
    public boolean hasSpecificCrew(String nome){
        
        int temp = pesquisaCrewMember(nome);
        
        return temp == 1; //Encontrou! (É o que queremos)
        
    }
    
    public String getSpecificCrew(String nome){
        
        for(String s : crewMembers){
            if(s.equals(nome)){
                return s;
            }
        }
        
        return null;
    }
    
    public boolean removeCrewMember(String nome){
        
        for(int i = 0; i < crewMembers.size(); i++){
            if(crewMembers.get(i).equals(nome)){
                crewMembers.set(i, " ");
                return true;
            }
        }
        
        return false;
    }

    public boolean addCrewMember(String nome){
        
        boolean temp = hasSpecificCrew(nome);
        
        if(!temp){ //Não existe
            crewMembers.add(nome);
            return true;       
        }
            
        return false;
    }
    
    public int getArtifact() {
        return artifact;
    }
    
    public void addArtifact(){
        artifact++;
    }

    public int getShieldCapacity() {
        return shieldCapacity;
    }
    
    public int getMaxShield(){
        return 0;
    }

    public void setShieldCapacity(int shieldCapacity) {
        this.shieldCapacity = shieldCapacity;
    }
    
    public void addShield(){}
    
    public void subShield(int value){
        if((shieldCapacity - value) <= 0){
            shieldCapacity = 0;
            
        }else
            shieldCapacity -= value;
    }
    
    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
  
}
