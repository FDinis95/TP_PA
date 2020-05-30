package Logica.estados;

import Logica.InteracaoEsperada;
import Logica.Jogo;
import Logica.dados.variations.MilitaryShip;

public class WaitSpaceStation extends StateAdapter {

    public WaitSpaceStation(Jogo jogo) {
        super(jogo);
    }

    @Override
    public Estado unDock() {

        return new WaitPlanetSector(getJogo());
    }

    @Override
    public Estado useSpaceStation(int valor, String from, String to) {

        if (getJogo().getSpaceShip() instanceof MilitaryShip) {

            //Troca de recursos para aumentar o cargoHold
            switch (valor) {
                case 1:
                    if (!getJogo().getUpgradeLimit()) {
                        if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Preto")) - 3) < 0) {
                            getJogo().getLog().addLog("Nao tens recursos pretos suficientes para fazer esta troca!");
                            getJogo().getLog().printLogs();
                            getJogo().getLog().clearLog();

                            return new WaitSpaceStation(getJogo());

                        } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Verde")) - 3) < 0) {
                            getJogo().getLog().addLog("Nao tens recursos verdes suficientes para fazer esta troca!");
                            getJogo().getLog().printLogs();
                            getJogo().getLog().clearLog();

                            return new WaitSpaceStation(getJogo());

                        } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Vermelho")) - 3) < 0) {
                            getJogo().getLog().addLog("Nao tens recursos vermelhos suficientes para fazer esta troca!");
                            getJogo().getLog().printLogs();
                            getJogo().getLog().clearLog();

                            return new WaitSpaceStation(getJogo());

                        } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Azul")) - 3) < 0) {
                            getJogo().getLog().addLog("Nao tens recursos azuis suficientes para fazer esta troca!");
                            getJogo().getLog().printLogs();
                            getJogo().getLog().clearLog();

                            return new WaitSpaceStation(getJogo());

                        } else {
                            //Feitas as verificações, vamos substrair os Recursos e adicionar o upgrade level
                            getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Preto"), 3);
                            getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Verde"), 3);
                            getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Vermelho"), 3);
                            getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Azul"), 3);

                            getJogo().getSpaceShip().addUpgradeLevel();

                            getJogo().getLog().addLog("Recursos Subtraidos e 1 nivel aumentado!");
                            getJogo().getLog().addLog("Upgrade Level: " + getJogo().getSpaceShip().getUpgradeLevel());
                            getJogo().getLog().printLogs();
                            getJogo().getLog().clearLog();
                        }
                    }

                    break;
                //Troca de recursos para aumentar o weaponSystem
                case 2:

                    if((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Preto")) - 1) < 0){
                        getJogo().getLog().addLog("Nao tens recursos pretos suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();
                        
                        return new WaitSpaceStation(getJogo());
                        
                    }else if((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Azul")) - 1) < 0){
                        getJogo().getLog().addLog("Nao tens recursos azuis suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();
                        
                    }else{
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Preto"), 1);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Azul"), 1);
                        getJogo().getSpaceShip().addWeaponUpgradeLimit();
                        getJogo().getLog().addLog("Recursos Subtraidos e 1 nivel de WeaponSystem aumentado!");
                        getJogo().getLog().addLog("Weapon Upgrade Level: " + getJogo().getSpaceShip().getWeaponUpgradeLimit());
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();
                        
                    }
                    
                    break;
                //Troca de recursos por recursos    
                case 3:
                    if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo(from)) - 1) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos " + from + " suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());
                    } else if (getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo(to)) + 1 > getJogo().getSpaceShip().getMaxCargo()) {

                        getJogo().getLog().addLog("Nao e preciso gastar recursos, ja tens o cargo pretendido no maximo!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else {
                        //Feitas as verificações, vamos substrair os Recursos e adicionar o Fuel
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo(from), 1);
                        getJogo().getSpaceShip().addCargo(getJogo().getSpaceShip().getSpecificCargo(to), 1);

                        getJogo().getLog().addLog("Recurso " + from + " Subtraido e Recurso " + to + " Adicionado!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();
                    }

                    break;
                //Troca de recursos por 1 crewMember    
                case 4:
                    if (getJogo().getSpaceShip().getCrewMembers().size() == 4) {
                        getJogo().getLog().addLog("Nao precisas de comprar mais nenhum tripulante!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();
                    }
                    if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Preto")) - 1) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos pretos suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Verde")) - 1) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos verdes suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Vermelho")) - 1) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos vermelhos suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Azul")) - 1) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos azuis suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else {
                        //Feitas as verificações, vamos substrair os Recursos e adicionar o crewMember
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Preto"), 1);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Verde"), 1);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Vermelho"), 1);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Azul"), 1);

                        switch (getJogo().getSpaceShip().getCrewMembers().size()) {
                            case 1:
                                getJogo().getSpaceShip().addCrewMember("Navigation");
                                getJogo().getLog().addLog("Encontraste um Navigation Officer!");
                                break;

                            case 2:
                                getJogo().getSpaceShip().addCrewMember("Landing");
                                getJogo().getLog().addLog("Encontraste um Landing Officer!");
                                break;

                            case 3:
                                getJogo().getSpaceShip().addCrewMember("Shields");
                                getJogo().getLog().addLog("Encontraste um Shield Officer!");
                                break;

                            case 4:
                                getJogo().getSpaceShip().addCrewMember("Cargo");
                                getJogo().getLog().addLog("Encontraste um Cargo Officer!");
                                break;

                            default:
                                getJogo().getLog().addLog("Felizmente tens todos os tripulantes!");
                                break;
                        }

                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();
                    }

                    break;
                //Trocar Recursos pela vida do Drone    
                case 5:
                    if (getJogo().getSpaceShip().getDrone().getVida() == getJogo().getSpaceShip().getDrone().getMaxVida()) {
                        getJogo().getLog().addLog("Nao precisas de recuperar a vida do Drone, ja esta no maximo!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Preto")) - 1) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos pretos suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Verde")) - 1) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos verdes suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Vermelho")) - 1) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos vermelhos suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Azul")) - 1) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos azuis suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else {
                        //Feitas as verificações, vamos substrair os Recursos e adicionar a vida
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Preto"), 1);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Verde"), 1);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Vermelho"), 1);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Azul"), 1);

                        getJogo().getSpaceShip().getDrone().setVida(getJogo().getSpaceShip().getDrone().getMaxVida());
                        getJogo().getLog().addLog("Vida do drone recuperada!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                    }

                    break;
                //Troca de Recursos por um novo Drone    
                case 6:
                    if (getJogo().getSpaceShip().getDrone() != null) {
                        getJogo().getLog().addLog("Nao precisas de comprar um novo drone, ja tens um!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Preto")) - 2) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos pretos suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Verde")) - 2) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos verdes suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Vermelho")) - 2) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos vermelhos suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Azul")) - 2) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos azuis suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else {
                        //Feitas as verificações, vamos substrair os Recursos e um novo drone
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Preto"), 2);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Verde"), 2);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Vermelho"), 2);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Azul"), 2);

                        getJogo().getSpaceShip().getDrone().setVida(getJogo().getSpaceShip().getDrone().getMaxVida());
                        getJogo().getLog().addLog("Drone adicionado com sucesso!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();
                    }
                    break;

                default:
                    break;

            }
        //Não é um MilitaryShip
        } else {

            //Troca de recursos para aumentar o cargoHold
            switch (valor) {
                case 1:
                    if (!getJogo().getUpgradeLimit()) {
                        if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Preto")) - 3) < 0) {
                            getJogo().getLog().addLog("Nao tens recursos pretos suficientes para fazer esta troca!");
                            getJogo().getLog().printLogs();
                            getJogo().getLog().clearLog();

                            return new WaitSpaceStation(getJogo());

                        } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Verde")) - 3) < 0) {
                            getJogo().getLog().addLog("Nao tens recursos verdes suficientes para fazer esta troca!");
                            getJogo().getLog().printLogs();
                            getJogo().getLog().clearLog();

                            return new WaitSpaceStation(getJogo());

                        } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Vermelho")) - 3) < 0) {
                            getJogo().getLog().addLog("Nao tens recursos vermelhos suficientes para fazer esta troca!");
                            getJogo().getLog().printLogs();
                            getJogo().getLog().clearLog();

                            return new WaitSpaceStation(getJogo());

                        } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Azul")) - 3) < 0) {
                            getJogo().getLog().addLog("Nao tens recursos azuis suficientes para fazer esta troca!");
                            getJogo().getLog().printLogs();
                            getJogo().getLog().clearLog();

                            return new WaitSpaceStation(getJogo());

                        } else {
                            //Feitas as verificações, vamos substrair os Recursos e adicionar o upgrade level
                            getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Preto"), 3);
                            getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Verde"), 3);
                            getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Vermelho"), 3);
                            getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Azul"), 3);

                            getJogo().getSpaceShip().addUpgradeLevel();

                            getJogo().getLog().addLog("Recursos Subtraidos e 1 nivel aumentado!");
                            getJogo().getLog().addLog("Upgrade Level: " + getJogo().getSpaceShip().getUpgradeLevel());
                            getJogo().getLog().printLogs();
                            getJogo().getLog().clearLog();
                        }
                    }

                    break;

                //Troca de recursos por recursos    
                case 2:
                    if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo(from)) - 1) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos " + from + " suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());
                    } else if (getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo(to)) + 1 > getJogo().getSpaceShip().getMaxCargo()) {

                        getJogo().getLog().addLog("Nao e preciso gastar recursos, ja tens o cargo pretendido no maximo!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else {
                        //Feitas as verificações, vamos substrair os Recursos e adicionar o Fuel
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo(from), 1);
                        getJogo().getSpaceShip().addCargo(getJogo().getSpaceShip().getSpecificCargo(to), 1);

                        getJogo().getLog().addLog("Recurso " + from + " Subtraido e Recurso " + to + " Adicionado!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();
                    }

                    break;
                //Troca de recursos por 1 crewMember    
                case 3:
                    if (getJogo().getSpaceShip().getCrewMembers().size() == 4) {
                        getJogo().getLog().addLog("Nao precisas de comprar mais nenhum tripulante!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();
                    }
                    if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Preto")) - 1) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos pretos suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Verde")) - 1) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos verdes suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Vermelho")) - 1) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos vermelhos suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Azul")) - 1) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos azuis suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else {
                        //Feitas as verificações, vamos substrair os Recursos e adicionar o crewMember
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Preto"), 1);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Verde"), 1);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Vermelho"), 1);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Azul"), 1);

                        switch (getJogo().getSpaceShip().getCrewMembers().size()) {
                            case 1:
                                getJogo().getSpaceShip().addCrewMember("Navigation");
                                getJogo().getLog().addLog("Encontraste um Navigation Officer!");
                                break;

                            case 2:
                                getJogo().getSpaceShip().addCrewMember("Landing");
                                getJogo().getLog().addLog("Encontraste um Landing Officer!");
                                break;

                            case 3:
                                getJogo().getSpaceShip().addCrewMember("Shields");
                                getJogo().getLog().addLog("Encontraste um Shield Officer!");
                                break;

                            case 4:
                                getJogo().getSpaceShip().addCrewMember("Cargo");
                                getJogo().getLog().addLog("Encontraste um Cargo Officer!");
                                break;

                            default:
                                getJogo().getLog().addLog("Felizmente tens todos os tripulantes!");
                                break;
                        }

                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();
                    }

                    break;
                //Trocar Recursos pela vida do Drone    
                case 4:
                    if (getJogo().getSpaceShip().getDrone().getVida() == getJogo().getSpaceShip().getDrone().getMaxVida()) {
                        getJogo().getLog().addLog("Nao precisas de recuperar a vida do Drone, ja esta no maximo!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Preto")) - 1) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos pretos suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Verde")) - 1) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos verdes suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Vermelho")) - 1) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos vermelhos suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Azul")) - 1) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos azuis suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else {
                        //Feitas as verificações, vamos substrair os Recursos e adicionar a vida
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Preto"), 1);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Verde"), 1);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Vermelho"), 1);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Azul"), 1);

                        getJogo().getSpaceShip().getDrone().setVida(getJogo().getSpaceShip().getDrone().getMaxVida());
                        getJogo().getLog().addLog("Vida do drone recuperada!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                    }

                    break;
                //Troca de Recursos por um novo Drone    
                case 5:
                    if (getJogo().getSpaceShip().getDrone() != null) {
                        getJogo().getLog().addLog("Nao precisas de comprar um novo drone, ja tens um!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Preto")) - 2) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos pretos suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Verde")) - 2) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos verdes suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Vermelho")) - 2) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos vermelhos suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else if ((getJogo().getSpaceShip().getSpecificCargoValue(getJogo().getSpaceShip().getSpecificCargo("Azul")) - 2) < 0) {
                        getJogo().getLog().addLog("Nao tens recursos azuis suficientes para fazer esta troca!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();

                        return new WaitSpaceStation(getJogo());

                    } else {
                        //Feitas as verificações, vamos substrair os Recursos e um novo drone
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Preto"), 2);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Verde"), 2);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Vermelho"), 2);
                        getJogo().getSpaceShip().subCargo(getJogo().getSpaceShip().getSpecificCargo("Azul"), 2);

                        getJogo().getSpaceShip().getDrone().setVida(getJogo().getSpaceShip().getDrone().getMaxVida());
                        getJogo().getLog().addLog("Drone adicionado com sucesso!");
                        getJogo().getLog().printLogs();
                        getJogo().getLog().clearLog();
                    }
                    break;

                default:
                    break;

            }

        }

        return new WaitSpaceStation(getJogo());
    }
    
    @Override
    public InteracaoEsperada getInteracaoEsperada() {
        return InteracaoEsperada.INTERACAO_WAIT_SPACE_STATION;
        
    }

}
