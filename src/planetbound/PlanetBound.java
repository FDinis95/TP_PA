package planetbound;

import Logica.MaquinaEstados;
import UI.Texto.Interface;
import java.io.IOException;

public class PlanetBound {

    public static void main(String[] args) throws IOException {
               
        Interface inter = new Interface(new MaquinaEstados());
        inter.menuInicial();
        
    }

}
