package Logica;

import java.beans.PropertyChangeSupport;


public class ObservableGame {

    MaquinaEstados maquina;
    PropertyChangeSupport pcs;

    public ObservableGame(MaquinaEstados machine) {
        this.maquina = machine;
        this.pcs = new PropertyChangeSupport(machine);
    }
    
    
    
    
}
