package Logica.estados;

import Logica.InteracaoEsperada;
import Logica.Jogo;
import Logica.dados.FabricaPlanetas;
import Logica.dados.SpaceStation;
import java.io.Serializable;

public class WaitMove extends StateAdapter implements Serializable{

    public WaitMove(Jogo jogo) {
        super(jogo);

    }
    
    //Move Feito Manualmente!
    @Override
    public Estado move(int move, int sector, int planet){
        if(getJogo().getWasPlanet()){
            
            getJogo().getSpaceShip().subFuel(1);

            return new WaitEvent(getJogo());
            
        }else{
            if (sector == 2){
            getJogo().getLog().addLog("Planeta com SpaceStation!");
            getJogo().setPlaneta(FabricaPlanetas.criaPlaneta(planet));
            getJogo().getPlaneta().setSS(getJogo().getSpaceShip());
            getJogo().getPlaneta().setSpaceStation(new SpaceStation());
            getJogo().setSpaceStation(getJogo().getPlaneta().getSpaceStation());
        
        }else{
            getJogo().getLog().addLog("Planeta sem SpaceStation!");
            getJogo().setPlaneta(FabricaPlanetas.criaPlaneta(planet));
            getJogo().getPlaneta().setSS(getJogo().getSpaceShip());
            getJogo().setSpaceStation(null);
            
        }

        getJogo().getLog().addLog("Resultado final:");
        
        if(move == 1){
            getJogo().getLog().addLog("Tipo de Movimento: Normal");
            getJogo().getSpaceShip().subFuel(1);
            
        }else{
            getJogo().getLog().addLog("Tipo de Movimento: Buraco Negro");
            if (getJogo().getSpaceShip().getCrewMembers().size() == 3) { //Não existe o Shields Officer
                    if (getJogo().getSpaceShip().getShieldCapacity() == 0) { //Não tem shield suficiente, morre um membro
                        getJogo().getSpaceShip().subFuel(4);
                        getJogo().getSpaceShip().getCrewMembers().remove(getJogo().getSpaceShip().getCrewMembers().size() - 1);
                    }

                    getJogo().getSpaceShip().subFuel(4);
                    getJogo().getSpaceShip().subShield(4);

                } else { //Existe um Shields Officer
                    if (getJogo().getSpaceShip().getShieldCapacity() == 0) {
                        getJogo().getSpaceShip().subFuel(3);
                        getJogo().getSpaceShip().getCrewMembers().remove(getJogo().getSpaceShip().getCrewMembers().size() - 1);

                    }else{
                       getJogo().getSpaceShip().subFuel(3);
                        getJogo().getSpaceShip().subShield(2);
                        
                    }
                }
        }
        
        if(sector == 1){
            getJogo().getLog().addLog("Tipo de Sector: Branco");
        }else
            getJogo().getLog().addLog("Tipo de Sector: Vermelho");
        
            getJogo().getLog().addLog("Tipo de Planeta: " + getJogo().getPlaneta().getTipo());
            getJogo().getLog().clearLog();
        
        }
        
        getJogo().setWasPlanet(true);
        return new WaitPlanetSector(getJogo());
    }

    @Override
    public Estado move() {

        int movType = 0;
        int sectorType = 0;
        int planetType = 0;
        movType = (int) (Math.random() * 8) + 1;

        if (movType <= 1) { // É um buraco Negro!
            getJogo().getLog().addLog("O resultado do movimento foi: " + movType + ", logo e um movimento por um buraco negro!");

            if (getJogo().getWasPlanet()) {
                if (getJogo().getSpaceShip().getCrewMembers().size() == 3) { //Não existe o Shields Officer
                    if (getJogo().getSpaceShip().getShieldCapacity() == 0) { //Não tem shield suficiente, morre um membro
                        getJogo().getSpaceShip().subFuel(4);
                        getJogo().getSpaceShip().getCrewMembers().remove(getJogo().getSpaceShip().getCrewMembers().size() - 1);
                    }

                    getJogo().getSpaceShip().subFuel(4);
                    getJogo().getSpaceShip().subShield(4);

                } else { //Existe um Shields Officer
                    if (getJogo().getSpaceShip().getShieldCapacity() == 0) {
                        getJogo().getSpaceShip().subFuel(3);
                        getJogo().getSpaceShip().getCrewMembers().remove(getJogo().getSpaceShip().getCrewMembers().size() - 1);

                    }else{
                       getJogo().getSpaceShip().subFuel(3);
                        getJogo().getSpaceShip().subShield(2);
                        
                    }
                }

                return new WaitEvent(getJogo());

            } else { //Não foi um planeta antes
                sectorType = (int) (Math.random() * 10) + 1; //Tipo de sector
                
                if (sectorType <= 3) { //Planeta Com SpaceStation
                    getJogo().getLog().addLog("Resultado do sector foi: " + sectorType + ", logo e um planeta com SpaceStation!");
                    planetType = (int) (Math.random() * 4) + 1;
                    getJogo().setPlaneta(FabricaPlanetas.criaPlaneta(planetType));
                    getJogo().getPlaneta().setSS(getJogo().getSpaceShip());
                    getJogo().getPlaneta().setSpaceStation(new SpaceStation());
                    getJogo().setSpaceStation(getJogo().getPlaneta().getSpaceStation());

                } else { //Planeta sem SpaceStation
                    getJogo().getLog().addLog("Resultado do sector foi: " + sectorType + ", logo e um planeta sem SpaceStation!");
                    planetType = (int) (Math.random() * 4) + 1;
                    getJogo().setPlaneta(FabricaPlanetas.criaPlaneta(planetType));
                    getJogo().getPlaneta().setSS(getJogo().getSpaceShip());
                    getJogo().setSpaceStation(null);

                }

                if (getJogo().getSpaceShip().getCrewMembers().size() == 3) { //Não existe o Shields Officer
                    if (getJogo().getSpaceShip().getShieldCapacity() == 0) { //Não tem shield suficiente, morre um membro
                        getJogo().getSpaceShip().subFuel(4);
                        getJogo().getSpaceShip().getCrewMembers().remove(getJogo().getSpaceShip().getCrewMembers().size() - 1);
                    } else {
                        getJogo().getSpaceShip().subFuel(4);
                        getJogo().getSpaceShip().subShield(4);
                    }

                } else { //Existe um Shields Officer
                    if (getJogo().getSpaceShip().getShieldCapacity() == 0) {
                        getJogo().getSpaceShip().subFuel(3);
                        getJogo().getSpaceShip().getCrewMembers().remove(getJogo().getSpaceShip().getCrewMembers().size() - 1);

                    } else {
                        getJogo().getSpaceShip().subFuel(3);
                        getJogo().getSpaceShip().subShield(2);

                    }
                }
                             
                getJogo().getLog().addLog("Resultado do tipo de sector: \n" + getJogo().getPlaneta() + "\n");
                getJogo().getLog().clearLog();

                return new WaitPlanetSector(getJogo());
            }
        } else { // É um movimento normal
            getJogo().getLog().addLog("O resultado do random foi: " + movType + ", logo e um movimento normal!");

            if (getJogo().getWasPlanet()) {

                getJogo().getSpaceShip().subFuel(1);

                return new WaitEvent(getJogo());

            } else {
                sectorType = (int) (Math.random() * 10) + 1; //Tipo de sector

                if (sectorType <= 3) { //Planeta Com SpaceStation
                    getJogo().getLog().addLog("Resultado do sector foi: " + sectorType + ", logo e um planeta com SpaceStation!");
                    planetType = (int) (Math.random() * 4) + 1;
                    getJogo().setPlaneta(FabricaPlanetas.criaPlaneta(planetType));
                    getJogo().getPlaneta().setSS(getJogo().getSpaceShip());
                    getJogo().getPlaneta().setSpaceStation(new SpaceStation());
                    getJogo().setSpaceStation(getJogo().getPlaneta().getSpaceStation());

                } else { //Planeta Sem SpaceStation
                    getJogo().getLog().addLog("Resultado do sector foi: " + sectorType + ", logo e um planeta sem SpaceStation!");
                    planetType = (int) (Math.random() * 4) + 1;
                    getJogo().setPlaneta(FabricaPlanetas.criaPlaneta(planetType));
                    getJogo().getPlaneta().setSS(getJogo().getSpaceShip());
                    getJogo().setSpaceStation(null);

                }

                getJogo().getSpaceShip().subFuel(1);
                getJogo().setWasPlanet(true);
                
                getJogo().getLog().addLog("Resultado do tipo de sector: \n" + getJogo().getPlaneta());
                getJogo().getLog().clearLog();

                return new WaitPlanetSector(getJogo());
            }
        }
    }
    
    @Override
    public InteracaoEsperada getInteracaoEsperada() {
        return InteracaoEsperada.INTERACAO_WAIT_MOVE;
        
    }
    

}
