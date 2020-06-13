package Logica.estados;

import Logica.InteracaoEsperada;
import Logica.Jogo;
import Logica.dados.FabricaSpaceShip;
import java.io.Serializable;


public class WaitShipSelection extends StateAdapter implements Serializable{

    public WaitShipSelection(Jogo jogo){
        super(jogo);
    }

    @Override
    public Estado selectShip(int valor) {
        //Cria as cartas referentes à nave (Tipo, Fuel, Cargo)
        getJogo().setSpaceShip(FabricaSpaceShip.criaSpaceShip(valor));
        getJogo().getLog().addLog("Starting ship: \n" + getJogo().getSpaceShip().toString() + "\n");
        getJogo().getLog().clearLog();
        
       
        return new WaitMove(getJogo());
    }
    
    @Override
    public InteracaoEsperada getInteracaoEsperada() {
        return InteracaoEsperada.INTERACAO_WAIT_SHIP_SELECTION;
        
    }
}
