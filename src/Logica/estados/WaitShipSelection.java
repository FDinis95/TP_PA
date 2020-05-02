package Logica.estados;

import Logica.Jogo;
import Logica.dados.FabricaSpaceShip;


public class WaitShipSelection extends StateAdapter {

    public WaitShipSelection(Jogo jogo){
        super(jogo);
    }

    @Override
    public Estado selectShip(int valor) {
        //Cria as cartas referentes à nave (Tipo, Fuel, Cargo)
        getJogo().setSpaceShip(FabricaSpaceShip.criaSpaceShip(valor));
        getJogo().getLog().addLog("Starting ship: \n" + getJogo().getSpaceShip().toString());
        getJogo().getLog().printLogs();
        getJogo().getLog().clearLog();
        
       
        return new WaitMove(getJogo());
    }
    
    
}
