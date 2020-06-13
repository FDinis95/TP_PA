package Logica.estados;

import Logica.InteracaoEsperada;

public interface Estado{
    
    //WaitBeginning
    Estado setup();
    
    //WaitShipSelection
    Estado selectShip(int valor);
    
    //WaitMove
    Estado move();
    Estado move(int move, int sector, int planet);
    Estado continua();
    
    //WaitPlanetSector
    Estado useRecursos(int valor, String from, String to);
    Estado nextTurn();
    Estado land();
    Estado dock();
    
    //WaitSpaceStation
    Estado unDock();
    Estado useSpaceStation(int valor, String from, String to);
    
    //WaitLanding
    Estado hasResource();
    Estado alienAttack();
    Estado moveToResource(int moveX, int moveY);
    
    //WaitAlienPhase
    Estado alienDestroysDrone();
    Estado alienDestroyed();
    
    //WaitEvent
    Estado rollD6();
    Estado rollD6(int id);
    
    //WaitGameOver
    Estado newGame();
    
    InteracaoEsperada getInteracaoEsperada();
}
