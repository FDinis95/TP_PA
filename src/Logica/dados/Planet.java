package Logica.dados;

import java.util.ArrayList;


public abstract class Planet {

    private Terreno terreno;
    private ArrayList<Resource> recursos;
    private String tipo;
    private SpaceShip ss;
    private SpaceStation spaceStation;
    
    public Planet() {
    
        this.terreno = new Terreno();
        this.recursos = new ArrayList<>();
        this.ss = null;
        this.spaceStation = null;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public void setTipo(String t){
        this.tipo = t;
    }
        
    public void addRecursos(Resource r){
        recursos.add(r);

    }
    
    public Terreno getTerreno() {
        return terreno;
    }
    
    public Resource searchResource(Resource r){
        for (Resource rs : recursos) {
            if (rs.equals(r)) {
                return rs;
            }
        }       
        return null; //NAO encontrou
    }
    
    public void removeResource(Resource r){ 
        
        Resource temp = searchResource(r);
        if(temp != null)
            recursos.remove(temp);
        else
            System.out.println("Nao encontrei");
        
    }

    public ArrayList<Resource> getRecursos() {
        return recursos;
    }

    public SpaceShip getSS() {
        return ss;
    }

    public void setSS(SpaceShip ss) {
        this.ss = ss;
    }

    public SpaceStation getSpaceStation() {
        return spaceStation;
    }

    public void setSpaceStation(SpaceStation spaceStation) {
        this.spaceStation = spaceStation;
    }
    
    @Override
    public String toString() {

        String s = "Planeta " + getTipo() + "\nrecursos = ";

        for (int i = 0; i < recursos.size(); i++) {
            s += recursos.get(i).getTipo() + " ";
        }

        if (getSpaceStation() != null) {
            s += "\nSpaceStation presente";
        }
        
        s += "\nSpaceShip: " + ss;

        return s;
    }
    
}
