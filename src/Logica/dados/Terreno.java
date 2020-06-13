package Logica.dados;

import Logica.dados.variations.AlienAzul;
import Logica.dados.variations.ResourceAzul;
import java.io.Serializable;

public class Terreno extends Celula implements Serializable{

    private Object[][] terreno;
    private Drone drone;
    private Resource resource;
    private Alien alienAttacker;
    private Dado dado;

    public Terreno() {    
        this.terreno = new Object[6][6];
        this.drone = new Drone();
        this.resource = new ResourceAzul();
        this.alienAttacker = new AlienAzul();
        this.dado = new Dado();
        
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource res) {
        this.resource = res;
    }

    public Alien getAlienAttacker() {
        return alienAttacker;
    }

    public void setAlienAttacker(Alien alien) {
        this.alienAttacker = alien;
    }
    
    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
    
    public Object[][] getTerreno(){
        return terreno;
    }
    
    public void clearBoard(){
        this.terreno = new Object[6][6];
    }
        
    public Object getEntity(Object o){
        
        for(int i = 0; i < terreno.length; i++){
            for(int j = 0; j < terreno[i].length; j++){
                if(terreno[i][j] == o)
                    return terreno[i][j];
            }
        }     
        return null;
    }
    
    public Object getEntityInPosition(int x, int y) {
        for (int i = 0; i < terreno.length; i++) {
            for (int j = 0; j < terreno[i].length; j++) {
                if(i == x && j == y)
                    if (terreno[i][j] != null)
                        return terreno[i][j];
            }
        }
        return null;
    }
    
    public boolean setEntityReset(int x, int y){
        
        for(int i = 0; i < terreno.length; i++){
           for(int j = 0; j < terreno[i].length; j++){
               if(i == x && j == y){
                   terreno[i][j] = null;
                   
                   return true;
               }
           }
       }
        
       return false;
    }
    
    public void checkCollision(Alien a, Resource r, Drone d){
        boolean canGo = false;

        dado.rollDice();
        int r1 = dado.getValue();
        dado.rollDice();
        int r2 = dado.getValue();
        
        dado.rollDice();
        int r3 = dado.getValue();
        dado.rollDice();
        int r4 = dado.getValue();
        
        dado.rollDice();
        int r5 = dado.getValue();
        dado.rollDice();
        int r6 = dado.getValue();
        
        boolean b1 = putEntity(a, r1, r2);
        boolean b2 = putEntity(r, r1, r2);
        boolean b3 = putEntity(d, r1, r2);
        
        do {
            
            if (b1 == false) {
                setEntityReset(r1, r2);
                
                dado.rollDice();
                r1 = dado.getValue();
                dado.rollDice();
                r2 = dado.getValue();
                
                b1 = putEntity(a, r1, r2);

            } else if (b2 == false) {
                setEntityReset(r3, r4);
                
                dado.rollDice();
                r3 = dado.getValue();
                dado.rollDice();
                r4 = dado.getValue();
                
                b2 = putEntity(r, r3, r4);

            } else if (b3 == false) {
                setEntityReset(r5, r6);
                
                dado.rollDice();
                r5 = dado.getValue();
                dado.rollDice();
                r6 = dado.getValue();
        
                b3 = putEntity(d, r5, r6);

            } else {
                canGo = true;
                
            }
        } while (!canGo);
        
    }
    
    public void moveEntities(int moveXChoice, int moveYChoice) {

        moveDrone(moveXChoice, moveYChoice);
        moveAlien();

    }
        
    public int[] checkBestCourse() {

        int ans[] = new int[2];        
        int rand = (int) (Math.random() * 10) + 1;

        if (alienAttacker.getPosX() == 0 && alienAttacker.getPosY() == 0) { //Nao pode ir para cima ou esquerda, vamos tirar essa hipotese

            if (Math.abs((alienAttacker.getPosX() + 1) - drone.getPosX()) < (Math.abs(alienAttacker.getPosX() - drone.getPosX()))) { //O drone para a Direita
                ans[0] = alienAttacker.getPosX() + 1;
                ans[1] = alienAttacker.getPosY();

            } else if ((Math.abs(alienAttacker.getPosY() + 1) - drone.getPosY()) < (Math.abs(alienAttacker.getPosY() - drone.getPosY()))) { //O drone está para Baixo
                ans[0] = alienAttacker.getPosX();
                ans[1] = alienAttacker.getPosY() + 1;

            } else {
                if (rand < 5) {
                    ans[0] = alienAttacker.getPosX() + 1;
                    ans[1] = alienAttacker.getPosY();

                } else {
                    ans[0] = alienAttacker.getPosX();
                    ans[1] = alienAttacker.getPosY() + 1;

                }
            }

        } else if (alienAttacker.getPosX() == 5 && alienAttacker.getPosY() == 0) { //Nao pode ir para cima ou para a direita!

            if (Math.abs((alienAttacker.getPosX() - 1) - drone.getPosX()) < (Math.abs(alienAttacker.getPosX() - drone.getPosX()))) { //O drone para a Esquerda
                ans[0] = alienAttacker.getPosX() - 1;
                ans[1] = alienAttacker.getPosY();

            } else if ((Math.abs(alienAttacker.getPosY() + 1) - drone.getPosY()) < (Math.abs(alienAttacker.getPosY() - drone.getPosY()))) { //O drone está para Baixo
                ans[0] = alienAttacker.getPosX();
                ans[1] = alienAttacker.getPosY() + 1;

            } else {
                if (rand < 5) {
                    ans[0] = alienAttacker.getPosX() - 1;
                    ans[1] = alienAttacker.getPosY();

                } else {
                    ans[0] = alienAttacker.getPosX();
                    ans[1] = alienAttacker.getPosY() + 1;

                }
            }

        } else if (alienAttacker.getPosX() == 5 && alienAttacker.getPosY() == 5) { //Nao pode ir para baixo ou para a direita!

            if (Math.abs((alienAttacker.getPosX() - 1) - drone.getPosX()) < (Math.abs(alienAttacker.getPosX() - drone.getPosX()))) { //O drone para a Esquerda
                ans[0] = alienAttacker.getPosX() - 1;
                ans[1] = alienAttacker.getPosY();

            } else if ((Math.abs(alienAttacker.getPosY() - 1) - drone.getPosY()) < (Math.abs(alienAttacker.getPosY() - drone.getPosY()))) { //O drone está para Cima
                ans[0] = alienAttacker.getPosX();
                ans[1] = alienAttacker.getPosY() - 1;

            } else {
                if (rand < 5) {
                    ans[0] = alienAttacker.getPosX() - 1;
                    ans[1] = alienAttacker.getPosY();

                } else {
                    ans[0] = alienAttacker.getPosX();
                    ans[1] = alienAttacker.getPosY() - 1;

                }
            }

        } else if (alienAttacker.getPosX() == 0 && alienAttacker.getPosY() == 5) { //Nao pode ir para baixo ou para a esquerda!

            if (Math.abs((alienAttacker.getPosX() + 1) - drone.getPosX()) < (Math.abs(alienAttacker.getPosX() - drone.getPosX()))) { //O drone para a Direita
                ans[0] = alienAttacker.getPosX() + 1;
                ans[1] = alienAttacker.getPosY();

            } else if ((Math.abs(alienAttacker.getPosY() - 1) - drone.getPosY()) < (Math.abs(alienAttacker.getPosY() - drone.getPosY()))) { //O drone está para Cima
                ans[0] = alienAttacker.getPosX();
                ans[1] = alienAttacker.getPosY() - 1;

            } else {
                if (rand < 5) {
                    ans[0] = alienAttacker.getPosX() + 1;
                    ans[1] = alienAttacker.getPosY();

                } else {
                    ans[0] = alienAttacker.getPosX();
                    ans[1] = alienAttacker.getPosY() - 1;

                }
            }

        } else if (alienAttacker.getPosX() == 0) { // Coluna da Esquerda

            if (Math.abs((alienAttacker.getPosX() + 1) - drone.getPosX()) < Math.abs(alienAttacker.getPosX() - drone.getPosX())) { //O drone esta para a direita! 
                ans[0] = alienAttacker.getPosX() + 1;
                ans[1] = alienAttacker.getPosY();

            } else {

                if (Math.abs((alienAttacker.getPosY() - 1) - drone.getPosY()) < (Math.abs((alienAttacker.getPosY() + 1) - drone.getPosY()))) { //Drone está para cima
                    ans[0] = alienAttacker.getPosX();
                    ans[1] = alienAttacker.getPosY() - 1;

                } else { //Vai para Baixo
                    ans[0] = alienAttacker.getPosX();
                    ans[1] = alienAttacker.getPosY() + 1;

                }
            }

        } else if (alienAttacker.getPosY() == 0) {

            if (Math.abs((alienAttacker.getPosY() + 1) - drone.getPosY()) < Math.abs(alienAttacker.getPosY() - drone.getPosY())) { //O drone esta para Baixo! 
                ans[0] = alienAttacker.getPosX();
                ans[1] = alienAttacker.getPosY() + 1;

            } else {

                if (Math.abs((alienAttacker.getPosX() - 1) - drone.getPosX()) < (Math.abs((alienAttacker.getPosX() + 1) - drone.getPosX()))) { //Drone está para a Esquerda
                    ans[0] = alienAttacker.getPosX() - 1;
                    ans[1] = alienAttacker.getPosY();

                } else { //Vai para Direita
                    ans[0] = alienAttacker.getPosX() + 1;
                    ans[1] = alienAttacker.getPosY();

                }
            }

        } else if (alienAttacker.getPosX() == 5) {

            if (Math.abs((alienAttacker.getPosX() - 1) - drone.getPosX()) < Math.abs(alienAttacker.getPosX() - drone.getPosX())) { //O drone esta para a Esquerda! 
                ans[0] = alienAttacker.getPosX() - 1;
                ans[1] = alienAttacker.getPosY();

            } else {

                if (Math.abs((alienAttacker.getPosY() - 1) - drone.getPosY()) < (Math.abs((alienAttacker.getPosY() + 1) - drone.getPosY()))) { //Drone está para cima
                    ans[0] = alienAttacker.getPosX();
                    ans[1] = alienAttacker.getPosY() - 1;

                } else { //Vai para Baixo
                    ans[0] = alienAttacker.getPosX();
                    ans[1] = alienAttacker.getPosY() + 1;

                }
            }

        } else if (alienAttacker.getPosY() == 5) {

            if (Math.abs((alienAttacker.getPosY() - 1) - drone.getPosY()) < Math.abs(alienAttacker.getPosY() - drone.getPosY())) { //O drone esta para Cima! 
                ans[0] = alienAttacker.getPosX();
                ans[1] = alienAttacker.getPosY() - 1;

            } else {

                if (Math.abs((alienAttacker.getPosX() - 1) - drone.getPosX()) < (Math.abs((alienAttacker.getPosX() + 1) - drone.getPosX()))) { //Drone está para a Esquerda
                    ans[0] = alienAttacker.getPosX() - 1;
                    ans[1] = alienAttacker.getPosY();

                } else { //Vai para Direita
                    ans[0] = alienAttacker.getPosX() + 1;
                    ans[1] = alienAttacker.getPosY();

                }
            }
            //Senao esta em nenhum dos cantos nem nos limites, entao esta no meio!    
        } else {

            if (alienAttacker.getPosX() == drone.getPosX()) { //Estao na mesma coluna
                if (alienAttacker.getPosY() < drone.getPosY()) { //O drone esta para Baixo
                    ans[0] = alienAttacker.getPosX();
                    ans[1] = alienAttacker.getPosY() + 1;

                } else { //O drone esta para cima
                    ans[0] = alienAttacker.getPosX();
                    ans[1] = alienAttacker.getPosY() - 1;

                }

            } else if (alienAttacker.getPosY() == drone.getPosY()) { //Estao na mesma Linha
                if (alienAttacker.getPosX() < drone.getPosX()) { //O drone esta para a Direita
                    ans[0] = alienAttacker.getPosX() + 1;
                    ans[1] = alienAttacker.getPosY();

                } else { //O drone esta para a Esquerda
                    ans[0] = alienAttacker.getPosX() - 1;
                    ans[1] = alienAttacker.getPosY();

                }

            } else {
                if (alienAttacker.getPosX() < drone.getPosX()) { //O drone está para a Direita

                    if (alienAttacker.getPosY() < drone.getPosY()) { //O drone esta para Baixo
                        if (rand < 5) { //Vai para a Direita
                            ans[0] = alienAttacker.getPosX() + 1;
                            ans[1] = alienAttacker.getPosY();

                        } else { //Vai para Baixo
                            ans[0] = alienAttacker.getPosX();
                            ans[1] = alienAttacker.getPosY() + 1;

                        }

                    } else { //O drone esta para Cima
                        if (rand < 5) { //Vai para a Direita
                            ans[0] = alienAttacker.getPosX() + 1;
                            ans[1] = alienAttacker.getPosY();

                        } else { //Vai para Cima
                            ans[0] = alienAttacker.getPosX();
                            ans[1] = alienAttacker.getPosY() - 1;

                        }
                    }

                } else { //O drone esta para a Esquerda
                    if (alienAttacker.getPosY() < drone.getPosY()) { //O drone esta para Baixo
                        if (rand < 5) { //Vai para a Esquerda
                            ans[0] = alienAttacker.getPosX() - 1;
                            ans[1] = alienAttacker.getPosY();

                        } else { //Vai para Baixo
                            ans[0] = alienAttacker.getPosX();
                            ans[1] = alienAttacker.getPosY() + 1;

                        }

                    } else { //O drone esta para Cima
                        if (rand < 5) { //Vai para a Esquerda
                            ans[0] = alienAttacker.getPosX() - 1;
                            ans[1] = alienAttacker.getPosY();

                        } else { //Vai para Cima
                            ans[0] = alienAttacker.getPosX();
                            ans[1] = alienAttacker.getPosY() - 1;

                        }
                    }

                }

            }

        }
        
        return ans;

    }

    public void moveAlien() {
        
        int coords[] = new int [2];

        coords = checkBestCourse();
        
        if(coords[0] == resource.getPosX() && coords[1] == resource.getPosY()){
            //do nothing
        }else if(coords[0] == drone.getPosX() && coords[1] == drone.getPosY()){
            //do nothing
        }else if(coords[0] == resource.getPosX() && coords[1] == resource.getPosY()){
            //do nothing       
            
        }else{
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            alienAttacker.setPosX(coords[0]);
            alienAttacker.setPosY(coords[1]);
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());
        }

        if(alienAttacker.getPosX() < 0  && alienAttacker.getPosY() == 0){ //Canto superior Esquerdo ( direçao: <- )
            alienAttacker.setPosX(0);
            alienAttacker.setPosY(0);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());
            
        }else if(alienAttacker.getPosX() == 0 && alienAttacker.getPosY() < 0){ //Canto Superior Esquerdo ( direçao: ^ )
            alienAttacker.setPosX(0);
            alienAttacker.setPosY(0);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());
            
        }else if(alienAttacker.getPosX() > 5  && alienAttacker.getPosY() == 0){ //Canto superior Direito ( direçao: -> )
            alienAttacker.setPosX(5);
            alienAttacker.setPosY(0);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());
            
        }else if(alienAttacker.getPosX() == 5 && alienAttacker.getPosY() < 0){ //Canto Superior Direito ( direçao: ^ )
            alienAttacker.setPosX(5);
            alienAttacker.setPosY(0);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());
            
        }else if(alienAttacker.getPosX() > 5  && alienAttacker.getPosY() == 5){ //Canto Inferior Direito ( direçao: -> )
            alienAttacker.setPosX(5);
            alienAttacker.setPosY(5);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());
            
        }else if(alienAttacker.getPosX() == 5 && alienAttacker.getPosY() > 5){ //Canto Inferior Direito ( direçao: v )
            alienAttacker.setPosX(5);
            alienAttacker.setPosY(5);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());
            
        }else if(alienAttacker.getPosX() < 0  && alienAttacker.getPosY() == 5){ //Canto Inferior Esquerdo ( direçao: <- )
            alienAttacker.setPosX(0);
            alienAttacker.setPosY(5);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());
            
        }else if(alienAttacker.getPosX() == 0 && alienAttacker.getPosY() > 5){ //Canto Inferior Esquerdo ( direçao: v )
            alienAttacker.setPosX(0);
            alienAttacker.setPosY(5);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());
            
        }else if(alienAttacker.getPosX() < 0){
            alienAttacker.setPosX(0);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());
            
        }else if(alienAttacker.getPosX() > getTerreno().length - 1){
            alienAttacker.setPosX(5);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());
            
        }else if (alienAttacker.getPosY() < 0) {
            alienAttacker.setPosY(0);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());

        } else if (alienAttacker.getPosY() > getTerreno().length - 1) {
            alienAttacker.setPosY(5);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());

        }

    }

    public void moveDrone(int moveX, int moveY) {

        //Para ir ter com o recurso
        int resX = resource.getPosX();
        int resY = resource.getPosY();
        
        setEntityReset(drone.getPosX(), drone.getPosY());
        drone.setPosX(drone.getPosX() + moveX);
        drone.setPosY(drone.getPosY() + moveY);
        putEntity(drone, drone.getPosX(), drone.getPosY());

        if(drone.getPosX() <= 0  && drone.getPosY() == 0){ //Canto superior Esquerdo ( direçao: <- )
            drone.setPosX(0);
            drone.setPosY(0);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());
            
        }else if(drone.getPosX() == 0 && drone.getPosY() <= 0){ //Canto Superior Esquerdo ( direçao: ^ )
            drone.setPosX(0);
            drone.setPosY(0);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());
            
        }else if(drone.getPosX() >= 5  && drone.getPosY() == 0){ //Canto superior Direito ( direçao: -> )
            drone.setPosX(5);
            drone.setPosY(0);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());
            
        }else if(drone.getPosX() == 5 && drone.getPosY() <= 0){ //Canto Superior Direito ( direçao: ^ )
            drone.setPosX(5);
            drone.setPosY(0);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());
            
        }else if(drone.getPosX() >= 5  && drone.getPosY() == 5){ //Canto Inferior Direito ( direçao: -> )
            drone.setPosX(5);
            drone.setPosY(5);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());
            
        }else if(drone.getPosX() == 5 && drone.getPosY() >= 5){ //Canto Inferior Direito ( direçao: v )
            drone.setPosX(5);
            drone.setPosY(5);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());
            
        }else if(drone.getPosX() <= 0  && drone.getPosY() == 5){ //Canto Inferior Esquerdo ( direçao: <- )
            drone.setPosX(0);
            drone.setPosY(5);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());
            
        }else if(drone.getPosX() == 0 && drone.getPosY() >= 5){ //Canto Inferior Esquerdo ( direçao: v )
            drone.setPosX(0);
            drone.setPosY(5);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());
            
        }else if(drone.getPosX() <= 0){
            drone.setPosX(0);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());
            
        }else if(drone.getPosX() > getTerreno().length - 1){
            drone.setPosX(5);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());
            
        }else if (drone.getPosY() <= 0) {
            drone.setPosY(0);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());

        } else if (drone.getPosY() > getTerreno().length - 1) {
            drone.setPosY(5);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());

        }
        
        if(drone.getPosX() == resX && drone.getPosY() == resY){ //Estou em cima do Recurso
            drone.setRes(resource);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());
            
        }

    }
    
    public boolean checkFight() {
        //Verificaçao se o drone está ao lado do alien

        if (drone.getPosX() >= getTerreno().length - 1) { //Verificar se esta no lado direito

            if (drone.getPosX() == 5 && drone.getPosY() == 0) { //Canto superior direito

                if (terreno[drone.getPosX() - 1][drone.getPosY()] == alienAttacker) { //Verifica à esquerda
                    return true;

                } else return terreno[drone.getPosX()][drone.getPosY() + 1] == alienAttacker; //Verifica abaixo              
                
            } else if (drone.getPosX() == 5 && drone.getPosY() == 5) { //Canto Inferior Direito

                if (terreno[drone.getPosX()][drone.getPosY() - 1] == alienAttacker) { //Verifica acima
                    return true;

                } else return terreno[drone.getPosX() - 1][drone.getPosY()] == alienAttacker; //Verifica à esquerda     
                
            } else { //Resto da coluna 6
                
                if (terreno[drone.getPosX()][drone.getPosY() - 1] == alienAttacker) { //Verifica acima
                    return true;

                } else if (terreno[drone.getPosX() - 1][drone.getPosY()] == alienAttacker) { //Verifica à esquerda
                    return true;

                } else return terreno[drone.getPosX()][drone.getPosY() + 1] == alienAttacker; //Verifica abaixo

            }

        } else if (drone.getPosY() >= getTerreno().length - 1) { //Verificar se esta abaixo

            if (drone.getPosX() == 5 && drone.getPosY() == 5) { //Canto Inferior Direito

                if (terreno[drone.getPosX()][drone.getPosY() - 1] == alienAttacker) { //Verifica acima
                    return true;

                } else return terreno[drone.getPosX() - 1][drone.getPosY()] == alienAttacker; //Verifica à esquerda
    
            } else if (drone.getPosX() == 0 && drone.getPosY() == 5) { //Canto Inferior Esquerdo

                if (terreno[drone.getPosX()][drone.getPosY() - 1] == alienAttacker) { //Verifica acima
                    return true;

                } else return terreno[drone.getPosX() + 1][drone.getPosY()] == alienAttacker; //Verifica à direita

            } else { //Resto da linha 6

                if (terreno[drone.getPosX()][drone.getPosY() - 1] == alienAttacker) { //Verifica acima
                    return true;

                } else if (terreno[drone.getPosX() + 1][drone.getPosY()] == alienAttacker) { //Verifica à direita
                    return true;

                } else return terreno[drone.getPosX() - 1][drone.getPosY()] == alienAttacker; //Verifica à esquerda
  
            }

        } else if (drone.getPosX() == 0) { //Verificar se esta no lado esquerdo

            if (drone.getPosX() == 0 && drone.getPosY() == 5) { //Canto Inferior Esquerdo

                if (terreno[drone.getPosX()][drone.getPosY() - 1] == alienAttacker) { //Verifica acima
                    return true;

                } else return terreno[drone.getPosX() + 1][drone.getPosY()] == alienAttacker; //Verifica à direita
  
            } else if (drone.getPosX() == 0 && drone.getPosY() == 0) { //Canto Superior Esquerdo

                if (terreno[drone.getPosX()][drone.getPosY() + 1] == alienAttacker) { //Verifica abaixo
                    return true;

                } else return terreno[drone.getPosX() + 1][drone.getPosY()] == alienAttacker; //Verifica à direita
   
            } else { //Resto da coluna 1

                if (terreno[drone.getPosX()][drone.getPosY() - 1] == alienAttacker) { //Verifica acima
                    return true;

                } else if (terreno[drone.getPosX()][drone.getPosY() + 1] == alienAttacker) { //Verifica abaixo
                    return true;

                } else return terreno[drone.getPosX() + 1][drone.getPosY()] == alienAttacker; //Verifica à direita  
            }

        } else if (drone.getPosY() == 0) { //Verificar se esta no topo

            if (drone.getPosX() == 0 && drone.getPosY() == 0) { //Canto Superior Esquerdo

                if (terreno[drone.getPosX()][drone.getPosY() + 1] == alienAttacker) { //Verifica abaixo
                    return true;

                } else return terreno[drone.getPosX() + 1][drone.getPosY()] == alienAttacker; //Verifica à direita
   
            } else if (drone.getPosX() == 5 && drone.getPosY() == 0) { //Canto superior direito

                if (terreno[drone.getPosX() - 1][drone.getPosY()] == alienAttacker) { //Verifica à esquerda
                    return true;

                } else return terreno[drone.getPosX()][drone.getPosY() + 1] == alienAttacker; //Verifica abaixo
  
            } else { //Resto da linha 1

                if (terreno[drone.getPosX() - 1][drone.getPosY()] == alienAttacker) { //Verifica à esquerda
                    return true;

                } else if (terreno[drone.getPosX()][drone.getPosY() + 1] == alienAttacker) { //Verifica abaixo
                    return true;

                } else return terreno[drone.getPosX() + 1][drone.getPosY()] == alienAttacker; //Verifica à direita   
            }
            
        }else{
            if (terreno[drone.getPosX()][drone.getPosY() + 1] == alienAttacker) { //Verifica abaixo
                return true;
                    
            }else if(terreno[drone.getPosX() + 1][drone.getPosY()] == alienAttacker){ //Verifica à direita
                return true;
                
            }else if (terreno[drone.getPosX()][drone.getPosY() - 1] == alienAttacker) { //Verifica acima
                return true;
                    
            }else return terreno[drone.getPosX() - 1][drone.getPosY()] == alienAttacker; //Verifica à esquerda         
        }
    }
    
    @Override
    public boolean putEntity(Alien a, int x, int y){
       
       for(int i = 0; i < terreno.length; i++){
           for(int j = 0; j < terreno[i].length; j++){
               if(i == x && j == y){
                   if (terreno[i][j] == null) {
                       terreno[i][j] = a;
                       a.setPosX(i);
                       a.setPosY(j);

                       return true;
                   }
                   return false;
               }
           }
       }
       
       return false;
    }

    @Override
    public boolean putEntity(Resource r, int x, int y) {
        
        for(int i = 0; i < terreno.length; i++){
           for(int j = 0; j < terreno[i].length; j++){
               if(i == x && j == y){
                   if (terreno[i][j] == null) {
                       terreno[i][j] = r;
                       r.setPosX(i);
                       r.setPosY(j);

                       return true;
                   }
                   return false;
               }
           }
       }
        
       return false;
    }

    @Override
    public boolean putEntity(Drone d, int x, int y) {
        
        for(int i = 0; i < terreno.length; i++){
           for(int j = 0; j < terreno[i].length; j++){
               if(i == x && j == y){
                   if (terreno[i][j] == null) {
                       terreno[i][j] = d;
                       d.setPosX(i);
                       d.setPosY(j);

                       return true;
                   }
                   return false;
               }
           }
       } 
        
       return false;
    }

    @Override
    public String toString() {
        
        String s = "Terreno \n";
        
        s += "-- Alien attacker -- \t-- Drone -- \t-- Recurso -- \n" + "Tipo: " + alienAttacker.getTipo() + "\t\tRecurso: " + drone.getRes() + "\tTipo: " + resource.getTipo() +
                "\nposX: " + alienAttacker.getPosX() + "\t\t\tposX: " + drone.getPosX() + "\t\tposX: " + resource.getPosX() +
                "\nposY: " + alienAttacker.getPosY() + "\t\t\tposY: " + drone.getPosY() + "\t\tposY: " + resource.getPosY() +
                "\nVida: " + alienAttacker.getVida() + "\t\t\tVida: " + + drone.getVida() + "\n\n";
        
        for(int i = 0; i < terreno.length; i++){
            for(int j = 0; j < terreno[i].length ; j++){
                if(terreno[j][i] == null ){
                    s += " - ";
                }else
                    s += " " + terreno[j][i] + " ";
                
            }
            s += "\n";
        }
        
        return s;
    }
    
    
    
}
