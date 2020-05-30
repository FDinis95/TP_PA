package Logica;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class ObservableGame {

    private MaquinaEstados maquina;
    private PropertyChangeSupport propertyChangeSupport;

    public ObservableGame(MaquinaEstados machine) {
        this.maquina = machine;
        propertyChangeSupport = new PropertyChangeSupport(machine);
        
    }
    
    public void addPropertyChangeListener(PropertyChangeListener listener){
        propertyChangeSupport.addPropertyChangeListener(listener);
        
    }
    
    public void setup(){
        this.maquina.setup();
        
        propertyChangeSupport.firePropertyChange(null, null, null);
    }
    
    public void start(int valor){
        this.maquina.start(valor);
        
        
        propertyChangeSupport.firePropertyChange(null, null, null);
    }
    
    public InteracaoEsperada getInteracaoEsperada(){
        return maquina.getInteracaoEsperada();
        
    }
    
    
}
