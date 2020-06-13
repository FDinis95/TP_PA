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
        imagens.put(Constants.ARTIFACT, new Image(Resources.getImageResourceFile(Constants.ARTIFACT)));
        imagens.put(Constants.DRONE, new Image(Resources.getImageResourceFile(Constants.DRONE)));
        imagens.put(Constants.NO_DRONE, new Image(Resources.getImageResourceFile(Constants.NO_DRONE)));
        imagens.put(Constants.CAPTAIN_CREW, new Image(Resources.getImageResourceFile(Constants.CAPTAIN_CREW)));
        imagens.put(Constants.NAVIGATION_CREW, new Image(Resources.getImageResourceFile(Constants.NAVIGATION_CREW)));
        imagens.put(Constants.LANDING_CREW, new Image(Resources.getImageResourceFile(Constants.LANDING_CREW)));
        imagens.put(Constants.SHIELD_CREW, new Image(Resources.getImageResourceFile(Constants.SHIELD_CREW)));
        imagens.put(Constants.WEAPON_CREW, new Image(Resources.getImageResourceFile(Constants.WEAPON_CREW)));
        imagens.put(Constants.CARGO_CREW, new Image(Resources.getImageResourceFile(Constants.CARGO_CREW)));
        imagens.put(Constants.EVENTS, new Image(Resources.getImageResourceFile(Constants.EVENTS)));
        imagens.put(Constants.REFERENCE_CARD, new Image(Resources.getImageResourceFile(Constants.REFERENCE_CARD)));
        imagens.put(Constants.DIE1, new Image(Resources.getImageResourceFile(Constants.DIE1)));
        imagens.put(Constants.DIE2, new Image(Resources.getImageResourceFile(Constants.DIE2)));
        imagens.put(Constants.DIE3, new Image(Resources.getImageResourceFile(Constants.DIE3)));
        imagens.put(Constants.DIE4, new Image(Resources.getImageResourceFile(Constants.DIE4)));
        imagens.put(Constants.DIE5, new Image(Resources.getImageResourceFile(Constants.DIE5)));
        imagens.put(Constants.DIE6, new Image(Resources.getImageResourceFile(Constants.DIE6)));
        imagens.put(Constants.DIE_ANIMATION, new Image(Resources.getImageResourceFile(Constants.DIE_ANIMATION)));
        imagens.put(Constants.GREEN_RESOURCE, new Image(Resources.getImageResourceFile(Constants.GREEN_RESOURCE)));
        imagens.put(Constants.BLUE_RESOURCE, new Image(Resources.getImageResourceFile(Constants.BLUE_RESOURCE)));
        imagens.put(Constants.BLACK_RESOURCE, new Image(Resources.getImageResourceFile(Constants.BLACK_RESOURCE)));
        imagens.put(Constants.RED_RESOURCE, new Image(Resources.getImageResourceFile(Constants.RED_RESOURCE)));
        imagens.put(Constants.ARTIFACT_RESOURCE, new Image(Resources.getImageResourceFile(Constants.ARTIFACT_RESOURCE)));
        imagens.put(Constants.NO_RESOURCE, new Image(Resources.getImageResourceFile(Constants.NO_RESOURCE)));
        imagens.put(Constants.ALIEN, new Image(Resources.getImageResourceFile(Constants.ALIEN)));
        imagens.put(Constants.DESERT_TILE, new Image(Resources.getImageResourceFile(Constants.DESERT_TILE)));
        imagens.put(Constants.VICTORY, new Image(Resources.getImageResourceFile(Constants.VICTORY)));
        imagens.put(Constants.DEFEAT, new Image(Resources.getImageResourceFile(Constants.DEFEAT)));
        imagens.put(Constants.INITIAL, new Image(Resources.getImageResourceFile(Constants.INITIAL)));
        
    }

    public static Image getImagem(String nome){
        return imagens.get(nome);
        
    }
    
}
