package planetbound;

import Logica.ObservableGame;
import Logica.dados.*;
//import Logica.dados.Planet;
import Logica.dados.variations.*;
import UI.Texto.Interface;

public class PlanetBound {

    public static void main(String[] args) {

        //TODO - IR DO INICIO AO FIM E COMEÇAR O QUE FALTA (TERRENO E AFINS ESTAO FEITOS)
        //TODO MAJOR - Fazer uma versão Debug do Jogo
        
        Interface inter = new Interface(new ObservableGame());
        inter.menuInicial();
    }

}
