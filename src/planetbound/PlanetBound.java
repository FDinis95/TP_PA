package planetbound;

import Logica.EscreverFicheiro;
import Logica.Jogo;
import Logica.ObservableGame;
import Logica.dados.*;
//import Logica.dados.Planet;
import Logica.dados.variations.*;
import UI.Texto.Interface;
import java.io.IOException;

public class PlanetBound {

    public static void main(String[] args) throws IOException {
        
        //TODO - IR DO INICIO AO FIM E COMEÇAR O QUE FALTA (TERRENO E AFINS ESTAO FEITOS)
        //TODO MAJOR - Fazer uma versão Debug do Jogo
        
        Interface inter = new Interface(new ObservableGame());
        inter.menuInicial();
    }

}
