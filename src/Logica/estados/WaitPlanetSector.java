package Logica.estados;

import Logica.InteracaoEsperada;
import Logica.Jogo;
import Logica.dados.Alien;
import Logica.dados.FabricaAliens;
import Logica.dados.FabricaResources;
import Logica.dados.Resource;
import java.io.Serializable;


public class WaitPlanetSector extends StateAdapter implements Serializable{

    public WaitPlanetSector(Jogo jogo) {
        super(jogo);
    }

    @Override
    public Estado nextTurn() {

        getJogo().getLog().addLog("Decidiste mudar de turno, vamos buscar um novo planeta!");
        getJogo().getLog().clearLog();

        if (getJogo().getSpaceShip().getArtifact() == 5) {

            getJogo().setGameOver();
            getJogo().setVictory();
            //Win condition
            getJogo().getLog().addLog("\nTens todos os artefactos! Ganhaste o jogo!");

            getJogo().getLog().clearLog();

            return new WaitGameOver(getJogo());

        } else {
            
            getJogo().setUpgradeLimit(false);
            getJogo().setWasPlanet(true);
            getJogo().getLog().addLog("\nNext Turn!");

            getJogo().getLog().clearLog();

            return new WaitMove(getJogo());
        }
    }

    @Override
    public Estado land() {

        if(!(getJogo().getSpaceShip().hasSpecificCrew("Landing"))){
            getJogo().getLog().addLog("Não foi possivel entrar no planeta, Nao tens o Landing Officer\n");
            getJogo().getLog().clearLog();

            return new WaitPlanetSector(getJogo());
            
        }else if ((getJogo().getSpaceShip().getNumberVisits() > getJogo().getPlaneta().getRecursos().size())){
            getJogo().getLog().addLog("Não foi possivel entrar no planeta, numero de visitas maior que o numero de recursos existentes no planeta\n");
            getJogo().getLog().clearLog();
       
            return new WaitPlanetSector(getJogo());
            
        }else if ((getJogo().getSpaceShip().getDrone() == null)) {
            getJogo().getLog().addLog("Não foi possivel entrar no planeta, nao tens um drone\n");
            getJogo().getLog().clearLog();
       
            return new WaitPlanetSector(getJogo());
   
        }else {
            
            getJogo().getPlaneta().getTerreno().clearBoard();
            
            int alienChoice = (int) (Math.random() * 4) + 1;
            int recursoChoice = (int) (Math.random() * getJogo().getPlaneta().getRecursos().size()) + 0;

            //Criação do Alien
            Alien a = FabricaAliens.criaAlien(alienChoice);
            getJogo().getPlaneta().getTerreno().setAlienAttacker(a);

            Resource r = null;
            //Criação do Recurso a partir dos que existem no planeta
            for(int i = 0; i < getJogo().getPlaneta().getRecursos().size(); i++){
                if(i == recursoChoice)
                    r = FabricaResources.criaResource(getJogo().getPlaneta().getRecursos().get(i));
            }
            
//            Resource r = FabricaResources.criaResource(recursoChoice);
            getJogo().getPlaneta().getTerreno().setResource(r);
            
            //Mete o Drone no Planeta
            getJogo().getPlaneta().getTerreno().setDrone(getJogo().getSpaceShip().getDrone());
            getJogo().getPlaneta().getTerreno().getDrone().setRes(null);
            
            //Verifica a colisão dos Objectos e mete-os no terreno do planeta
            getJogo().getPlaneta().getTerreno().checkCollision(a, r, getJogo().getSpaceShip().getDrone());
            
            //Para saber o caminho de volta
            getJogo().setInitialDroneX(getJogo().getPlaneta().getTerreno().getDrone().getPosX());
            getJogo().setInitialDroneY(getJogo().getPlaneta().getTerreno().getDrone().getPosY());
            getJogo().getSpaceShip().addVisit();
            
            getJogo().getLog().addLog("Planeta: " + getJogo().getPlaneta().getTipo());
            getJogo().getLog().addLog("Recursos: " + getJogo().getPlaneta().getRecursos() +"\n");
            getJogo().getLog().addLog("" + getJogo().getPlaneta().getTerreno());
            getJogo().getLog().clearLog();

            return new WaitLanding(getJogo());
        }
    }

    @Override
    public Estado dock(){
        
        getJogo().getLog().addLog("Vamos entrar numa SpaceStation!");
        getJogo().getLog().clearLog();
        
        return new WaitSpaceStation(getJogo());
    }
    
    @Override
    public Estado useRecursos(int valor, String from, String to){
        
        if(!(getJogo().getSpaceShip().hasSpecificCrew("Cargo"))){
            
            getJogo().getLog().addLog("Nao existe um Cargo Officer, nao e possivel trocar recursos!\n");
            getJogo().getLog().clearLog();
            
            return new WaitPlanetSector(getJogo());
        }
        
        //Recursos para o Shield
        switch (valor) {
            case 1:
                //Verificação se existe recursos suficientes
                if((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Preto")) - 1) < 0){
                    getJogo().getLog().addLog("Nao tens recursos pretos suficientes para fazer esta troca!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                    
                }else  if((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Verde")) - 1) < 0){
                    getJogo().getLog().addLog("Nao tens recursos verdes suficientes para fazer esta troca!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                    
                }else  if((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Azul")) - 1) < 0){
                    getJogo().getLog().addLog("Nao tens recursos azuis suficientes para fazer esta troca!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                    
                }else if((getJogo().getSpaceShip().getShieldCapacity()+ 1) > getJogo().getSpaceShip().getMaxShield()){
                    getJogo().getLog().addLog("Nao e preciso gastar recursos, ja tens os shields no maximo!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                    
                }else{
                    
                    //Feitas as verificações, vamos substrair os Recursos e adicionar o Shield
                    getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Preto"), 1);
                    getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Verde"), 1);
                    getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Azul"), 1);
                    
                    getJogo().getSpaceShip().addShield();
                    
                    getJogo().getLog().addLog("Recursos Subtraidos e 1 Shield regenerado!\n");
                    getJogo().getLog().clearLog();
                    
                }
                
                
                break;
            
            //Recursos para o Ammo
            case 2:
                //Verificação se existe recursos suficientes
                if((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Preto")) - 1) < 0){
                    getJogo().getLog().addLog("Nao tens recursos pretos suficientes para fazer esta troca!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                    
                }else  if((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Azul")) - 1) < 0){
                    getJogo().getLog().addLog("Nao tens recursos azuis suficientes para fazer esta troca!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                    
                }else if((getJogo().getSpaceShip().getShieldCapacity()+ 1) > getJogo().getSpaceShip().getMaxWeapon()){
                    getJogo().getLog().addLog("Nao e preciso gastar recursos, ja tens a Ammo no maximo!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                    
                }else{
                    
                    //Feitas as verificações, vamos substrair os Recursos e adicionar o Shield
                    getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Preto"), 1);
                    getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Azul"), 1);
                    
                    getJogo().getSpaceShip().addWeapon();
                    
                    getJogo().getLog().addLog("Recursos Subtraidos e 1 Ammo regenerado!\n");
                    getJogo().getLog().clearLog();
                    
                }
                
                
                break;    
                
            //Recursos para o Fuel
            case 3:
                //Verificação se existe recursos suficientes
                if((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Preto")) - 1) < 0){
                    getJogo().getLog().addLog("Nao tens recursos pretos suficientes para fazer esta troca!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                    
                }else  if((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Verde")) - 1) < 0){
                    getJogo().getLog().addLog("Nao tens recursos verdes suficientes para fazer esta troca!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                    
                }else  if((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Vermelho")) - 1) < 0){
                    getJogo().getLog().addLog("Nao tens recursos vermelhos suficientes para fazer esta troca!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                    
                 //Verificar se é mesmo necessario os recursos para adicionar 1 shield
                }else if((getJogo().getSpaceShip().getFuelStorage()+ 1) > getJogo().getSpaceShip().getMaxFuel()){
                    getJogo().getLog().addLog("Nao e preciso gastar recursos, ja tens o fuel no maximo!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                    
                }else{
                    //Feitas as verificações, vamos substrair os Recursos e adicionar o Fuel
                    getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Preto"), 1);
                    getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Verde"), 1);
                    getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Vermelho"), 1);
                    
                    getJogo().getSpaceShip().addFuel();
                    
                    getJogo().getLog().addLog("Recursos Subtraidos e 1 Fuel regenerado!\n");
                    getJogo().getLog().clearLog();
                }
                
                
                break;
                
            //Recurso para outro recurso    
            case 4:
                //Verificação se existe recursos suficientes
                if((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo(from)) - 1) < 0){
                    getJogo().getLog().addLog("Nao tens recursos " + from + " suficientes para fazer esta troca!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                }else if(getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo(to)) + 1 > getJogo().getSpaceShip().getMaxCargo()){
                    
                    getJogo().getLog().addLog("Nao e preciso gastar recursos, ja tens o cargo pretendido no maximo!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                    
                }else{
                    //Feitas as verificações, vamos substrair os Recursos e adicionar o Fuel
                    getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo(from), 1);
                    getJogo().getSpaceShip().addCargo(getJogo().getSpaceShip().getSpecificCargo(to), 1);
                    
                    getJogo().getLog().addLog("Recurso " + from + " Subtraido e Recurso " + to + " Adicionado!\n");
                    getJogo().getLog().clearLog();
                }
                
                
                break;
                
            //Recursos para aumentar a vida do drone    
            case 5:
                //Verificação se ja existe um drone!
                if(getJogo().getSpaceShip().getDrone() != null){
                    getJogo().getLog().addLog("Ja tens um drone, nao precisas de outro!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                    
                }   //Verificação se existe recursos suficientes
                if((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Preto")) - 1) < 0){
                    getJogo().getLog().addLog("Nao tens recursos pretos suficientes para fazer esta troca!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                    
                }else  if((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Verde")) - 1) < 0){
                    getJogo().getLog().addLog("Nao tens recursos verdes suficientes para fazer esta troca!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                    
                }else  if((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Vermelho")) - 1) < 0){
                    getJogo().getLog().addLog("Nao tens recursos vermelhos suficientes para fazer esta troca!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                    
                }else  if((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Azul")) - 1) < 0){
                    getJogo().getLog().addLog("Nao tens recursos azuis suficientes para fazer esta troca!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                    
                }else if((getJogo().getSpaceShip().getDrone().getVida() + 1) > getJogo().getSpaceShip().getDrone().getMaxVida()){
                    getJogo().getLog().addLog("Nao precisas de aumentar a vida do Drone, ja esta no maximo!\n");
                    getJogo().getLog().clearLog();
                    
                    return new WaitPlanetSector(getJogo());
                    
                }else{
                    //Feitas as verificações, vamos substrair os Recursos e adicionar o Fuel
                    getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Preto"), 1);
                    getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Verde"), 1);
                    getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Vermelho"), 1);
                    getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Azul"), 1);
                    
                    getJogo().getSpaceShip().getDrone().addVida();
                    
                    getJogo().getLog().addLog("Recursos Subtraidos e 1 vida regenerada do Drone!\n");
                    getJogo().getLog().clearLog();
                }
                break;
                
            default:
                break;
        }
        
        
        return new WaitPlanetSector(getJogo());
    }
    
    @Override
    public InteracaoEsperada getInteracaoEsperada() {
        return InteracaoEsperada.INTERACAO_WAIT_PLANET_SECTOR;
        
    }
    
}
