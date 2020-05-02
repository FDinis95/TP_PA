package Logica.dados;

public class Terreno extends Celula {

    private Object[][] terreno;
    private Drone drone;
    private Resource resource;
    private Alien alienAttacker;
    private Dado dado;

    public Terreno() {    
        this.terreno = new Object[7][7];
        this.drone = null;
        this.resource = null;
        this.alienAttacker = null;
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
        
    public Object getEntity(Object o){
        
        for(int i = 1; i < terreno.length; i++){
            for(int j = 1; j < terreno[i].length; j++){
                if(terreno[i][j] == o)
                    return terreno[i][j];
            }
        }     
        return null;
    }
    
    public Object getEntityInPosition(int x, int y) {
        for (int i = 1; i < terreno.length; i++) {
            for (int j = 1; j < terreno[i].length; j++) {
                if(i == x && j == y)
                    if (terreno[i][j] != null)
                        return terreno[i][j];
            }
        }
        return null;
    }
    
    public boolean setEntityReset(int x, int y){
        
        for(int i = 1; i < terreno.length; i++){
           for(int j = 1; j < terreno[i].length; j++){
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
        int droneCoords = drone.getPosX() + drone.getPosY();
        int vaiEsquerda = Math.abs((alienAttacker.getPosX() + (alienAttacker.getPosY() - 1)) - droneCoords);
        int vaiDireita =  Math.abs((alienAttacker.getPosX() + (alienAttacker.getPosY() + 1)) - droneCoords);
        int vaiBaixo = Math.abs(((alienAttacker.getPosX() + 1) + alienAttacker.getPosY()) - droneCoords);
        int vaiCima = Math.abs(((alienAttacker.getPosX() - 1) + alienAttacker.getPosY()) - droneCoords);

        int rand = (int) (Math.random() * 10) + 1;

        if (alienAttacker.getPosX() == 1) { //Nao pode ir para cima, vamos tirar essa hipotese

            if ((vaiEsquerda + vaiBaixo) > (vaiDireita + vaiBaixo)) {

                //Nao interessa para onde vai (Lado esquerdo ou baixo, as coords sao iguais)
                if (rand < 5) { // Vai para baixo
                    ans[0] = alienAttacker.getPosX() + 1;
                    ans[1] = alienAttacker.getPosY();

                } else { // Senao vai para a esquerda
                    ans[0] = alienAttacker.getPosX();
                    ans[1] = alienAttacker.getPosY() - 1;

                }

            } else { //Se direita + baixo for menor então faz isto

                if (rand < 5) { // Vai para baixo
                    ans[0] = alienAttacker.getPosX() + 1;
                    ans[1] = alienAttacker.getPosY();

                } else { // Senao vai para a direita
                    ans[0] = alienAttacker.getPosX();
                    ans[1] = alienAttacker.getPosY() + 1;

                }
            }

        } else if (alienAttacker.getPosX() == 6) { //Nao pode ir para baixo, vamos tirar essa hipotese

            if ((vaiEsquerda + vaiCima) > (vaiDireita + vaiCima)) {

                //Nao interessa para onde vai (Lado esquerdo ou cima, as coords sao iguais)
                if (rand < 5) { // Vai para cima
                    ans[0] = alienAttacker.getPosX() - 1;
                    ans[1] = alienAttacker.getPosY();

                } else { // Senao vai para a esquerda
                    ans[0] = alienAttacker.getPosX();
                    ans[1] = alienAttacker.getPosY() - 1;

                }

            } else { //Se direita + cima for menor então faz isto

                if (rand < 5) { // Vai para cima
                    ans[0] = alienAttacker.getPosX() - 1;
                    ans[1] = alienAttacker.getPosY();

                } else { // Senao vai para a direita
                    ans[0] = alienAttacker.getPosX();
                    ans[1] = alienAttacker.getPosY() + 1;

                }
            }

        } else if (alienAttacker.getPosY() == 1) { //Nao pode ir para a esquerda, vamos tirar essa hipotese

            if ((vaiDireita + vaiCima) > (vaiDireita + vaiBaixo)) {

                //Nao interessa para onde vai (Lado direito ou cima, as coords sao iguais)
                if (rand < 5) { // Vai para cima
                    ans[0] = alienAttacker.getPosX() - 1;
                    ans[1] = alienAttacker.getPosY();

                } else { // Senao vai para a direita
                    ans[0] = alienAttacker.getPosX();
                    ans[1] = alienAttacker.getPosY() + 1;

                }

            } else { //Se direito + baixo for menor então faz isto

                if (rand < 5) { // Vai para baixo
                    ans[0] = alienAttacker.getPosX() + 1;
                    ans[1] = alienAttacker.getPosY();

                } else { // Senao vai para a direita
                    ans[0] = alienAttacker.getPosX();
                    ans[1] = alienAttacker.getPosY() + 1;

                }
            }

        } else if (alienAttacker.getPosY() == 6) { //Nao pode ir para a direita, vamos tirar essa hipotese

            if ((vaiEsquerda + vaiCima) > (vaiEsquerda + vaiBaixo)) {

                //Nao interessa para onde vai (Lado esquerdo ou cima, as coords sao iguais)
                if (rand < 5) { // Vai para cima
                    ans[0] = alienAttacker.getPosX() - 1;
                    ans[1] = alienAttacker.getPosY();

                } else { // Senao vai para a esquerda
                    ans[0] = alienAttacker.getPosX();
                    ans[1] = alienAttacker.getPosY() - 1;

                }

            } else { //Se esquerda + baixo for menor então faz isto

                if (rand < 5) { // Vai para baixo
                    ans[0] = alienAttacker.getPosX() + 1;
                    ans[1] = alienAttacker.getPosY();

                } else { // Senao vai para a esquerda
                    ans[0] = alienAttacker.getPosX();
                    ans[1] = alienAttacker.getPosY() - 1;

                }
            }

            //Se chegar aqui não está em nenhum dos limites do campo, vamos verificar todos os pares com as coords do drone:
            // - ((cima + direita) + (baixo + direita)) -> coords)
            // - ((cima + esquerda) + (baixo + esquerda)) -> coords)
        } else {

            //Isto verifica se  o drone está para cima ou baixo do alien (Apesar de ser extremamente confuso...)
            if (((vaiCima + vaiDireita) + (vaiCima + vaiEsquerda)) < ((vaiBaixo + vaiDireita) + (vaiBaixo + vaiEsquerda))) {
                //Se entrar aqui eu sei que o Drone está acima do alien, portanto vou so comparar qual será o melhor caminho aqui.
                if ((vaiEsquerda + vaiCima) > (vaiDireita + vaiCima)) {

                    //Nao interessa para onde vai (Lado esquerdo ou cima, as coords sao iguais)
                    if (rand < 5) { // Vai para cima
                        ans[0] = alienAttacker.getPosX() - 1;
                        ans[1] = alienAttacker.getPosY();

                    } else { // Senao vai para a esquerda
                        ans[0] = alienAttacker.getPosX();
                        ans[1] = alienAttacker.getPosY() - 1;

                    }

                } else { //Se direita + cima for menor então faz isto

                    if (rand < 5) { // Vai para cima
                        ans[0] = alienAttacker.getPosX() - 1;
                        ans[1] = alienAttacker.getPosY();

                    } else { // Senao vai para a direita
                        ans[0] = alienAttacker.getPosX();
                        ans[1] = alienAttacker.getPosY() + 1;

                    }
                }
                //Se entrar neste else, quer dizer que o drone está abaixo do alien!
            } else {
                if ((vaiEsquerda + vaiBaixo) > (vaiDireita + vaiBaixo)) {

                    //Nao interessa para onde vai (Lado esquerdo ou cima, as coords sao iguais)
                    if (rand < 5) { // Vai para baixo
                        ans[0] = alienAttacker.getPosX() + 1;
                        ans[1] = alienAttacker.getPosY();

                    } else { // Senao vai para a esquerda
                        ans[0] = alienAttacker.getPosX();
                        ans[1] = alienAttacker.getPosY() - 1;

                    }

                } else { //Se esquerda + baixo for menor então faz isto

                    if (rand < 5) { // Vai para baixo
                        ans[0] = alienAttacker.getPosX() + 1;
                        ans[1] = alienAttacker.getPosY();

                    } else { // Senao vai para a direita
                        ans[0] = alienAttacker.getPosX();
                        ans[1] = alienAttacker.getPosY() + 1;

                    }
                }

            }

        }

        return ans;

    }

    public void moveAlien() {
        
        int coords[] = new int [2];
        
        setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
        coords = checkBestCourse();
        if(coords[0] == resource.getPosX() && coords[1] == resource.getPosY()){
            //do nothing
            
        }else{
            alienAttacker.setPosX(coords[0]);
            alienAttacker.setPosY(coords[1]);
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());
        }

        if (alienAttacker.getPosX() < 1 && alienAttacker.getPosY() == 1) { //Canto Superior Esquerdo
            alienAttacker.setPosX(1);
            alienAttacker.setPosY(1);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());

        } else if (alienAttacker.getPosX() == 1 && alienAttacker.getPosY() < 1) { //Canto Superior Esquerdo
            alienAttacker.setPosX(1);
            alienAttacker.setPosY(1);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());

        } else if (alienAttacker.getPosX() < 1 && alienAttacker.getPosY() == 6) { //Canto Superior Direito
            alienAttacker.setPosX(1);
            alienAttacker.setPosY(6);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());

        } else if (alienAttacker.getPosX() == 1 && alienAttacker.getPosY() > 6) { //Canto Superior Direito
            alienAttacker.setPosX(1);
            alienAttacker.setPosY(6);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());

        } else if (alienAttacker.getPosX() > 6 && alienAttacker.getPosY() == 6) { //Canto Inferior Direito
            alienAttacker.setPosX(6);
            alienAttacker.setPosY(6);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());

        } else if (alienAttacker.getPosX() == 6 && alienAttacker.getPosY() > 6) { //Canto Inferior Direito
            alienAttacker.setPosX(6);
            alienAttacker.setPosY(6);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());

        } else if (alienAttacker.getPosX() > 6 && alienAttacker.getPosY() == 1) { //Canto Inferior Esquerdo
            alienAttacker.setPosX(6);
            alienAttacker.setPosY(1);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());

        } else if (alienAttacker.getPosX() == 6 && alienAttacker.getPosY() < 1) { //Canto Inferior Esquerdo
            alienAttacker.setPosX(6);
            alienAttacker.setPosY(1);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());

        } else if (alienAttacker.getPosX() <= 1) {
            alienAttacker.setPosX(1);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());

        } else if (alienAttacker.getPosX() >= getTerreno().length - 1) {
            alienAttacker.setPosX(6);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());

        } else if (alienAttacker.getPosY() <= 1) {
            alienAttacker.setPosY(1);
            setEntityReset(alienAttacker.getPosX(), alienAttacker.getPosY());
            putEntity(alienAttacker, alienAttacker.getPosX(), alienAttacker.getPosY());

        } else if (alienAttacker.getPosY() >= getTerreno().length - 1) {
            alienAttacker.setPosY(6);
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

        if (drone.getPosX() < 1 && drone.getPosY() == 1) { //Canto Superior Esquerdo
            drone.setPosX(1);
            drone.setPosY(1);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());

        } else if (drone.getPosX() == 1 && drone.getPosY() < 1) { //Canto Superior Esquerdo
            drone.setPosX(1);
            drone.setPosY(1);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());

        } else if (drone.getPosX() < 1 && drone.getPosY() == 6) { //Canto Superior Direito
            drone.setPosX(1);
            drone.setPosY(6);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());

        } else if (drone.getPosX() == 1 && drone.getPosY() > 6) { //Canto Superior Direito
            drone.setPosX(1);
            drone.setPosY(6);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());

        } else if (drone.getPosX() > 6 && drone.getPosY() == 6) { //Canto Inferior Direito
            drone.setPosX(6);
            drone.setPosY(6);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());

        } else if (drone.getPosX() == 6 && drone.getPosY() > 6) { //Canto Inferior Direito
            drone.setPosX(6);
            drone.setPosY(6);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());

        } else if (drone.getPosX() > 6 && drone.getPosY() == 1) { //Canto Inferior Esquerdo
            drone.setPosX(6);
            drone.setPosY(1);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());

        } else if (drone.getPosX() == 6 && drone.getPosY() < 1) { //Canto Inferior Esquerdo
            drone.setPosX(6);
            drone.setPosY(1);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());

        } else if (drone.getPosX() <= 1) {
            drone.setPosX(1);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());

        } else if (drone.getPosX() >= getTerreno().length - 1) {
            drone.setPosX(6);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());

        } else if (drone.getPosY() <= 1) {
            drone.setPosY(1);
            setEntityReset(drone.getPosX(), drone.getPosY());
            putEntity(drone, drone.getPosX(), drone.getPosY());

        } else if (drone.getPosY() >= getTerreno().length - 1) {
            drone.setPosY(6);
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
        int droneX = drone.getPosX();
        int droneY = drone.getPosY();

        if (droneX >= getTerreno().length - 1) { //Verificar se esta no lado direito

            if (droneX == 6 && droneY == 1) { //Canto superior direito

                if (terreno[droneX - 1][droneY] == alienAttacker) { //Verifica à esquerda
                    return true;

                } else return terreno[droneX][droneY + 1] == alienAttacker; //Verifica abaixo              
                
            } else if (droneX == 6 && droneY == 6) { //Canto Inferior Direito

                if (terreno[droneX][droneY - 1] == alienAttacker) { //Verifica acima
                    return true;

                } else return terreno[droneX - 1][droneY] == alienAttacker; //Verifica à esquerda     
                
            } else { //Resto da coluna 6
                
                if (terreno[droneX][droneY - 1] == alienAttacker) { //Verifica acima
                    return true;

                } else if (terreno[droneX - 1][droneY] == alienAttacker) { //Verifica à esquerda
                    return true;

                } else return terreno[droneX][droneY + 1] == alienAttacker; //Verifica abaixo

            }

        } else if (droneY >= getTerreno().length - 1) { //Verificar se esta abaixo

            if (droneX == 6 && droneY == 6) { //Canto Inferior Direito

                if (terreno[droneX][droneY - 1] == alienAttacker) { //Verifica acima
                    return true;

                } else return terreno[droneX - 1][droneY] == alienAttacker; //Verifica à esquerda
    
            } else if (droneX == 1 && droneY == 6) { //Canto Inferior Esquerdo

                if (terreno[droneX][droneY - 1] == alienAttacker) { //Verifica acima
                    return true;

                } else return terreno[droneX + 1][droneY] == alienAttacker; //Verifica à direita

            } else { //Resto da linha 6

                if (terreno[droneX][droneY - 1] == alienAttacker) { //Verifica acima
                    return true;

                } else if (terreno[droneX + 1][droneY] == alienAttacker) { //Verifica à direita
                    return true;

                } else return terreno[droneX - 1][droneY] == alienAttacker; //Verifica à esquerda
  
            }

        } else if (droneX == 1) { //Verificar se esta no lado esquerdo

            if (droneX == 1 && droneY == 6) { //Canto Inferior Esquerdo

                if (terreno[droneX][droneY - 1] == alienAttacker) { //Verifica acima
                    return true;

                } else return terreno[droneX + 1][droneY] == alienAttacker; //Verifica à direita
  
            } else if (droneX == 1 && droneY == 1) { //Canto Superior Esquerdo

                if (terreno[droneX][droneY + 1] == alienAttacker) { //Verifica abaixo
                    return true;

                } else return terreno[droneX + 1][droneY] == alienAttacker; //Verifica à direita
   
            } else { //Resto da coluna 1

                if (terreno[droneX][droneY - 1] == alienAttacker) { //Verifica acima
                    return true;

                } else if (terreno[droneX][droneY + 1] == alienAttacker) { //Verifica abaixo
                    return true;

                } else return terreno[droneX + 1][droneY] == alienAttacker; //Verifica à direita  
            }

        } else if (droneX == 1) { //Verificar se esta no topo

            if (droneX == 1 && droneY == 1) { //Canto Superior Esquerdo

                if (terreno[droneX][droneY + 1] == alienAttacker) { //Verifica abaixo
                    return true;

                } else return terreno[droneX + 1][droneY] == alienAttacker; //Verifica à direita
   
            } else if (droneX == 6 && droneY == 1) { //Canto superior direito

                if (terreno[droneX - 1][droneY] == alienAttacker) { //Verifica à esquerda
                    return true;

                } else return terreno[droneX][droneY + 1] == alienAttacker; //Verifica abaixo
  
            } else { //Resto da linha 1

                if (terreno[droneX - 1][droneY] == alienAttacker) { //Verifica à esquerda
                    return true;

                } else if (terreno[droneX][droneY + 1] == alienAttacker) { //Verifica abaixo
                    return true;

                } else return terreno[droneX + 1][droneY] == alienAttacker; //Verifica à direita   
            }
            
        }else{
            if (terreno[droneX][droneY + 1] == alienAttacker) { //Verifica abaixo
                return true;
                    
            }else if(terreno[droneX + 1][droneY] == alienAttacker){ //Verifica à direita
                return true;
                
            }else if (terreno[droneX][droneY - 1] == alienAttacker) { //Verifica acima
                return true;
                    
            }else return terreno[droneX - 1][droneY] == alienAttacker; //Verifica à esquerda         
        }
    }
    
    @Override
    public boolean putEntity(Alien a, int x, int y){
       
       for(int i = 1; i < terreno.length; i++){
           for(int j = 1; j < terreno[i].length; j++){
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
        
        for(int i = 1; i < terreno.length; i++){
            for(int j = 1; j < terreno[i].length; j++){
                if(terreno[i][j] == null ){
                    s += " - ";
                }else
                    s += " " + terreno[i][j] + " ";
                
            }
            s += "\n";
        }
        
        return s;
    }
    
    
    
}
