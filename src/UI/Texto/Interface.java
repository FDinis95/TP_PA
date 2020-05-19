package UI.Texto;

import Logica.*;
import Logica.dados.variations.MilitaryShip;
import Logica.estados.*;
import com.sun.xml.internal.ws.util.StringUtils;
import java.io.IOException;
import java.util.Scanner;

public class Interface {

    private MaquinaEstados ob;
    private Scanner scanner;
    private boolean sair = false;

    public Interface(MaquinaEstados obGame) {
        this.ob = obGame;
        scanner = new Scanner(System.in);
        
    }
    
    public void menuInicial() throws IOException {

        while (!sair) {

            if (ob.getEstado() instanceof WaitBegining) {
                UserInputWaitBeginning();

            } else if (ob.getEstado() instanceof WaitShipSelection) {
                UserInputWaitShipSelection();

            } else if (ob.getEstado() instanceof WaitMove) {
                UserInputWaitMove();

            } else if (ob.getEstado() instanceof WaitEvent) {
                UserInputWaitEvent();

            } else if (ob.getEstado() instanceof WaitPlanetSector) {
                UserInputWaitPlanetSector();

            } else if (ob.getEstado() instanceof WaitLanding) {
                UserInputWaitLanding();

            } else if (ob.getEstado() instanceof WaitSpaceStation) {
                UserInputWaitSpaceStation();

            } else if ((ob.getEstado() instanceof WaitGameOver)) {
                UserInputGameOver();

            }

        }

    }
    
    private void UserInputWaitBeginning(){
                
        ob.setup();
        
    }
    
    private void UserInputWaitShipSelection(){
                
        int valor;        
        do {
            System.out.println("\nEscolha o tipo de navio para o jogo!");
            System.out.println("1 - Navio Militar");
            System.out.println("2 - Navio Mineiro");
            System.out.print(">> ");

            valor = scanner.nextInt();
            if(valor <= 0 || valor >= 3)
                System.out.println("Nao escolheu um navio disponivel, volte a tentar\n");

        } while (valor != 1 && valor != 2);
        
        ob.start(valor);
        
    }
    
    private void UserInputWaitMove(){
        
        int choice = 0;
        int moveType = 0;
        int sectorType = 0;
        int planetType = 0;
        
        
        if (!ob.getJogo().getWasPlanet()) {
            do {
                System.out.println("\nEntrar em?");
                System.out.println("1 - Modo Automatico\n2 - Modo manual");
                System.out.print(">> ");
                choice = scanner.nextInt();

            } while (choice != 1 && choice != 2);

            switch (choice) {
                case 1:
                    ob.move();
                    break;

                case 2: //do while()
                    do {
                        System.out.println("\nDefinir o tipo de movimento");
                        System.out.println("1 - Normal\n2 - Buraco Negro");
                        System.out.println(">> ");
                        moveType = scanner.nextInt();

                    } while (moveType != 1 && moveType != 2);

                    do {
                        System.out.println("\nDefinir o tipo de sector");
                        System.out.println("1 - Branco\n2 - Vermelho");
                        System.out.println(">> ");
                        sectorType = scanner.nextInt();

                    } while (sectorType != 1 && sectorType != 2);

                    do {
                        System.out.println("\nDefinir o tipo de Planeta");
                        System.out.println("1 - Planeta Verde\n2 - Planeta Preto\n3 - Planeta Vermelho\n4 - Planeta Azul");
                        System.out.println(">> ");
                        planetType = scanner.nextInt();

                    } while (planetType != 1 && planetType != 2 && planetType != 3 && planetType != 4);

                    ob.move(moveType, sectorType, planetType);

            }
        }else
            ob.move();
        
    }
    
    private void UserInputWaitEvent(){
        
        int choice = 0;
        
        do{
            System.out.println("\nEscolha uma das seguintes opcoes!");
            System.out.println("1 - Escolher Evento ao calhas");
            System.out.println("2 - Escolher Evento por ID");
            System.out.print(">> ");
            
            choice = scanner.nextInt();
            
        }while(choice != 1 && choice != 2);
        
        switch(choice){
            case 1:
                ob.rollD6();
                break;
                
            case 2:
                do{
                    System.out.println("\nDigite um numero de 1 a 6");
                    System.out.print(">> ");
                    
                    choice = scanner.nextInt();
                    
                }while(choice < 1 && choice > 6);                
                
                ob.rollD6(choice);
        }

    }
    
    private void UserInputWaitPlanetSector() {
                
        int choice = 0;
        String from = " ", to = " ";
        
        System.out.println(ob.getJogo().getSpaceShip());
        
        if (ob.getJogo().getPlaneta().getSpaceStation() == null) {

            do {
                System.out.println("\nEscolha uma das seguintes opcoes!");
                System.out.println("1 - Ir para o planeta");
                System.out.println("2 - Converter Recursos");
                System.out.println("3 - Requesitar Log");
                System.out.println("4 - Artefactos a 5?");
                System.out.println("5 - Next Turn");
                System.out.print(">> ");

                choice = scanner.nextInt();
                if (choice <= 0 || choice >= 6) {
                    System.out.println("Nao escolheu nenhuma opcao disponivel, volte a tentar\n");
                }

            } while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5);
            
            switch (choice) {
                case 1:
                    ob.land();
                    break;
                    
                case 2:
                    do {
                        System.out.println("\nEscolha uma das seguintes opcoes de conversao!");
                        System.out.println("1 - Converter Recursos para Shield [Preto, Verde, Azul]");
                        System.out.println("2 - Converter Recursos para Fuel [Preto, Vermelho, Verde]");
                        System.out.println("3 - Converter Recursos para outro Recurso");
                        System.out.println("4 - Converter Recursos para a vida do Drone");
                        System.out.println("5 - Voltar atras");
                        System.out.print(">> ");

                        choice = scanner.nextInt();
                        if (choice <= 0 || choice >= 6) {
                            System.out.println("Nao escolheu nenhuma opcao disponivel, volte a tentar\n");
                        }

                    } while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5);
                    
                    if (choice == 3) {
                        do {
                            do {
                                System.out.println("\nDiga o nome do Recurso que pretende usar para a troca");
                                System.out.print(">> ");
                                from = scanner.next();
                                from = StringUtils.capitalize(from);
                                
                            } while (!(from.equals("Preto")) && !(from.equals("Vermelho")) && !(from.equals("Azul")) && !(from.equals("Verde")));

                            do {
                                System.out.println("Diga o nome do Recurso que pretende ganhar da troca");
                                System.out.print(">> ");
                                to = scanner.next();
                                to = StringUtils.capitalize(to);
                                
                            } while (!(from.equals("Preto")) && !(from.equals("Vermelho")) && !(from.equals("Azul")) && !(from.equals("Verde")));
                            
                        } while (from.equals(" ") && to.equals(" "));
                    }
                    
                    ob.useRecursos(choice, from, to);
                    break;
                    
                case 3:
                    ob.getJogo().getLog().requestLog();
                    break;
                
                case 4:
                    ob.getJogo().getSpaceShip().setArtifacts(5);
                    break;
                    
                case 5:
                    ob.nextTurn();                  
                    break;
                    
                default:
                    break;
            }
            
        } else{
            do {
                System.out.println("\nEscolha uma das seguintes opcoes!");
                System.out.println("1 - Ir para o Planeta");
                System.out.println("2 - Converter Recursos");
                System.out.println("3 - Ir para a SpaceStation");
                System.out.println("4 - Requesitar Log");
                System.out.println("5 - Artifacts a 5?");
                System.out.println("6 - Next Turn");
                System.out.print(">> ");

                choice = scanner.nextInt();
                if (choice <= 0 || choice >= 7) {
                    System.out.println("Nao escolheu nenhuma opcao disponivel, volte a tentar\n");
                }

            } while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6);
            
            switch (choice) {
                case 1:
                    ob.land();
                    break;
                    
                case 2:
                    
                    do {
                        System.out.println("\nEscolha uma das seguintes opcoes de conversao!");
                        System.out.println("1 - Converter Recursos para Shield [Preto, Verde, Azul]");
                        System.out.println("2 - Converter Recursos para Fuel [Preto, Vermelho, Verde]");
                        System.out.println("3 - Converter Recursos para outro Recurso");
                        System.out.println("4 - Converter Recursos para a vida do Drone");
                        System.out.println("5 - Voltar atras");
                        System.out.print(">> ");

                        choice = scanner.nextInt();
                        if (choice <= 0 || choice >= 6) {
                            System.out.println("Nao escolheu nenhuma opcao disponivel, volte a tentar\n");
                        }

                    } while (choice != 1 && choice != 2 && choice != 3 && choice != 4);
                    
                    if (choice == 3) {
                        do {
                            do {
                                System.out.println("\nDiga o nome do Recurso que pretende usar para a troca");
                                System.out.print(">> ");
                                from = scanner.next();
                                from.toUpperCase().charAt(0);
                                
                            } while (!(from.equals("Preto")) && !(from.equals("Vermelho")) && !(from.equals("Azul")) && !(from.equals("Verde")));

                            do {
                                System.out.println("Diga o nome do Recurso que pretende ganhar da troca");
                                System.out.print(">> ");
                                to = scanner.next();
                                to.toUpperCase().charAt(0);
                                
                            } while (!(from.equals("Preto")) && !(from.equals("Vermelho")) && !(from.equals("Azul")) && !(from.equals("Verde")));
                            
                        } while (from.equals(" ") && to.equals(" "));
                    }
                    
                    ob.useRecursos(choice, from, to);
                    break;
                    
                case 3:
                    ob.dock();
                    break;
                    
                case 4:
                    ob.getJogo().getLog().requestLog();
                    break;
                    
                case 5:
                    ob.getJogo().getSpaceShip().setArtifacts(5);
                    break;
                    
                case 6:
                    ob.nextTurn();
                    break;
                    
                default:
                    break;
            }
        }
                
    }
    
    private void UserInputWaitSpaceStation(){
        
        int choice = 0;
        String from = " ", to = " ";
        
        if(ob.getJogo().getSpaceShip() instanceof MilitaryShip){
            
            do {
                System.out.println("\nEscolha uma das seguintes opcoes!");
                System.out.println("1 - Upgrade cargo level");
                System.out.println("2 - Upgrade weapon system");
                System.out.println("3 - Converter Recursos para outros");
                System.out.println("4 - Comprar um novo Tripulante");
                System.out.println("5 - Aumentar a vida do Drone");
                System.out.println("6 - Comprar um novo Drone");
                System.out.println("7 - Sair da SpaceStation");
                System.out.print(">> ");

                choice = scanner.nextInt();
                if (choice <= 0 || choice >= 8) {
                    System.out.println("Nao escolheu nenhuma opcao disponivel, volte a tentar\n");
                }

            } while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6 && choice != 7);
        
        if (choice == 3) {
            System.out.println("\nDiga o nome do Recurso que pretende usar para a troca");
            System.out.print(">> ");
            from = scanner.next();

            System.out.println("Diga o nome do Recurso que pretende ganhar da troca");
            System.out.print(">> ");
            to = scanner.next();

        }
        
        if(choice != 7)
            ob.useSpaceStation(choice, from, to);
        
        else
            ob.unDock();
        
        }else{
            
            do {
                System.out.println("\nEscolha uma das seguintes opcoes!");
                System.out.println("1 - Upgrade cargo level");
                System.out.println("2 - Converter Recursos para outros");
                System.out.println("3 - Comprar um novo Tripulante");
                System.out.println("4 - Aumentar a vida do Drone");
                System.out.println("5 - Comprar um novo Drone");
                System.out.println("6 - Sair da SpaceStation");
                System.out.print(">> ");

                choice = scanner.nextInt();
                if (choice <= 0 || choice >= 7) {
                    System.out.println("Nao escolheu nenhuma opcao disponivel, volte a tentar\n");
                }

            } while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6);
        
        if (choice == 2) {
            System.out.println("\nDiga o nome do Recurso que pretende usar para a troca");
            System.out.print(">> ");
            from = scanner.next();

            System.out.println("Diga o nome do Recurso que pretende ganhar da troca");
            System.out.print(">> ");
            to = scanner.next();

        }
        
        if(choice != 6)
            ob.useSpaceStation(choice, from, to);
        
        else
            ob.unDock();
            
            
        }
            
                
    }
    
    private void UserInputWaitLanding() {

        do {
            if (ob.getJogo().getPlaneta().getTerreno().checkFight()) {
                while (!ob.getJogo().getFightOver()) {
                    ob.alienAttack();

                }
            }

            if (ob.getJogo().getSpaceShip().getDrone() != null) {
                System.out.println("Escolha uma das direcoes:");
                System.out.println("1 - ^\n2 - v\n3 - >\n4 - < ");
                System.out.print(">> ");

                switch (scanner.nextInt()) {
                    case 1:
                        ob.moveToResource(-1, 0);
                        break;
                    case 2:
                        ob.moveToResource(1, 0);
                        break;
                    case 3:
                        ob.moveToResource(0, 1);
                        break;
                    default:
                        ob.moveToResource(0, -1);
                        break;

                }

                System.out.println(ob.getJogo().getPlaneta().getTerreno());
                System.out.println("InitialDroneX: " + ob.getJogo().getInitialDroneX() + "\nInitialDroneY: " + ob.getJogo().getInitialDroneY());

                if ((ob.getJogo().getPlaneta().getTerreno().getDrone().getPosX() == ob.getJogo().getInitialDroneX())
                        && (ob.getJogo().getPlaneta().getTerreno().getDrone().getPosY() == ob.getJogo().getInitialDroneY())
                        && ob.getJogo().getPlaneta().getTerreno().getDrone().getRes() != null) {
                    ob.hasResource();
                }
            } else {
                break;
            }
        } while ((ob.getJogo().getPlaneta().getTerreno().getDrone().getRes() == null));

    }

    private void UserInputGameOver() throws IOException{
        
        int choice = 0;

        //Decisão final onde se pergunta se queremos jogar outra vez ou sair
        System.out.println("\nEscolha uma das direcoes:");
        System.out.println("1 - Começar um novo jogo\n2 - Sair\n");
        System.out.print(">> ");
        
        choice = scanner.nextInt();
        
        switch(choice){
            case 1:
                ob.newGame();
                break;
                
            case 2:
                EscreverFicheiro logging = new EscreverFicheiro(ob.getJogo());
                sair = true;
        }
        
    }
}
