package Logica.estados;

import Logica.Jogo;
import Logica.dados.Alien;
import Logica.dados.FabricaAliens;
import Logica.dados.variations.ResourceRoxo;


public class WaitLanding extends StateAdapter{

    public WaitLanding(Jogo jogo) {
        super(jogo);
    }
    
    @Override
    public Estado moveToResource(int moveX, int moveY) {

        if (getJogo().getPlaneta().getTerreno().checkFight()) {

            getJogo().getLog().addLog("O drone encontrou um alien, vai haver uma luta!");
            getJogo().getLog().printLogs();
            getJogo().getLog().clearLog();

            return new WaitAlienAttack(getJogo());

        } else {

            getJogo().getPlaneta().getTerreno().moveEntities(moveX, moveY);

            getJogo().getLog().addLog("Drone em movimento!");
            getJogo().getLog().printLogs();
            getJogo().getLog().clearLog();

            return new WaitLanding(getJogo());
        }
    }

    @Override
    public Estado hasResource() {

        //Fazer um lançamento de dado para saber quantos recursos conseguimos
        getJogo().getDice().rollDice();
        int quantidade = getJogo().getDiceRoll();
        
        //Adiciona o cargo
        if(getJogo().getPlaneta().getTerreno().getResource() instanceof ResourceRoxo){
            getJogo().getSpaceShip().addArtifact();
            
        }else
            getJogo().getSpaceShip().addCargo(getJogo().getPlaneta().getTerreno().getResource(), quantidade);
        
        //Retirar o Resource do arrayList do planeta
        getJogo().getPlaneta().removeResource(getJogo().getPlaneta().getTerreno().getDrone().getRes());
        
        //Tirar 1 de Fuel
        getJogo().getSpaceShip().subFuel(1);
         
        getJogo().getLog().addLog("O drone encontrou o recurso, vai voltar a base e depositar o que tem!");
        getJogo().getLog().addLog("" + getJogo().getSpaceShip());
        getJogo().getLog().printLogs();
        getJogo().getLog().clearLog();

        return new WaitPlanetSector(getJogo());

    }
    
    @Override
    public Estado alienAttack(){
        
        int diceRoll = 0;
        int turn = 1;
        int newPosX = 0, newPosY = 0;
        int newAlienType = 0;
        do{
            //Ataque do drone!
            getJogo().getLog().addLog("Turno: " + turn);
            getJogo().getDice().rollDice();
            diceRoll = getJogo().getDiceRoll();
            getJogo().getLog().addLog("Resultado do lançamento do Drone: " + diceRoll);
            
            switch (getJogo().getPlaneta().getTerreno().getAlienAttacker().getTipo()) {
                case "Preto":
                    if(diceRoll >= 5){
                        getJogo().getPlaneta().getTerreno().setEntityReset(getJogo().getPlaneta().getTerreno().getAlienAttacker().getPosX(), getJogo().getPlaneta().getTerreno().getAlienAttacker().getPosY());
                        
                        newAlienType = (int)(Math.random() * 4) + 1;
                        Alien a = FabricaAliens.criaAlien(newAlienType);
                        getJogo().getPlaneta().getTerreno().setAlienAttacker(a);
                        
                        do{
                            newPosX = (int)(Math.random() * 6) + 1;
                            newPosY = (int)(Math.random() * 6) + 1;
                            
                        }while(getJogo().getPlaneta().getTerreno().getEntityInPosition(newPosX, newPosY) != null);
                        getJogo().getPlaneta().getTerreno().putEntity(a, newPosX, newPosY);
                        getJogo().setFightOver(true);
                        getJogo().getLog().addLog("Alien morreu, outro a nascer!");
                        
                        return new WaitLanding(getJogo());
                        
                    }
                    break;
                    
                case "Verde":
                    if(diceRoll >= 4){
                        getJogo().getPlaneta().getTerreno().setEntityReset(getJogo().getPlaneta().getTerreno().getAlienAttacker().getPosX(), getJogo().getPlaneta().getTerreno().getAlienAttacker().getPosY());
                        
                        newAlienType = (int)(Math.random() * 4) + 1;
                        Alien a = FabricaAliens.criaAlien(newAlienType);
                        getJogo().getPlaneta().getTerreno().setAlienAttacker(a);
                         
                        do{
                            newPosX = (int)(Math.random() * 6) + 1;
                            newPosY = (int)(Math.random() * 6) + 1;
                            
                        }while(getJogo().getPlaneta().getTerreno().getEntityInPosition(newPosX, newPosY) != null);
                        getJogo().getPlaneta().getTerreno().putEntity(a, newPosX, newPosY);
                        getJogo().setFightOver(true);
                        getJogo().getLog().addLog("Alien morreu, outro a nascer!");
                        
                        return new WaitLanding(getJogo());
                        
                    }
                    break;
                    
                case "Azul":
                    if(diceRoll == 3 || diceRoll == 4 || diceRoll == 5){
                        getJogo().getPlaneta().getTerreno().setEntityReset(getJogo().getPlaneta().getTerreno().getAlienAttacker().getPosX(), getJogo().getPlaneta().getTerreno().getAlienAttacker().getPosY());
                        
                        newAlienType = (int)(Math.random() * 4) + 1;
                        Alien a = FabricaAliens.criaAlien(newAlienType);
                        getJogo().getPlaneta().getTerreno().setAlienAttacker(a);
                        
                        do{
                            newPosX = (int)(Math.random() * 6) + 1;
                            newPosY = (int)(Math.random() * 6) + 1;
                            
                        }while(getJogo().getPlaneta().getTerreno().getEntityInPosition(newPosX, newPosY) != null);
                        getJogo().getPlaneta().getTerreno().putEntity(a, newPosX, newPosY);
                        getJogo().setFightOver(true);
                        getJogo().getLog().addLog("Alien morreu, outro a nascer!");
                        
                        return new WaitLanding(getJogo());
                        
                    }
                    break;
                    
                case "Vermelho":
                    if(diceRoll <= 2){
                        getJogo().getPlaneta().getTerreno().setEntityReset(getJogo().getPlaneta().getTerreno().getAlienAttacker().getPosX(), getJogo().getPlaneta().getTerreno().getAlienAttacker().getPosY());
                        
                        newAlienType = (int)(Math.random() * 4) + 1;
                        Alien a = FabricaAliens.criaAlien(newAlienType);
                        getJogo().getPlaneta().getTerreno().setAlienAttacker(a);
                        
                        do{
                            newPosX = (int)(Math.random() * 6) + 1;
                            newPosY = (int)(Math.random() * 6) + 1;
                            
                        }while(getJogo().getPlaneta().getTerreno().getEntityInPosition(newPosX, newPosY) != null);
                        getJogo().getPlaneta().getTerreno().putEntity(a, newPosX, newPosY);
                        getJogo().setFightOver(true);
                        getJogo().getLog().addLog("Alien morreu, outro a nascer!");
                        
                        return new WaitLanding(getJogo());
                        
                    }
                    break;
                    
                default:
                    break;
            }
            
            //Ataque do Alien
            getJogo().getDice().rollDice();
            diceRoll = getJogo().getDiceRoll();
            getJogo().getLog().addLog("Resultado do lançamento do Alien: " + diceRoll);
            
            switch (getJogo().getPlaneta().getTerreno().getAlienAttacker().getTipo()) {
                case "Preto":
                    if(diceRoll == 1){
                        getJogo().getPlaneta().getTerreno().getDrone().subVida();
                        if(getJogo().getPlaneta().getTerreno().getDrone().getVida() == 0){
                            getJogo().getPlaneta().getTerreno().setEntityReset(getJogo().getPlaneta().getTerreno().getDrone().getPosX(), getJogo().getPlaneta().getTerreno().getDrone().getPosY());
                            getJogo().getPlaneta().getTerreno().setDrone(null);
                            getJogo().getSpaceShip().setDrone(null);
                        }
                        
                        return new WaitPlanetSector(getJogo());
                    }
 
                    break;
                    
                case "Verde":
                    if(diceRoll <= 2){
                        getJogo().getPlaneta().getTerreno().getDrone().subVida();
                        if(getJogo().getPlaneta().getTerreno().getDrone().getVida() == 0){
                            getJogo().getPlaneta().getTerreno().setEntityReset(getJogo().getPlaneta().getTerreno().getDrone().getPosX(), getJogo().getPlaneta().getTerreno().getDrone().getPosY());
                            getJogo().getPlaneta().getTerreno().setDrone(null);
                            getJogo().getSpaceShip().setDrone(null);
                        }
                        
                        return new WaitPlanetSector(getJogo());
                    }
                    
                    break;
                    
                case "Azul":
                    if(diceRoll == 3 || diceRoll == 4 || diceRoll == 5){
                        getJogo().getPlaneta().getTerreno().getDrone().subVida();
                        if(getJogo().getPlaneta().getTerreno().getDrone().getVida() == 0){
                            getJogo().getPlaneta().getTerreno().setEntityReset(getJogo().getPlaneta().getTerreno().getDrone().getPosX(), getJogo().getPlaneta().getTerreno().getDrone().getPosY());
                            getJogo().getPlaneta().getTerreno().setDrone(null);
                            getJogo().getSpaceShip().setDrone(null);
                        }
                        
                        return new WaitPlanetSector(getJogo());
                    }
                    
                    break;
                    
                case "Vermelho":
                    if(diceRoll >= 5){
                        getJogo().getPlaneta().getTerreno().getDrone().subVida();
                        if(getJogo().getPlaneta().getTerreno().getDrone().getVida() == 0){
                            getJogo().getPlaneta().getTerreno().setEntityReset(getJogo().getPlaneta().getTerreno().getDrone().getPosX(), getJogo().getPlaneta().getTerreno().getDrone().getPosY());
                            getJogo().getPlaneta().getTerreno().setDrone(null);
                            getJogo().getSpaceShip().setDrone(null);
                        }
                        
                        return new WaitPlanetSector(getJogo());
                    }
                    
                    break;
                    
                default:
                    break;
            }
            
            turn++;
            getJogo().getLog().printLogs();
            getJogo().getLog().clearLog();
            
        }while(!(getJogo().getFightOver()));
        
        return new WaitAlienAttack(getJogo());
    }

}
