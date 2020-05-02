package UI.Texto;

import Logica.*;
import Logica.estados.*;
import java.util.Scanner;

public class Interface {

    private ObservableGame ob;
    private Scanner scanner;

    public Interface(ObservableGame obGame) {
        this.ob = obGame;
        scanner = new Scanner(System.in);
        
    }
    
    public void menuInicial(){
        
        while(!(ob.getEstado() instanceof WaitGameOver)){
            
            if(ob.getEstado() instanceof WaitBegining){
                UserInputWaitBeginning();
                
            }else if(ob.getEstado() instanceof WaitShipSelection){            
                UserInputWaitShipSelection();
                
            }else if(ob.getEstado() instanceof WaitMove){
                UserInputWaitMove();
                
            }else if(ob.getEstado() instanceof WaitEvent){
                UserInputWaitEvent();
                
            }else if(ob.getEstado() instanceof WaitPlanetSector){
                UserInputWaitPlanetSector();
                
            }else if(ob.getEstado() instanceof WaitLanding){
                UserInputWaitLanding();
                
            }else if(ob.getEstado() instanceof WaitAlienAttack){
                UserInputWaitAlienAttack();
                
            }else if(ob.getEstado() instanceof WaitSpaceStation){
                UserInputWaitSpaceStation();
                
            }
        }
        
        UserInputGameOver();
    }
    
    private void UserInputWaitBeginning(){
        
//        System.out.println(" ------ WaitBeginningPhase Begin! ------ \n");
        
        ob.setup();
        
//        System.out.println(" ------ WaitBeginningPhase End! ------ \n");
    }
    
    private void UserInputWaitShipSelection(){
        
//        System.out.println(" ------ WaitShipSelectionPhase Begin! ------ \n\n");
        
        int valor;        
        do {
            System.out.println("Escolha o tipo de navio para o jogo!");
            System.out.println("1 - Navio Militar");
            System.out.println("2 - Navio Mineiro");
            System.out.print(">> ");

            valor = scanner.nextInt();
            if(valor <= 0 || valor >= 3)
                System.out.println("Nao escolheu um navio disponivel, volte a tentar\n");

        } while (valor != 1 && valor != 2);
        
        ob.start(valor);
//        System.out.println(" ------ WaitShipSelectionPhase End! ------ \n\n");
        
    }
    
    private void UserInputWaitMove(){
        
//        System.out.println(" ------ WaitMovePhase Begin! ------ \n\n");
   
        ob.move();

//        System.out.println(" ------ WaitMovePhase End! ------ \n\n");
        
    }
    
    private void UserInputWaitEvent(){
        
//        System.out.println(" ------ WaitEvent Begin! ------ \n");
        
        ob.rollD6();

//        System.out.println(" ------ WaitEvent End! ------ \n");
    }
    
    private void UserInputWaitPlanetSector() {
        
//        System.out.println(" ------ WaitPlanetSectorPhase Begin! ------ \n\n");
        
        //Check spacestation == null para saber qual dos tipos de interface escolher
        
        int choice = 0;
        String from = " ", to = " ";
        
        System.out.println(ob.getJogo().getSpaceShip());
        //Dar a opção de trocar recursos (se tiver o Cargo Officer), ir para a SpaceStation ou ir para o planeta
        if (ob.getJogo().getPlaneta().getSpaceStation() == null) {

            do {
                System.out.println("Escolha uma das seguintes opcoes!");
                System.out.println("1 - Ir para o planeta");
                System.out.println("2 - Converter Recursos");
                System.out.println("3 - Next Turn");
                System.out.print(">> ");

                choice = scanner.nextInt();
                if (choice <= 0 || choice >= 4) {
                    System.out.println("Nao escolheu nenhuma opcao disponivel, volte a tentar\n");
                }

            } while (choice != 1 && choice != 2 && choice != 3);
            
            switch (choice) {
                case 1:
                    ob.land();
                    break;
                    
                case 2:
                    do {
                        System.out.println("Escolha uma das seguintes opcoes de conversao!");
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
                    if(choice == 3){
                        System.out.println("Diga o nome do Recurso que pretende usar para a troca");
                        System.out.println(">> ");
                        from = scanner.next();
                        
                        System.out.println("Diga o nome do Recurso que pretende ganhar da troca");
                        System.out.println(">> ");
                        to = scanner.next();
                        
                    }
                    
                    ob.useRecursos(choice, from, to);
                    break;
                    
                case 3:
                    ob.nextTurn();
                    break;
                    
                default:
                    break;
            }
            
        } else{
            do {
                System.out.println("Escolha uma das seguintes opcoes!");
                System.out.println("1 - Ir para o Planeta");
                System.out.println("2 - Converter Recursos");
                System.out.println("3 - Ir para a SpaceStation");
                System.out.println("4 - Next Turn");
                System.out.print(">> ");

                choice = scanner.nextInt();
                if (choice <= 0 || choice >= 5) {
                    System.out.println("Nao escolheu nenhuma opcao disponivel, volte a tentar\n");
                }

            } while (choice != 1 && choice != 2 && choice != 3 && choice != 4);
            
            switch (choice) {
                case 1:
                    ob.land();
                    break;
                    
                case 2:
                    
                    do {
                        System.out.println("Escolha uma das seguintes opcoes de conversao!");
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
                    if(choice == 3){
                        System.out.println("Diga o nome do Recurso que pretende usar para a troca");
                        System.out.println(">> ");
                        from = scanner.next();
                        
                        System.out.println("Diga o nome do Recurso que pretende ganhar da troca");
                        System.out.println(">> ");
                        to = scanner.next();
                        
                    }
                    
                    ob.useRecursos(choice, from, to);
                    break;
                    
                case 3:
                    ob.dock();
                    break;
                    
                case 4:
                    ob.nextTurn();
                    break;
                    
                default:
                    break;
            }
        }
        
//        System.out.println(" ------ WaitPlanetSectorPhase End! ------ \n\n");
        
    }
    
    private void UserInputWaitSpaceStation(){
        
//        System.out.println(" ------ WaitSpaceStationPhase Begin! ------ \n\n");
        int choice = 0;
        String from = " ", to = " ";
        //Sitio onde se vai fazer maior parte das trocas, ter em atençao à carta de referencia!
        do {
                System.out.println("Escolha uma das seguintes opcoes!");
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
            System.out.println("Diga o nome do Recurso que pretende usar para a troca");
            System.out.println(">> ");
            from = scanner.next();

            System.out.println("Diga o nome do Recurso que pretende ganhar da troca");
            System.out.println(">> ");
            to = scanner.next();

        }
        
        if(choice != 6)
            ob.useSpaceStation(choice, from, to);
        
        else
            ob.unDock();
        
//        System.out.println(" ------ WaitSpaceStationPhase End! ------ \n\n");
        
    }
    
    private void UserInputWaitLanding() {

//        System.out.println(" ------ WaitLandingPhase Begin! ------ \n\n");

        do{

            while(ob.getJogo().getPlaneta().getTerreno().checkFight()){
                ob.alienAttack();
                
            }
            
            
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
            
            if((ob.getJogo().getPlaneta().getTerreno().getDrone().getPosX() == ob.getJogo().getInitialDroneX()) &&
                (ob.getJogo().getPlaneta().getTerreno().getDrone().getPosY() == ob.getJogo().getInitialDroneY()))
            {
                ob.hasResource();
            }
            
        }while ((ob.getJogo().getPlaneta().getTerreno().getDrone().getRes() == null));
        
//        System.out.println(" ------ WaitLandingPhase End! ------ \n\n");

    }

    private void UserInputWaitAlienAttack(){
        
//        System.out.println(" ------ WaitAlienAttackPhase Begin! ------ \n\n");
        
        //Meramente ilustrativo para percebermos o que se passa no terreno.
        while(!ob.getJogo().getFightOver()){
            System.out.println("Prima uma tecla para andar com a luta para a frente!");            
            ob.alienAttack();
            
            scanner.next();
            
        }
        
        System.out.println(" ------ WaitAlienAttackPhase End! ------ \n\n");
        
    }

    private void UserInputGameOver(){
        
//        System.out.println(" ------ WaitGameOverPhase Begin! ------ \n\n");
        int choice = 0;

        //Decisão final onde se pergunta se queremos jogar outra vez ou sair
        System.out.println("Escolha uma das direcoes:");
        System.out.println("1 - Começar um novo jogo\n2 - Sair\n");
        System.out.print(">> ");
        
        choice = scanner.nextInt();
        
        switch(choice){
            case 1:
                ob.setup();
                break;
                
            case 2:
                System.exit(0);
        }
//        System.out.println(" ------ WaitGameOverPhase End! ------ \n\n");
        
    }
}
