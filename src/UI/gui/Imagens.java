package UI.gui;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;


public class Imagens {
    
    private static final Map<String, Image> imagens = new HashMap<>();
    
    static{
        
        imagens.put(Constants.BACKGROUND, new Image(Resources.getImageResourceFile(Constants.BACKGROUND)));
        imagens.put(Constants.ROCKET_SHIP, new Image(Resources.getImageResourceFile(Constants.ROCKET_SHIP)));
        imagens.put(Constants.MINING_SHIP, new Image(Resources.getImageResourceFile(Constants.MINING_SHIP)));
        imagens.put(Constants.MILITARY_SHIP, new Image(Resources.getImageResourceFile(Constants.MILITARY_SHIP)));
        imagens.put(Constants.CREW_ARTIFACTS, new Image(Resources.getImageResourceFile(Constants.CREW_ARTIFACTS)));
        imagens.put(Constants.EVENTS, new Image(Resources.getImageResourceFile(Constants.EVENTS)));
        imagens.put(Constants.MILITARY_CARGO_HOLD, new Image(Resources.getImageResourceFile(Constants.MILITARY_CARGO_HOLD)));
        imagens.put(Constants.MILITARY_FUEL_SYSTEM, new Image(Resources.getImageResourceFile(Constants.MILITARY_FUEL_SYSTEM)));
        imagens.put(Constants.MILITARY_WEAPON_SHIELD, new Image(Resources.getImageResourceFile(Constants.MILITARY_WEAPON_SHIELD)));
        imagens.put(Constants.MINING_CARGO_HOLD, new Image(Resources.getImageResourceFile(Constants.MINING_CARGO_HOLD)));
        imagens.put(Constants.MINING_FUEL_SYSTEM, new Image(Resources.getImageResourceFile(Constants.MINING_FUEL_SYSTEM)));
        imagens.put(Constants.MINING_WEAPON_SHIELD, new Image(Resources.getImageResourceFile(Constants.MINING_WEAPON_SHIELD)));
        imagens.put(Constants.NAV1, new Image(Resources.getImageResourceFile(Constants.NAV1)));
        imagens.put(Constants.NAV2_3, new Image(Resources.getImageResourceFile(Constants.NAV2_3)));
        imagens.put(Constants.NAV4_5, new Image(Resources.getImageResourceFile(Constants.NAV4_5)));
        imagens.put(Constants.NAV6, new Image(Resources.getImageResourceFile(Constants.NAV6)));
        imagens.put(Constants.REFERENCE_CARD, new Image(Resources.getImageResourceFile(Constants.REFERENCE_CARD)));
        imagens.put(Constants.TERRAIN, new Image(Resources.getImageResourceFile(Constants.TERRAIN)));
        
    }

    public static Image getImagem(String nome){
        return imagens.get(nome);
        
    }
    
}
